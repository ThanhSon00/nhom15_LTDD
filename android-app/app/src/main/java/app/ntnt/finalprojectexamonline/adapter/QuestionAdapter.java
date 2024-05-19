package app.ntnt.finalprojectexamonline.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.test.AddTestActivity;
import app.ntnt.finalprojectexamonline.activity.test.AnswerActivity;
import app.ntnt.finalprojectexamonline.activity.test.QuestionActivity;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IQuestionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionAdapter extends Adapter<QuestionAdapter.TopicViewHolder> {
    private AddTestActivity context;
    QuestionActivity contextQues;
    List<QuestionResponse> questions;
    List<QuestionResponse> filteredList;


    List<QuestionResponse> selectedQuestion;
    boolean b;

    public QuestionAdapter(QuestionActivity contextQues) {
        this.contextQues = contextQues;
        notifyDataSetChanged();
        this.b = true;
    }

    public QuestionAdapter(AddTestActivity context) {
        this.context = context;
        this.selectedQuestion = new ArrayList<>();
        this.b = false;
        notifyDataSetChanged();
    }

    public void setData(List<QuestionResponse> questions) {

        this.questions = questions;
        this.filteredList = new ArrayList<>(questions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (b == true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_teacher, parent, false);
            return new TopicViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_for_add, parent, false);
            return new TopicViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        QuestionResponse question = filteredList.get(position);
        if (question == null) {
            return;
        }
        holder.tvIdQuestion.setText(String.valueOf(question.getQuestionId()));
        holder.tvNameQuestion.setText(question.getQuestion());
        if (b == false) {

            Glide.with(context).load(question.getImage()).into(holder.imgQuestion);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedQuestion.add(question);
                    } else {
                        if (selectedQuestion.contains(question)) {
                            selectedQuestion.remove(question);
                        }
                    }

                }
            });
        }
        if (b == true) {
            Glide.with(contextQues).load(question.getImage()).into(holder.imgQuestion);
            holder.imgEdit.setVisibility(View.GONE);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contextQues.updateData(question.getQuestionId());
                }
            });

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(contextQues, AnswerActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("question", (ArrayList<? extends Parcelable>) question.getAnswers());
                        bundle.putString("nameQuestion",question.getQuestion());
                        intent.putExtras(bundle);
                        startActivity(contextQues, intent, bundle);
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

        ImageView imgQuestion, imgEdit, imgDelete;
        TextView tvIdQuestion, tvNameQuestion;
        RelativeLayout relativeLayout;
        CheckBox checkBox;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            if (b == true) {
                imgQuestion = itemView.findViewById(R.id.img_question_teacher);
                tvNameQuestion = itemView.findViewById(R.id.tv_name_question);
                tvIdQuestion = itemView.findViewById(R.id.tv_id_question);
                relativeLayout = itemView.findViewById(R.id.rlt_question_teacher);
                imgEdit = itemView.findViewById(R.id.img_edit_topic);
                imgDelete = itemView.findViewById(R.id.img_delete_question);
            } else {
                imgQuestion = itemView.findViewById(R.id.img_question_for_add);
                tvNameQuestion = itemView.findViewById(R.id.tv_name_question_for_add);
                tvIdQuestion = itemView.findViewById(R.id.tv_id_question_for_add);
                relativeLayout = itemView.findViewById(R.id.rlt_question_for_add);
                checkBox = itemView.findViewById(R.id.checkBox2);
            }


        }
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(questions);
        } else {
            for (QuestionResponse item : questions) {
                if (item.getQuestion().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<QuestionResponse> questionsChecked() {
//        Log.d("TAG", "questionsChecked: " + selectedQuestion.toString());
        return selectedQuestion;
    }
}
