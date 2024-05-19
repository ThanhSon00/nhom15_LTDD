package app.ntnt.finalprojectexamonline.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.test.AddTestActivity;
import app.ntnt.finalprojectexamonline.activity.test.AnswerActivity;
import app.ntnt.finalprojectexamonline.activity.test.QuestionActivity;
import app.ntnt.finalprojectexamonline.model.response.HighScoreResponse;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;


public class HighScoreAdapter extends Adapter<HighScoreAdapter.TopicViewHolder> {

    Context context;
    List<HighScoreResponse> highScoreResponses;


    public HighScoreAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<HighScoreResponse> highScoreResponses) {

        this.highScoreResponses = highScoreResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_high_score, parent, false);
            return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HighScoreResponse highScoreResponse = highScoreResponses.get(position);
        if (highScoreResponse == null) {
            return;
        }
        holder.tvStt.setText(String.valueOf(position+1));
        holder.tvName.setText(highScoreResponse.getFullName());
        holder.tvScore.setText(String.valueOf(highScoreResponse.getScore()));
    }


    @Override
    public int getItemCount() {
        if (highScoreResponses != null)
            return highScoreResponses.size();
        return 0;
    }

    public class TopicViewHolder extends ViewHolder {


        TextView tvStt, tvName,tvScore;


        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStt = itemView.findViewById(R.id.tv_highscore);
            tvName =itemView.findViewById(R.id.tv_name_high_score);
            tvScore =itemView.findViewById(R.id.tv_high_score);
        }
    }

}
