package app.ntnt.finalprojectexamonline.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.HighScoreActivity;
import app.ntnt.finalprojectexamonline.activity.LoginActivity;
import app.ntnt.finalprojectexamonline.activity.TestInfoActivity;
import app.ntnt.finalprojectexamonline.activity.test.LoadQuestionActivity;
import app.ntnt.finalprojectexamonline.fragment.HomeFragment;

import app.ntnt.finalprojectexamonline.model.TestInfor;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;

import app.ntnt.finalprojectexamonline.model.response.TestResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IStatisticService;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestInforAdapter extends Adapter<TestInforAdapter.TopicViewHolder> {
    private HomeFragment context;
    private TestInfoActivity testInforActivity;
    List<TestResponse> testInforList;
    private boolean b;

    public TestInforAdapter(HomeFragment context) {
        this.context = context;
        this.b=true;
    }

    public TestInforAdapter(TestInfoActivity testInforActivity) {
        this.testInforActivity = testInforActivity;
        this.b=false;
    }

    public void setData(List<TestResponse> testInforList) {

        this.testInforList = testInforList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        TestResponse test = testInforList.get(position);
        if (test == null) {
            return;
        }
        holder.tvName.setText(test.getTestName());
//        holder.tvNameAuthor.setText(test.getNameAuthor());

        String str = test.getDateCreate();
        String first20Chars = str.substring(0, Math.min(str.length(), 10));
        holder.tvDateCreated.setText(first20Chars);
        holder.tvTime.setText(String.valueOf(test.getTime())+ " phút");
//        holder.tvNameSubject.setText(test.getTestName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b==true)
                {
                    Intent intent = new Intent(context.getContext(), LoadQuestionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("testId", test.getTestId());
                    intent.putExtras(bundle);
                    startActivity(context.getContext(),intent,bundle);
                }
               else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(testInforActivity);
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Mời bạn chọn lựa chọn bên dưới?");
                    builder.setPositiveButton("Thực hiện bài thi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(SharedPrefManager.getInstance(testInforActivity).getUserId()!=null)
                            {
                                Intent intent = new Intent(testInforActivity, LoadQuestionActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putLong("testId", test.getTestId());
                                intent.putExtras(bundle);
//                            intent.putExtra("test",test);
                                startActivity(testInforActivity,intent,bundle);
                            }
                            else {
                                Intent intent = new Intent(testInforActivity, LoginActivity.class);
                                Bundle bundle = new Bundle();
                                startActivity(testInforActivity,intent,bundle);
                            }

                        }
                    });
                    builder.setNegativeButton("Xem điểm cao", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(testInforActivity, HighScoreActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putLong("testId", test.getTestId());
                            bundle.putString("testName", test.getTestName());
                            intent.putExtras(bundle);
                            startActivity(testInforActivity,intent,bundle);

//                            BaseAPIService.createService(IStatisticService.class).getHighScore(test.getTestId()).enqueue(new Callback<ResponseEntity>() {
//                                @Override
//                                public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
//                                    Log.d("TAG", "onResponse: success");
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseEntity> call, Throwable t) {
//                                    Log.d("TAG", "onResponse: failed");
//                                }
//                            });

                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

//                    Intent intent = new Intent(testInforActivity, LoadQuestionActivity.class);
//                    Bundle bundle = new Bundle();
//                    startActivity(testInforActivity,intent,bundle);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (testInforList != null)
            return testInforList.size();
        return 0;
    }

    public class TopicViewHolder extends ViewHolder {

         TextView tvName,tvTime,tvNameSubject,tvNameAuthor,tvDateCreated;
         //ProgressBar progressBar;
        RelativeLayout relativeLayout;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_nameExam_test);
            tvTime = itemView.findViewById(R.id.tv_time_test);;
            tvDateCreated = itemView.findViewById(R.id.tv_datecreated);
            relativeLayout =itemView.findViewById(R.id.rlt_item_test);


        }
    }
}
