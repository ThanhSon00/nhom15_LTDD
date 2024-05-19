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
import app.ntnt.finalprojectexamonline.model.response.NewQuestionResponse;


public class LoadAnswerAdapter extends Adapter<LoadAnswerAdapter.TopicViewHolder> {
    Context context;
    List<AnswerResponse> answers;
    List<AnswerResponse> filteredList;


    public LoadAnswerAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<AnswerResponse> answers) {
        this.answers = answers;
        this.filteredList = new ArrayList<>(answers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delete_anwser, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AnswerResponse answer = filteredList.get(position);
        if (answer == null) {
            return;
        }
        holder.tvNameAnswer.setText(answer.getContent());


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

            tvNameAnswer = itemView.findViewById(R.id.tv_load_answer_content);
            relativeLayout= itemView.findViewById(R.id.rlt_load_answer_adapter);

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
