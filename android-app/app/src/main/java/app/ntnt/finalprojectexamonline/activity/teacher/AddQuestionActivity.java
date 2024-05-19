package app.ntnt.finalprojectexamonline.activity.teacher;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.test.QuestionActivity;
import app.ntnt.finalprojectexamonline.fragment.UploadImageFragment;
import app.ntnt.finalprojectexamonline.model.request.AnswerRequest;
import app.ntnt.finalprojectexamonline.model.request.QuestionRequest;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IQuestionService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuestionActivity extends AppCompatActivity implements UploadImageFragment.OnImageUploadedListener {
    Button btnAddAnswer, btnAddQuestion;
    LinearLayout linearLayout;
    UploadImageFragment uploadImageFragment;
    Uri mUri;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        btnAddAnswer = findViewById(R.id.btn_addAnswer);
        btnAddQuestion = findViewById(R.id.btn_addQuestion);
        linearLayout = findViewById(R.id.llt_addQues);
        editText = findViewById(R.id.edt_nameQuestion);
        Bundle bundle = getIntent().getExtras();
        Long topicId = bundle.getLong("topicId");
        Log.d("TAG", "onCreate: "+topicId);

        initFragMent();

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(linearLayout,topicId);
            }
        });

        btnAddAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEditTextAnswer();
            }
        });
    }

    private void add(LinearLayout linearLayout,Long topicId) {
        MultipartBody.Part img = AppConstrain.toPart(this, mUri);
        Log.d("TAG", "onResponse: " + img);

        BaseAPIService.createService(IQuestionService.class).addQuestion(addQuestion(linearLayout,topicId), img).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                Toast.makeText(AddQuestionActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddQuestionActivity.this, QuestionActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putLong("topicId",topicId);
                intent.putExtras(bundle1);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Log.d("TAG", "onResponse: failed");
            }
        });
    }

    private QuestionRequest addQuestion(LinearLayout linearLayout,Long topicId) {
        List<AnswerRequest> answerRequestList = new ArrayList<>();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof LinearLayout) {
                String text = "";
                boolean isCorrect = false;
                for (int j = 0; j < ((LinearLayout) view).getChildCount(); j++) {

                    View view1 = ((LinearLayout) view).getChildAt(j);

                    if (view1 instanceof EditText) {
                        EditText childEditText = (EditText) view1;
                        text = childEditText.getText().toString();
                    }
                    if (view1 instanceof CheckBox) {
                        CheckBox childCheckbox = (CheckBox) view1;
                        isCorrect = childCheckbox.isChecked();
                    }
                }
                answerRequestList.add(new AnswerRequest(text, isCorrect));
            }
        }
        QuestionRequest questionRequest = new QuestionRequest(editText.getText().toString(), topicId, answerRequestList);
        return questionRequest;
    }

    private void createEditTextAnswer() {
        LinearLayout linearLayout1 = new LinearLayout(AddQuestionActivity.this);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 8, 8, 8);
        layoutParams.weight = 4;
        layoutParams.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(8, 8, 8, 8);
        layoutParams2.weight = 1;
        layoutParams2.gravity = Gravity.CENTER;


        EditText editText = new EditText(AddQuestionActivity.this);
        editText.setLayoutParams(layoutParams);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setBackgroundResource(R.drawable.bg_blue_corner_border_16);
        editText.setTextColor(Color.WHITE);
        editText.setHintTextColor(Color.WHITE);
        editText.setGravity(Gravity.CENTER);
        editText.setHint("Nhập đáp án");

        CheckBox checkBox = new CheckBox(AddQuestionActivity.this);
        checkBox.setText("Đáp án");
        checkBox.setLayoutParams(layoutParams2);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setButtonTintList(ColorStateList.valueOf(Color.WHITE));
        checkBox.setChecked(false); // để thiết lập trạng thái được chọn hoặc không được chọn của CheckBox


        linearLayout1.addView(editText);
        linearLayout1.addView(checkBox);
        linearLayout.addView(linearLayout1);
    }

    private void initFragMent() {
        uploadImageFragment = UploadImageFragment.getInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.img_dialog_addImage, uploadImageFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onImageUploaded(Uri uri) {
        mUri = uri;
    }
}