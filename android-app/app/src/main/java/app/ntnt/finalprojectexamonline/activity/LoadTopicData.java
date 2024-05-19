package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.TopicAdapter;
import app.ntnt.finalprojectexamonline.model.entites.Topic;
import app.ntnt.finalprojectexamonline.model.response.TopicResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.ITopicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadTopicData extends AppCompatActivity {

    RecyclerView recyclerView;
    TopicAdapter topicAdapter;
    List<Topic> topics;
    SearchView searchView;
    ImageView imageView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_activity);

        recyclerView = findViewById(R.id.rcv_topic);
        textView = findViewById(R.id.tv_list_topic);
        imageView = findViewById(R.id.img_addTopic);

        imageView.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();
        Long subjectId = (Long) bundle.getSerializable("subjectId");
        String nameSubject = (String)bundle.getSerializable("nameSubject");
        textView.setText("Danh sách chủ đề "+nameSubject);

        setTopicAdapter(subjectId);
    }

    private void setTopicAdapter(Long subjectId) {
        topics = new ArrayList<>();
        topicAdapter = new TopicAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        BaseAPIService.createService(ITopicService.class).getTopicBySubjectId(subjectId,0,100).enqueue(new Callback<TopicResponse>() {
            @Override
            public void onResponse(Call<TopicResponse> call, Response<TopicResponse> response) {
                List<Topic> topics1 = response.body().getData();
                topicAdapter.setData(topics1);
                recyclerView.setAdapter(topicAdapter);

            }
            @Override
            public void onFailure(Call<TopicResponse> call, Throwable t) {
                Log.d("done", "fail");
            }
        });

    }
}