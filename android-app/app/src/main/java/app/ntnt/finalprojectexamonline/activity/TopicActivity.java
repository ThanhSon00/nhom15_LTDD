package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.teacher.AddQuestionActivity;
import app.ntnt.finalprojectexamonline.activity.teacher.AddTopicActivity;
import app.ntnt.finalprojectexamonline.adapter.TopicAdapter;
import app.ntnt.finalprojectexamonline.model.entites.Subject;
import app.ntnt.finalprojectexamonline.model.entites.Topic;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.TopicResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.ISubjectService;
import app.ntnt.finalprojectexamonline.services.ITopicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopicActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TopicAdapter topicAdapter;
    List<Topic> topics;
    SearchView searchView;
    ImageView imageView;

    TextView textView;
    Long subjectId ;
    String nameSubject ;
    boolean bUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_activity);

        recyclerView = findViewById(R.id.rcv_topic);
        textView = findViewById(R.id.tv_list_topic);
        imageView = findViewById(R.id.img_addTopic);


        Bundle bundle = getIntent().getExtras();
        subjectId = (Long) bundle.getSerializable("subjectId");
        nameSubject = (String)bundle.getSerializable("nameSubject");
//         bundle.putSerializable("bUser", bUser);
        bUser = (boolean)bundle.getSerializable("bUser");
        textView.setText("Danh sách chủ đề môn "+nameSubject);
        setTopicAdapter(subjectId,bUser);
        Log.d("hehe", String.valueOf(bUser));
        searchView = (SearchView) findViewById(R.id.search_topic);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopicActivity.this, AddTopicActivity.class);
                //Intent intent = new Intent(TopicActivity.this, AddQuestionActivity.class);
                intent.putExtra("subjectId",subjectId);
                intent.putExtra("bUser",bUser);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                topicAdapter.filter(newText);
                return true;
            }
        });

    }



    private void setTopicAdapter(Long subjectId,boolean bUser) {
        topics = new ArrayList<>();
        topicAdapter = new TopicAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        BaseAPIService.createService(ITopicService.class).getTopicBySubjectId(subjectId,0,100).enqueue(new Callback<TopicResponse>() {
            @Override
            public void onResponse(Call<TopicResponse> call, Response<TopicResponse> response) {
                List<Topic> topics1 = response.body().getData();
                topicAdapter.setData(topics1,bUser);
                recyclerView.setAdapter(topicAdapter);

            }
            @Override
            public void onFailure(Call<TopicResponse> call, Throwable t) {
                Log.d("done", "fail");
            }
        });

    }





    public void showDialogDelete(long idTopic) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn xác nhận
                BaseAPIService.createService(ITopicService.class).deleteTopicById(idTopic).enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        Log.d("TAG", "onResponse: xóa thành công");
                        setTopicAdapter(subjectId,bUser);
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Log.d("TAG", "onResponse: sai");
                    }
                });
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}