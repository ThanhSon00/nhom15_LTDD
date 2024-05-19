package app.ntnt.finalprojectexamonline.adapter;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.test.AnswerActivity;
import app.ntnt.finalprojectexamonline.activity.test.QuestionActivity;
import app.ntnt.finalprojectexamonline.fragment.QuestionFragment;
import app.ntnt.finalprojectexamonline.model.entites.Answer;
import app.ntnt.finalprojectexamonline.model.entites.Question;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;


public class AnswerAdapter extends Adapter<AnswerAdapter.TopicViewHolder> {
    Context context;
    List<AnswerResponse> answers;
    List<AnswerResponse> filteredList;
    NewQuestionResponse newQuestionResponse;
    private Long selectedAnswerPosition = -1L;
    public Long getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public void setSelectedAnswerPosition(Long selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public AnswerAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<AnswerResponse> answers, NewQuestionResponse newQuestionResponse) {
        this.answers = answers;
        this.filteredList = new ArrayList<>(answers);
        this.newQuestionResponse=newQuestionResponse;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anwser, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AnswerResponse answer = filteredList.get(position);
        if (answer == null) {
            return;
        }
        holder.tvNameAnswer.setText(answer.getContent());

        if (answer.getAnswerId() == newQuestionResponse.getPositionChecked()) {
            holder.relativeLayout.setBackgroundResource(R.color.yellow);
        } else {
            holder.relativeLayout.setBackgroundResource(R.color.teal_200);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newQuestionResponse.setPositionChecked(answer.getAnswerId());
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (filteredList != null)
            return filteredList.size();
        return 0;
    }

    public class TopicViewHolder extends ViewHolder {

        TextView tvNameAnswer;
        RelativeLayout relativeLayout;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameAnswer = itemView.findViewById(R.id.tv_answer_content);
            relativeLayout= itemView.findViewById(R.id.rlt_load_answer);

        }
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(answers);
        } else {
            for (AnswerResponse item : answers) {
                if (item.getContent().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
