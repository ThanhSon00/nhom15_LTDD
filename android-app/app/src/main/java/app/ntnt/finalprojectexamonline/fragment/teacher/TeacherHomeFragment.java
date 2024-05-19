package app.ntnt.finalprojectexamonline.fragment.teacher;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.teacher.AddSubjectActivity;
import app.ntnt.finalprojectexamonline.activity.teacher.HomeTeacherActivity;
import app.ntnt.finalprojectexamonline.adapter.SubjectAdpater;
import app.ntnt.finalprojectexamonline.fragment.UploadImageFragment;
import app.ntnt.finalprojectexamonline.model.entites.Subject;
import app.ntnt.finalprojectexamonline.model.entites.User;
import app.ntnt.finalprojectexamonline.model.response.DeleteResponse;
import app.ntnt.finalprojectexamonline.model.response.RespRegister;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.SubjectDataResponse;
import app.ntnt.finalprojectexamonline.model.response.SubjectResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.ISubjectService;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeacherHomeFragment extends Fragment implements UploadImageFragment.OnImageUploadedListener {
    private RecyclerView recyclerView;
    private SubjectAdpater subjectAdpater;
    private SearchView searchView;
    private List<Subject> subjects;
    private ImageView imageViewAdd;
    private Uri mUri;
    View view;
    Button buttonCancel;
    UploadImageFragment uploadImageFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_teacher, container, false);
       // Ánh xạ view
        init(view);
        loadData();

        //SharedPrefManager.getInstance(getContext()).getUser();
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chuyển sang activity addSubject
                Intent intent = new Intent(getContext(), AddSubjectActivity.class);
                startActivity(intent);

            }
        });

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

        return view;
    }

    private void loadData() {
        subjectAdpater = new SubjectAdpater(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        subjects = new ArrayList<>();
        List<Boolean> b = new ArrayList<>();
        BaseAPIService.createService(ISubjectService.class).getSubject().enqueue(new Callback<SubjectDataResponse>() {
            @Override
            public void onResponse(Call<SubjectDataResponse> call, Response<SubjectDataResponse> response) {
                SubjectDataResponse subjectDataResponse = response.body();
                List<SubjectResponse> subjectResponses = subjectDataResponse.getData();
                for (int i = 0; i < subjectResponses.size(); i++) {
                    SubjectResponse subjectResponse = subjectResponses.get(i);
                    Subject subjects1 = new Subject(subjectResponse.getSubjectId(), subjectResponse.getName(), subjectResponse.getImage());
                    subjects.add(subjects1);
                    List<User> users= subjectResponse.getUsers();
                    Long userId = SharedPrefManager.getInstance(getContext()).getUserId();
                    boolean b1=false;
                    for(User user:users)
                    {
                        if(user.getUserId()==userId)
                        {
                            b1=true;
                        }
                    }
                    b.add(b1);
                }
                subjectAdpater.setDataUserSubject(subjects,b);
                recyclerView.setAdapter(subjectAdpater);
            }

            @Override
            public void onFailure(Call<SubjectDataResponse> call, Throwable t) {
                Log.d("s", "lỗi");
            }
        });

    }

    private void init(View view)
    {
        recyclerView = view.findViewById(R.id.rcv_subject_home_teacher);
        searchView = view.findViewById(R.id.search_subject);
        imageViewAdd = view.findViewById(R.id.img_addSubject);
        searchView = view.findViewById(R.id.search_subject);
    }
    public void addSubject(String name)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                name);

        BaseAPIService.createService(ISubjectService.class).addSubject(requestBody).enqueue(new Callback<RespRegister>() {
            @Override
            public void onResponse(Call<RespRegister> call, Response<RespRegister> response) {
                Log.d("T","Thêm thành công");
                loadData();
            }

            @Override
            public void onFailure(Call<RespRegister> call, Throwable t) {
                Log.d("T","Thêm thất bại");
            }
        });
    }

    public void deleteSubject(Long subjectId)
    {
        BaseAPIService.createService(ISubjectService.class).delSubject(subjectId).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                String error =response.body().getError();
                if(error.equals("false"))
                    Log.d("TAG", "onResponse: Thành công");
                else
                    Log.d("TAG", response.body().getData().getMessage());
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.d("TAG", "onResponse: Thất bại");
            }
        });

    }

    @Override
    public void onImageUploaded(Uri uri) {
        mUri= uri;
    }
}