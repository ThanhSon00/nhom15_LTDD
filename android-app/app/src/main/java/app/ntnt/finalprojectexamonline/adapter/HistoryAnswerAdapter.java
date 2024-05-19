package app.ntnt.finalprojectexamonline.adapter;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.model.response.AnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewAnswerResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionHistoryResponse;
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;


public class HistoryAnswerAdapter extends Adapter<HistoryAnswerAdapter.TopicViewHolder> {
    Context context;
    List<NewAnswerResponse> answers;
    List<NewAnswerResponse> filteredList;
    NewQuestionHistoryResponse newQuestionResponse;
    NewAnswerResponse newAnswerResponse;
//    private Long selectedAnswerPosition = -1L;
//    public Long getSelectedAnswerPosition() {
//        return selectedAnswerPosition;
//    }
//
//    public void setSelectedAnswerPosition(Long selectedAnswerPosition) {
//        this.selectedAnswerPosition = selectedAnswerPosition;
//    }

    public HistoryAnswerAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<NewAnswerResponse> answers, NewQuestionHistoryResponse newQuestionResponse,NewAnswerResponse newAnswerResponse) {
        this.answers = answers;
        this.filteredList = new ArrayList<>(answers);
        this.newQuestionResponse=newQuestionResponse;
        this.newAnswerResponse = newAnswerResponse;
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
        NewAnswerResponse answer = filteredList.get(position);
        if (answer == null) {
            return;
        }
        holder.tvNameAnswer.setText(answer.getContent());

        if (answer.isCorrect()) {
            holder.relativeLayout.setBackgroundResource(R.color.yellow);
        }
        else
        {
            holder.relativeLayout.setBackgroundResource(R.color.teal_200);
        }

        if(newAnswerResponse.getAnswerId()==answer.getAnswerId())
        {
            if (answer.isCorrect()) {
                holder.relativeLayout.setBackgroundResource(R.color.yellow);
            }
            else
                holder.relativeLayout.setBackgroundResource(R.color.red);
        }

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newQuestionResponse.setPositionChecked(answer.getAnswerId());
//                notifyDataSetChanged();
//            }
//        });
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
}
