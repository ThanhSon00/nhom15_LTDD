package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.HighScoreAdapter;
import app.ntnt.finalprojectexamonline.adapter.TopicAdapter;
import app.ntnt.finalprojectexamonline.databinding.ActivityHighScoreBinding;
import app.ntnt.finalprojectexamonline.model.response.HighScoreResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.TestResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IStatisticService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HighScoreActivity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    HighScoreAdapter highScoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Bundle bundle = getIntent().getExtras();
        Long testId = bundle.getLong("testId");
        String nameTest = bundle.getString("testName");
        Log.d("TAG", "onCreate: "+testId +""+nameTest);

        init();
        textView.setText("Danh sách thí sinh thực hiện bài thi "+nameTest);


        highScoreAdapter = new HighScoreAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        BaseAPIService.createService(IStatisticService.class).getHighScore(testId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                List<Object> objects;
                objects = AppConstrain.objectList(response.body().getData(), HighScoreResponse.class);
                List<HighScoreResponse> highScoreResponses = new ArrayList<>();
                for (Object i: objects){
                    highScoreResponses.add((HighScoreResponse) i);
                }
                highScoreAdapter.setData(highScoreResponses);
                recyclerView.setAdapter(highScoreAdapter);
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

            }
        });
    }
    private void init()
    {
        textView = findViewById(R.id.tv_name_test_high_score);
        recyclerView= findViewById(R.id.rcv_load_high_score);
    }
}