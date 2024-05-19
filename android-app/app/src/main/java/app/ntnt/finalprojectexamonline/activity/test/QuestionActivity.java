package app.ntnt.finalprojectexamonline.activity.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.teacher.AddQuestionActivity;
import app.ntnt.finalprojectexamonline.adapter.QuestionAdapter;
import app.ntnt.finalprojectexamonline.adapter.TopicAdapter;
import app.ntnt.finalprojectexamonline.model.entites.Question;
import app.ntnt.finalprojectexamonline.model.entites.Topic;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IQuestionService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    List<QuestionResponse> questions;
    ImageView imageView;
    Long topicId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_teacher);

        recyclerView = findViewById(R.id.rcv_question);
        imageView = findViewById(R.id.img_addQuestion);

        Bundle bundle = getIntent().getExtras();
        topicId = bundle.getLong("topicId");

        Log.d("TAG", "onCreate: "+topicId);
        setQuestionAdapter(topicId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, AddQuestionActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putLong("topicId",topicId);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });


    }

    private void setQuestionAdapter(Long idTopic) {
        questionAdapter = new QuestionAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        BaseAPIService.createService(IQuestionService.class).getQuestionByTopicId(idTopic).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                //questions = (List<QuestionResponse>) response.body().getData();
                //questions = AppConstrain.objectList(response.body().getData(),QuestionResponse.class);
                questions = new ArrayList<>();
                if(response.body().getData()!=null)
                {
                    List<Object> objects;
                    objects = AppConstrain.objectList(response.body().getData(), QuestionResponse.class);
                    if(objects!=null)
                    {
                        for (Object i: objects){
                            questions.add((QuestionResponse) i);
                        }
                        questionAdapter.setData(questions);
                        recyclerView.setAdapter(questionAdapter);
                    }
                }



            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

            }
        });
    }


    public void updateData(Long questionId)
    {
        Log.d("TAG", "onClick: xóa câu hỏi");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn xóa ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseAPIService.createService(IQuestionService.class)
                        .deleteQuestionById(questionId)
                        .enqueue(new Callback<ResponseEntity>() {
                            @Override
                            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                                if(response.body().isError())
                                {
                                    Log.d("TAG", "onResponse: lỗi khi xóa");
                                }

                                setQuestionAdapter(topicId);

                                Log.d("TAG", "onResponse: thành công");

                            }

                            @Override
                            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                                Log.d("TAG", "onResponse: lỗi gọi api");
                            }
                        });
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Code xử lý khi người dùng chọn Hủy
                dialog.cancel();
            }
        });
        builder.show();


    }

}