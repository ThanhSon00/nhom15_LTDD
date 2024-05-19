package app.ntnt.finalprojectexamonline.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.UpdateAvatar;
import app.ntnt.finalprojectexamonline.activity.UpdateProfileActivity;
import app.ntnt.finalprojectexamonline.model.entites.User;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IUserService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.ContextUtil;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountFragment extends Fragment {
    View view;
    private TextView fullName, email, phoneNumber, gender;
    private Button btnUpdateProfile, btnLogout;
    CircleImageView avatar;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        view= inflater.inflate(R.layout.fragment_account, container, false);
        view= inflater.inflate(R.layout.activity_account, container, false);
        init();
        loadData();
       setEvent();
        return view;
    }
    private void setEvent(){

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateAvatar.class));
            }
        });
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("profile", (Serializable) user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    private void logout(){
        SharedPrefManager.getInstance(ContextUtil.context).logout();
        BaseAPIService.createService(IUserService.class).logout()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {

                    }
                });
    }
    @SuppressLint("SetTextI18n")
    private void renderData(User user){
        fullName.setText(user.getFirstName()+" "+user.getLastName());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        gender.setText(user.getGender());
        String avt = user.getAvatar();
        Glide.with(view).load(user.getAvatar()).placeholder(R.drawable.pic6).into(avatar);
    }

    private void loadData (){
        BaseAPIService.createService(IUserService.class).getProfile()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        if (response.body()!= null){
                            if (!response.body().isError()){
                                user = (User) AppConstrain.toObject(response.body().getData(), User.class);
                                renderData(user);
                            }
                        }
                        if (response.errorBody()!= null){
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {

                    }
                });
    }

    private void init(){
        fullName = view.findViewById(R.id.tvFullName);
        email = view.findViewById(R.id.tvEmail);
        phoneNumber = view.findViewById(R.id.tvPhoneNumber);
        gender = view.findViewById(R.id.tvGender);
        avatar = view.findViewById(R.id.avatar);
        btnLogout = view.findViewById(R.id.btnLogOut);
        btnUpdateProfile = view.findViewById(R.id.btnUpdate);
    }
}