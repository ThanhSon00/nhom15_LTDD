package app.ntnt.finalprojectexamonline.activity.test;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.HomeActivity;
import app.ntnt.finalprojectexamonline.adapter.LoadQuestionAdapter;
import app.ntnt.finalprojectexamonline.adapter.LoadQuestionHistoryAdapter;
import app.ntnt.finalprojectexamonline.model.request.HistoryRequest;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.HistoryItemResponse;
import app.ntnt.finalprojectexamonline.model.response.NewAnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewHisItemResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionHistoryResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.TestResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IHistoryService;
import app.ntnt.finalprojectexamonline.services.ITestService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadHistoryQuestionActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button button, submit;
    private ImageView imageView;
    List<QuestionResponse> questionResponses = new ArrayList<>();
    private int size = 10;
    private TextView txtBack, txtNext, txtCurrent, txtSumQuestion,textView;
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    List<NewQuestionResponse> newQuestionResponse = new ArrayList<NewQuestionResponse>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_question);
        init();

        Bundle bundle = getIntent().getExtras();
        Long hisItem = (Long) bundle.getLong("hisItem");
        Long testId = (Long) bundle.getLong("testId");
        String nameTest = (String) bundle.getString("nameTest");
        int status = (int) bundle.getInt("status");

        textView.setText("Đang xem lại "+ nameTest);
//        loadDataQuestion(testId);
        Long userId = SharedPrefManager.getInstance(LoadHistoryQuestionActivity.this).getUserId();
        BaseAPIService.createService(IHistoryService.class).getHistoryByHisItem(userId,hisItem).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {


                Object objects;
                objects = AppConstrain.toObject(response.body().getData(), HistoryItemResponse.class);
                HistoryItemResponse historyItemResponse = (HistoryItemResponse) objects;
                List<NewHisItemResponse> newHisItemResponses= historyItemResponse.getHisItems();

                List<NewAnswerResponse> answerSelected= new ArrayList<>();
                List<NewQuestionHistoryResponse> listQuestion = new ArrayList<>();

                for(NewHisItemResponse newHisItemResponse:newHisItemResponses)
                {
                    listQuestion.add(newHisItemResponse.getQuestion());
                    answerSelected.add(newHisItemResponse.getAnswer());
                }

                List<List<NewAnswerResponse>> listAnswer = new ArrayList<>();
                for(NewQuestionHistoryResponse newQuestionHistoryResponse: listQuestion)
                {
                    listAnswer.add(newQuestionHistoryResponse.getAnswers());
                }

                size = answerSelected.size();

                LoadQuestionHistoryAdapter viewPagerAdapter = new LoadQuestionHistoryAdapter(LoadHistoryQuestionActivity.this, listQuestion,listAnswer, answerSelected);
                viewPager.setAdapter(viewPagerAdapter);


                Log.d("TAG", "onResponse: success");

            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Log.d("TAG", "onResponse: failed");
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                txtCurrent.setText(String.valueOf(position + 1));
                txtSumQuestion.setText(String.valueOf(size));
                if (position == 0) {
                    txtBack.setVisibility(View.GONE);
                    txtNext.setVisibility(View.VISIBLE);
                    txtNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                } else if (position == size - 1) {
                    txtBack.setVisibility(View.VISIBLE);
                    txtNext.setVisibility(View.GONE);

                } else {
                    txtBack.setVisibility(View.VISIBLE);
                    txtNext.setVisibility(View.VISIBLE);
                }
                txtBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    }
                });
                txtNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoadHistoryQuestionActivity.this);
                builder.setMessage("Bạn có thực hiện thi lại bài thi này?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(status==1)
                        {
                            Intent intent = new  Intent(LoadHistoryQuestionActivity.this,LoadQuestionActivity.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putLong("testId",testId);
                            intent.putExtras(bundle1);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoadHistoryQuestionActivity.this, "Bài thi đã xóa khỏi hệ thống, bạn chỉ có thể xem lại", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Xử lý khi người dùng chọn hủy
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }


    private void loadDataQuestion(Long testId) {
        BaseAPIService.createService(ITestService.class).getTestById(testId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                Log.d("TAG", "onResponse: succees");
                if (response.body().isError() == false) {
                    Object objects;
                    objects = AppConstrain.toObject(response.body().getData(), TestResponse.class);
                    TestResponse testResponse = (TestResponse) objects;
                    questionResponses = testResponse.getQuestions();
                    List<List<AnswerResponse>> answerResponses = new ArrayList<>();
                    for (QuestionResponse questionResponse : questionResponses) {
                        answerResponses.add(questionResponse.getAnswers());
                    }
                    size = questionResponses.size();
                    newQuestionResponse = new ArrayList<NewQuestionResponse>();

                    for (QuestionResponse questionResponse : questionResponses) {
                        newQuestionResponse.add(new NewQuestionResponse(questionResponse, -1L));
                    }

                    LoadQuestionAdapter viewPagerAdapter = new LoadQuestionAdapter(LoadHistoryQuestionActivity.this, newQuestionResponse, answerResponses);
                    viewPager.setAdapter(viewPagerAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Log.d("TAG", "onResponse: failed");
            }
        });
    }

    private void init() {
        countdownText = findViewById(R.id.tv_timmer);
        txtBack = findViewById(R.id.txt_back);
        txtCurrent = findViewById(R.id.txt_curent);
        txtNext = findViewById(R.id.txt_next);
        txtSumQuestion = findViewById(R.id.txt_sumquestion);
        viewPager = findViewById(R.id.view_pager);

        submit = findViewById(R.id.btn_submit);

        imageView = findViewById(R.id.imgtimmer);
        imageView.setVisibility(View.INVISIBLE);
        countdownText.setVisibility(View.INVISIBLE);

        textView = findViewById(R.id.textView24);
//        submit.setText("Thi lại");

    }


}