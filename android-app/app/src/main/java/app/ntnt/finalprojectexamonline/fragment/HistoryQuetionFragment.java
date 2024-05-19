package app.ntnt.finalprojectexamonline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.AnswerAdapter;
import app.ntnt.finalprojectexamonline.adapter.HistoryAnswerAdapter;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewAnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionHistoryResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;

public class HistoryQuetionFragment extends Fragment {
    private View view;
    private TextView textView;

    private RecyclerView recyclerView;
    private HistoryAnswerAdapter answerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_question, container, false);
        textView= view.findViewById(R.id.txt_ques);
        recyclerView = view.findViewById(R.id.rcv_anwser);

        answerAdapter = new HistoryAnswerAdapter(getContext());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<NewAnswerResponse> list = new ArrayList<NewAnswerResponse>();
        Bundle bundle = getArguments();
        if(bundle !=null)
        {
            NewQuestionHistoryResponse question = (NewQuestionHistoryResponse) bundle.getParcelable("question_object");
//            answerAdapter.setSelectedAnswerPosition(question.getPositionChecked());
//            answerAdapter.notifyDataSetChanged();
            list = bundle.getParcelableArrayList("answers_object");
            if(question!=null)
            {
                textView.setText(question.getQuestion());
            }
            NewAnswerResponse newAnswerResponse = bundle.getParcelable("selected_answer");
            answerAdapter.setData(list,question,newAnswerResponse);
        }
        recyclerView.setAdapter(answerAdapter);
        return view;
    }
}
