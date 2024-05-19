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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.LoadTopicData;
import app.ntnt.finalprojectexamonline.activity.TopicActivity;
import app.ntnt.finalprojectexamonline.activity.test.HomeTeacherActivity;
import app.ntnt.finalprojectexamonline.fragment.HomeFragment;
import app.ntnt.finalprojectexamonline.fragment.teacher.TeacherHomeFragment;
import app.ntnt.finalprojectexamonline.model.entites.Subject;


public class SubjectAdpater extends Adapter<SubjectAdpater.TopicViewHolder> {
    private HomeFragment context;

    //private HomeTeacherActivity homeTeacherContext;

    private TeacherHomeFragment teacherHomeFragment;
    private Boolean b;
    List<Subject> subjects;
    List<Subject> filteredList;
    List<Boolean> booleans;

    public SubjectAdpater(HomeFragment context) {
        this.context = context;
        this.b = true;
    }

    //mới thêm
    public SubjectAdpater(TeacherHomeFragment teacherHomeFragment) {
        this.teacherHomeFragment = teacherHomeFragment;
        this.b = false;
    }

    public void setDataUserSubject(List<Subject> subjects, List<Boolean> booleans) {

        this.subjects = subjects;
        this.booleans = booleans;
        this.filteredList = new ArrayList<>(subjects);
        notifyDataSetChanged();
    }

    public void setData(List<Subject> subjects) {

        this.subjects = subjects;
        this.filteredList = new ArrayList<>(subjects);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (b == true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
            return new TopicViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_teacher, parent, false);
            return new TopicViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Subject subject = filteredList.get(position);



        if (subject == null) {
            return;
        }


        holder.tvNameSubject.setText(subject.getName());

        if(b==true)
        {
            Glide.with(context.getContext()).load(subject.getImage()).placeholder(R.drawable.pic6).into(holder.imageViewSubject);
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(context.getContext(), LoadTopicData.class);
                    bundle.putSerializable("subjectId", subject.getSubjectId());
                    bundle.putSerializable("nameSubject", subject.getName());
                    intent.putExtras(bundle);
                    startActivity(context.getContext(), intent, bundle);
                }
            });
        }

        if (b == false) {
            Boolean bUser = booleans.get(position);
            Glide.with(teacherHomeFragment.getContext()).load(subject.getImage()).into(holder.imageViewSubject);

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(teacherHomeFragment.getContext(), TopicActivity.class);
                    bundle.putSerializable("subjectId", subject.getSubjectId());
                    bundle.putSerializable("nameSubject", subject.getName());
                    bundle.putSerializable("bUser", bUser);
                    intent.putExtras(bundle);
                    startActivity(teacherHomeFragment.getContext(), intent, bundle);

                }
            });

            if (checkAllFalse(booleans)) {
                holder.imgDelete.setVisibility(View.INVISIBLE);
                holder.imgEdit.setVisibility(View.VISIBLE);
                holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Gọi API thêm

                        AlertDialog.Builder builder = new AlertDialog.Builder(teacherHomeFragment.getContext());
                        builder.setTitle("Bạn đang chọn môn" + subject.getName());
                        builder.setMessage("Bạn có chắn chắn không?");
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                teacherHomeFragment.addSubject(subject.getName());
                            }
                        });
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Thực hiện hành động khi người dùng nhấp vào nút "Không"
                                dialog.dismiss();
                            }
                        });
                        builder.show();

                        //teacherHomeFragment.addSubject(subject.getName());
                    }
                });
            } else {
                if (bUser == false) {
                    holder.imgDelete.setVisibility(View.INVISIBLE);
                    holder.imgEdit.setVisibility(View.INVISIBLE);
                } else if (bUser == true) {
//                    holder.imgDelete.setVisibility(View.VISIBLE);
                    holder.imgEdit.setVisibility(View.INVISIBLE);
                    holder.imgDelete.setVisibility(View.INVISIBLE);
                    holder.relativeLayout.setBackgroundResource(R.color.yellow);


//                    holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(teacherHomeFragment.getContext());
//                            builder.setTitle("Bạn đang chọn môn" + subject.getName());
//                            builder.setMessage("Bạn có chắn chắn không?");
//                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    teacherHomeFragment.deleteSubject(subject.getSubjectId());
//                                }
//                            });
//                            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Thực hiện hành động khi người dùng nhấp vào nút "Không"
//                                    dialog.dismiss();
//                                }
//                            });
//                            builder.show();
//                        }
//                    });
//

                }
            }
//            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                        Intent intent = new Intent(teacherHomeFragment.getContext(), TopicActivity.class);
////                        Bundle bundle = new Bundle();
////                        startActivity(teacherHomeFragment.getContext(), intent, bundle);
//
//
//                    Bundle bundle = new Bundle();
//                    Intent intent = new Intent(teacherHomeFragment.getContext(), TopicActivity.class);
//                    bundle.putSerializable("subjectId", subject.getSubjectId());
//                    intent.putExtras(bundle);
//                    startActivity(teacherHomeFragment.getContext(), intent, bundle);
//                }
//            });
        }


    }


    @Override
    public int getItemCount() {
        if (filteredList != null)
            return filteredList.size();
        return 0;
    }

    public class TopicViewHolder extends ViewHolder {

        ImageView imageViewSubject, imgEdit, imgDelete;
        TextView tvNameSubject;
        RelativeLayout relativeLayout;


        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            if (b == true) {
                tvNameSubject = itemView.findViewById(R.id.tv_name_subject);
                imageViewSubject = itemView.findViewById(R.id.img_subject);
                relativeLayout = itemView.findViewById(R.id.rlt_subject);
            } else {
                tvNameSubject = itemView.findViewById(R.id.tv_subject_teacher);
                imageViewSubject = itemView.findViewById(R.id.img_subject_teacher);
                relativeLayout = itemView.findViewById(R.id.rlt_subject_teacher);
                imgDelete = itemView.findViewById(R.id.img_delete);
                imgEdit = itemView.findViewById(R.id.img_edit);
            }

        }
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(subjects);
        } else {
            for (Subject item : subjects) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    private boolean checkAllFalse(List<Boolean> booleans) {
        for (boolean b : booleans) {
            if (b) {
                return false;
            }
        }
        return true;
    }
}
