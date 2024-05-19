package app.ntnt.finalprojectexamonline.activity.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.LoadAnswerAdapter;
import app.ntnt.finalprojectexamonline.model.entites.Answer;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IQuestionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LoadAnswerAdapter answerAdapter;
    TextView textView;
    List<AnswerResponse> answerResponse;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        recyclerView= findViewById(R.id.rcv_answer);
        textView = findViewById(R.id.tv_load_name_question);
        Bundle bundle = getIntent().getExtras();
        answerResponse= bundle.getParcelableArrayList("question");
        String nameTest = bundle.getString("nameQuestion");
        textView.setText("Danh sách đáp án "+nameTest);
        setQuestionAdapter(answerResponse);

    }

    private void setQuestionAdapter(List<AnswerResponse> answerResponse)
    {
        answerAdapter = new LoadAnswerAdapter(this);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        answerAdapter.setData(answerResponse);
        recyclerView.setAdapter(answerAdapter);

    }

}