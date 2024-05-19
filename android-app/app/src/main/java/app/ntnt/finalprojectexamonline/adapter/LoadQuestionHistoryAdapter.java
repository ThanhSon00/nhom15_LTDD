package app.ntnt.finalprojectexamonline.adapter;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.fragment.HistoryQuetionFragment;
import app.ntnt.finalprojectexamonline.fragment.QuestionFragment;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewAnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionHistoryResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;

public class LoadQuestionHistoryAdapter extends FragmentStateAdapter {
    //    List<List<AnswerResponse>> answers;
//    List<NewQuestionResponse> questions;
    List<List<NewAnswerResponse>> listAnswer;
    List<NewQuestionHistoryResponse> listQuestion;
    List<NewAnswerResponse> answerSelected= new ArrayList<>();

    public LoadQuestionHistoryAdapter(@NonNull FragmentActivity fragment, List<NewQuestionHistoryResponse> questions,
                                      List<List<NewAnswerResponse>> answers,
                                      List<NewAnswerResponse> answerSelected) {
        super(fragment);
        this.listQuestion = questions;
        this.listAnswer = answers;
        this.answerSelected= answerSelected;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (listQuestion != null) {
            NewQuestionHistoryResponse question = listQuestion.get(position);
            List<NewAnswerResponse> answers1 = listAnswer.get(position);
            NewAnswerResponse selectedAnswer = answerSelected.get(position);

            HistoryQuetionFragment questionFragment = new HistoryQuetionFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable("question_object", (Parcelable) question);
            bundle.putParcelableArrayList("answers_object", (ArrayList<? extends Parcelable>) answers1);
            bundle.putParcelable("selected_answer", (Parcelable) selectedAnswer);

            questionFragment.setArguments(bundle);
            return questionFragment;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (listQuestion != null)
            return listQuestion.size();
        return 0;
    }
}
