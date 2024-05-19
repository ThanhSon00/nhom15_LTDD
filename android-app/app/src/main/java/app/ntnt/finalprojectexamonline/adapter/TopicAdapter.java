package app.ntnt.finalprojectexamonline.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.LoadTopicData;
import app.ntnt.finalprojectexamonline.activity.TestInfoActivity;
import app.ntnt.finalprojectexamonline.activity.TopicActivity;
import app.ntnt.finalprojectexamonline.activity.test.QuestionActivity;
import app.ntnt.finalprojectexamonline.model.entites.Topic;


public class TopicAdapter extends Adapter<TopicAdapter.TopicViewHolder> {
    private TopicActivity context;
    private LoadTopicData loadTopicData;
    List<Topic> topics;
    List<Topic> filteredList;
    boolean b;
    boolean bUser;

    public TopicAdapter(TopicActivity context) {
        this.context = context;
        this.b = true;
    }

    public TopicAdapter(LoadTopicData loadTopicData) {
        this.loadTopicData = loadTopicData;
        this.b = false;
    }

    public void setData(List<Topic> topics) {

        this.topics = topics;
        this.filteredList = new ArrayList<>(topics);
        notifyDataSetChanged();
    }

    public void setData(List<Topic> topics, boolean bUser) {

        this.topics = topics;
        this.filteredList = new ArrayList<>(topics);
        this.bUser = bUser;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_teacher, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = filteredList.get(position);
        if (topic == null) {
            return;
        }

        holder.tvNameTopic.setText(topic.getName());
//        holder.imageViewTopic.setImageResource(R.drawable.pic6);
        if (b == true) {
            Glide.with(context).load(topic.getImage()).into(holder.imageViewTopic);
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QuestionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("topicId", topic.getId());
                    intent.putExtras(bundle);
                    startActivity(context, intent, bundle);
                }
            });


            if (bUser == false) {
                holder.imgEdit.setVisibility(View.GONE);
                holder.imgDelete.setVisibility(View.GONE);
            }
            else {
                holder.imgEdit.setVisibility(View.GONE);
                holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.showDialogDelete(topic.getId());
                    }
                });
            }


        } else {
            Glide.with(loadTopicData).load(topic.getImage()).into(holder.imageViewTopic);
            holder.imgEdit.setVisibility(View.GONE);
            holder.imgDelete.setVisibility(View.GONE);
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(loadTopicData, TestInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("topicId", topic.getId());
                    bundle.putString("nameTopic", topic.getName());
                    intent.putExtras(bundle);
                    startActivity(loadTopicData, intent, bundle);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        if (filteredList != null)
            return filteredList.size();
        return 0;
    }

    public class TopicViewHolder extends ViewHolder {

//        ImageView imageViewTopic,imgEdit,imgDelete;
//        TextView tvNameTopic;
//        RelativeLayout relativeLayout;

        ImageView imageViewTopic, imgEdit, imgDelete;
        TextView tvNameTopic;
        RelativeLayout relativeLayout;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTopic = itemView.findViewById(R.id.tv_subject_teacher);
            imageViewTopic = itemView.findViewById(R.id.img_subject_teacher);
            relativeLayout = itemView.findViewById(R.id.rlt_subject_teacher);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgEdit = itemView.findViewById(R.id.img_edit);

        }
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(topics);
        } else {
            for (Topic item : topics) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
