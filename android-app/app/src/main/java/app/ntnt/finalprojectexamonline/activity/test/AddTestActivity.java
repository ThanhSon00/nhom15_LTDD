package app.ntnt.finalprojectexamonline.activity.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.MultiCheckboxSpinnerAdapter;
import app.ntnt.finalprojectexamonline.adapter.OnSpinnerCheckboxSelectedListener;
import app.ntnt.finalprojectexamonline.adapter.QuestionAdapter;
import app.ntnt.finalprojectexamonline.adapter.SpinnerAdapter;
import app.ntnt.finalprojectexamonline.fragment.DialogFragmentTest;
import app.ntnt.finalprojectexamonline.model.entites.Question;
import app.ntnt.finalprojectexamonline.model.entites.Subject;
import app.ntnt.finalprojectexamonline.model.entites.Topic;
import app.ntnt.finalprojectexamonline.model.request.TestRequest;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.SubjectResponse;
import app.ntnt.finalprojectexamonline.model.response.TestResponse;
import app.ntnt.finalprojectexamonline.model.response.TopicResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IQuestionService;
import app.ntnt.finalprojectexamonline.services.ISubjectService;
import app.ntnt.finalprojectexamonline.services.ITestService;
import app.ntnt.finalprojectexamonline.services.ITopicService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTestActivity extends AppCompatActivity {
    Spinner spinnerSubject, spinnerTopic;
    QuestionAdapter questionAdapter;
    List<QuestionResponse> questions;
    RecyclerView recyclerView;
    Button btnAddTest;

    ArrayList<Topic> topics;
    boolean[] selected;
    List<Topic> selectedItems;
    ImageView imageViewCountQuestion, imageReloadTopic;
    SpinnerAdapter adapter;
    EditText editTextNameTest,edtTime,edtCountQues;
    long idSubject = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        imageViewCountQuestion = findViewById(R.id.img_countQues);
        spinnerTopic = findViewById(R.id.spinnerTopic);
        btnAddTest = findViewById(R.id.btn_add_test);
        imageReloadTopic = findViewById(R.id.img_reload_topic);
        recyclerView = findViewById(R.id.rcv_list_ques_for_add);
        editTextNameTest = findViewById(R.id.edt_name_test);
        edtTime= findViewById(R.id.edt_time_test);
        edtCountQues = findViewById(R.id.edt_num_questions);


        // Tạo popup window
        PopupWindow popupWindow = new PopupWindow(getApplicationContext());
        TextView popupTextView = new TextView(getApplicationContext());
        // popupTextView.setBackgroundResource(R.drawable.popup_background);
        popupTextView.setPadding(10, 10, 10, 10);
        popupTextView.setTextColor(Color.WHITE);
        popupWindow.setContentView(popupTextView);

        // Bắt sự kiện OnTouchListener cho TextView
        imageViewCountQuestion.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {

                return false;
            }
        });
        imageViewCountQuestion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Hiển thị popup window tại vị trí của sự kiện
                        popupWindow.showAtLocation(imageViewCountQuestion, Gravity.NO_GRAVITY,
                                (int) event.getRawX(), (int) event.getRawY() + 30);
                        // Cập nhật nội dung cho popup window
                        popupTextView.setText("Khi số câu hỏi được chọn bên dưới nhỏ hơn số lượng câu hỏi được điền ở đây" +
                                " sẽ tiến hành chọn ngẫu nhiên các câu hỏi không trùng bên dưới để tạo thành đề thi hoàn chỉnh"
                        );
                        break;
                    case MotionEvent.ACTION_UP:
                        // Ẩn popup window khi thả chuột
                        popupWindow.dismiss();
                        break;

                }
                return true;
            }
        });


        btnAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAPIService.createService(ITestService.class).addTest(createTest()).enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                      if(response.body().isError())
                      {
                          Log.d("TAG", "onResponse: failed");
                          Toast.makeText(AddTestActivity.this, "Lỗi khi thêm", Toast.LENGTH_SHORT).show();
                      }

                        Log.d("TAG", "onResponse: success");

                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Log.d("TAG", "onResponse: failed");
                    }
                });


                Log.d("TAG", "onClick: " + questionAdapter.questionsChecked().toString());
            }
        });
        imageReloadTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                setQuestionAdapter();
            }
        });

        LoadDataTopic(spinnerTopic);

    }


    private void setQuestionAdapter() {
        questionAdapter = new QuestionAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<Long> items = new ArrayList<>();
        for (Topic topic : selectedItems) {
            items.add(topic.getId());
        }
        for (Long id : items) {
            questions = new ArrayList<>();
            BaseAPIService.createService(IQuestionService.class).getQuestionByTopicId(id).enqueue(new Callback<ResponseEntity>() {
                @Override
                public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                    List<Object> objects;
                    objects = AppConstrain.objectList(response.body().getData(), QuestionResponse.class);
                    for (Object i : objects) {
                        questions.add((QuestionResponse) i);
                    }
                    questionAdapter.setData(questions);
                }

                @Override
                public void onFailure(Call<ResponseEntity> call, Throwable t) {

                }
            });
        }

        recyclerView.setAdapter(questionAdapter);
    }

    private void LoadDataTopic(Spinner spinner) {
        topics = new ArrayList<>();

        BaseAPIService.createService(ISubjectService.class)
                .getSubjectByUserId(SharedPrefManager.getInstance(AddTestActivity.this).getUserId())
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        Object objects;
                        objects = AppConstrain.toObject(response.body().getData(), SubjectResponse.class);
                        SubjectResponse subjectResponse = (SubjectResponse) objects;
                        idSubject = subjectResponse.getSubjectId();

                        Log.d("TAG", "onResponse: " + subjectResponse.getSubjectId().toString());

                        if (idSubject != -1) {

                            BaseAPIService.createService(ITopicService.class).getTopicBySubjectId(idSubject, 0, 100).enqueue(new Callback<TopicResponse>() {
                                @Override
                                public void onResponse(Call<TopicResponse> call, Response<TopicResponse> response) {
                                    List<Topic> topics1 = response.body().getData();
                                    for (Topic topic : topics1) {
                                        topics.add(topic);
                                    }
                                    selected = new boolean[topics.size()];
                                    selectedItems = new ArrayList<>();
                                    adapter = new SpinnerAdapter(AddTestActivity.this, topics, selectedItems, selected);
                                    spinner.setAdapter(adapter);
                                    Log.d("TAG", "LoadDataTopic: " + topics.toString());
                                }

                                @Override
                                public void onFailure(Call<TopicResponse> call, Throwable t) {
                                    Log.d("done", "fail");
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {

                    }
                });

    }

    private TestRequest createTest() {
        String testName = editTextNameTest.getText().toString();
        int time = Integer.parseInt(edtTime.getText().toString());
        int countQuestion = Integer.parseInt(edtCountQues.getText().toString());
        List<QuestionResponse> questionResponses = questionAdapter.questionsChecked();
        List<Long> questionIds = new ArrayList<>();
        for (QuestionResponse questionResponse : questionResponses) {
            questionIds.add(questionResponse.getQuestionId());
        }

        List<Long> topicIds = new ArrayList<>();
        for (Topic topic : selectedItems) {
            topicIds.add(topic.getId());
        }

        return new TestRequest(testName, countQuestion, time, questionIds, topicIds);
    }

}