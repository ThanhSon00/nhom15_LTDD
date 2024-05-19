package app.ntnt.finalprojectexamonline.adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.fragment.QuestionFragment;
import app.ntnt.finalprojectexamonline.model.entites.Answer;
import app.ntnt.finalprojectexamonline.model.entites.Question;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;

public class LoadQuestionAdapter extends FragmentStateAdapter {
    List<List<AnswerResponse>> answers;
    //    List<QuestionResponse> questions;
    List<NewQuestionResponse> questions;

    public LoadQuestionAdapter(@NonNull FragmentActivity fragment, List<NewQuestionResponse> questions, List<List<AnswerResponse>> answers) {
        super(fragment);
        this.questions = questions;
        this.answers = answers;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (questions != null) {
            NewQuestionResponse question = questions.get(position);
            List<AnswerResponse> answers1 = answers.get(position);
            QuestionFragment questionFragment = new QuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("question_object", question);
            bundle.putParcelableArrayList("answers_object", (ArrayList<? extends Parcelable>) answers1);

            questionFragment.setArguments(bundle);
            return questionFragment;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (questions != null)
            return answers.size();
        return 0;
    }
}
