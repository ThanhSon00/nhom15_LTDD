package app.ntnt.finalprojectexamonline.activity.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.SubjectAdpater;
import app.ntnt.finalprojectexamonline.model.entites.Subject;

public class HomeTeacherActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SubjectAdpater subjectAdpater;
    private SearchView searchView;
    private List<Subject> subjects;

    private ImageView imageViewAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_teacher);

        recyclerView = findViewById(R.id.rcv_subject_home_teacher);
        searchView = findViewById(R.id.search_subject);
        imageViewAdd = findViewById(R.id.img_addSubject);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddSubject();
            }
        });
       // loadDataSubject();
        loadData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subjectAdpater.filter(newText);
                return true;
            }
        });

    }
    public void showDialogAddSubject() {
        Dialog dialog = new Dialog(HomeTeacherActivity.this);
        dialog.setContentView(R.layout.dialog_add_subject);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        Button btnOk = dialog.findViewById(R.id.dialog_btn_Ok);
        Button btnCancel = dialog.findViewById(R.id.dialog_btn_Cancel);


//        imageView = dialog.findViewById(R.id.img_dialog_imgSubject);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckPermission();
//            }
//        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi API update môn học
            }
        });
        dialog.show();
    }
    public void showDialogUpdate() {
        Dialog dialog = new Dialog(HomeTeacherActivity.this);
        dialog.setContentView(R.layout.dialog_add_subject);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        Button btnOk = dialog.findViewById(R.id.dialog_btn_Ok);
        Button btnCancel = dialog.findViewById(R.id.dialog_btn_Cancel);


//        imageView = dialog.findViewById(R.id.img_dialog_imgSubject);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckPermission();
//            }
//        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi API update môn học
            }
        });
        dialog.show();
    }

    public void showDialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn xác nhận
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn hủy
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private void loadData() {
//        subjectAdpater = new SubjectAdpater(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeTeacherActivity.this, 1, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        subjects = new ArrayList<>();
//        for(int i=1;i<10;i++)
//        {
//            subjects.add(new Subject(1L,"Toán",null));
//        }
//        subjectAdpater.setData(subjects);
//        recyclerView.setAdapter(subjectAdpater);
////        BaseAPIService.createService(ISubjectService.class).getAllSubject()
////                .enqueue(new Callback<List<Subject>>() {
////                    @Override
////                    public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
////                        if (response.body() != null) {
////                            subjectAdpater.setData(response.body());
////                            recyclerView.setAdapter(subjectAdpater);
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<List<Subject>> call, Throwable t) {
////                        Log.d("erroeee rồi", t.getMessage().toString());
////                    }
////                });
    }
}