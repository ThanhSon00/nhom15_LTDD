package app.ntnt.finalprojectexamonline.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.test.LoadHistoryQuestionActivity;
import app.ntnt.finalprojectexamonline.fragment.HistoryFragment;
import app.ntnt.finalprojectexamonline.model.entites.Test;
import app.ntnt.finalprojectexamonline.model.response.HistoryInUserResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.TestResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.ITestService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestAdapter extends Adapter<TestAdapter.TopicViewHolder> {
    private HistoryFragment context;
    List<HistoryInUserResponse> tests;
    TestResponse testResponse = new TestResponse();

    public TestAdapter(HistoryFragment context) {
        this.context = context;
    }

    public void setData(List<HistoryInUserResponse> tests) {

        this.tests = tests;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        HistoryInUserResponse test = tests.get(position);
        if (test == null) {
            return;
        }
        float percent = (test.getScore() * 10);
        holder.tvPercent.setText(String.valueOf(percent) + " %");

        holder.tvName.setText(test.getTestName());
        holder.tvTime.setText(String.valueOf(test.getTimeInTest()) + " phút");
        holder.tvDateComplete.setText(test.getTime());

//        BaseAPIService.createService(ITestService.class).getTestById(test.getTestId()).enqueue(new Callback<ResponseEntity>() {
//            @Override
//            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
//
//                if(response.body().isError()==false) {
//
//                    Object object;
//                    object = AppConstrain.toObject(response.body().getData(), TestResponse.class);
//
//                    testResponse = (TestResponse) object;
//                    if (testResponse != null) {
//                        holder.tvName.setText(testResponse.getTestName());
//                        holder.tvTime.setText(String.valueOf(testResponse.getTime()) + " phút");
//                        holder.tvDateComplete.setText(testResponse.getDateCreate());
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseEntity> call, Throwable t) {
//                Log.d("TAG", "onResponse: failed");
//            }
//        });
        holder.progressBar.setProgress((int) percent);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getContext(), LoadHistoryQuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("status",test.getStatus());
                bundle.putString("nameTest",test.getTestName());
                bundle.putLong("hisItem",test.getHisId());
                bundle.putLong("testId",test.getTestId());
                intent.putExtras(bundle);
                startActivity(context.getContext(), intent, bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (tests != null)
            return tests.size();
        return 0;
    }

    public class TopicViewHolder extends ViewHolder {

        TextView tvName, tvTime, tvPercent, tvDateComplete;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_nameExam);
            tvTime = itemView.findViewById(R.id.tv_time);

            tvPercent = itemView.findViewById(R.id.txt_percent);
            tvDateComplete = itemView.findViewById(R.id.txt_date_complete_history);

            progressBar = itemView.findViewById(R.id.pbr_complete);
            linearLayout = itemView.findViewById(R.id.llt_item_history);

        }
    }
}
