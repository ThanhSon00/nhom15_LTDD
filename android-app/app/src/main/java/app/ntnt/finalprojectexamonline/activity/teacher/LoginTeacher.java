package app.ntnt.finalprojectexamonline.activity.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.HomeActivity;
import app.ntnt.finalprojectexamonline.activity.LoginActivity;
import app.ntnt.finalprojectexamonline.databinding.ActivityLoginBinding;
import app.ntnt.finalprojectexamonline.databinding.ActivityLoginTeacherBinding;
import app.ntnt.finalprojectexamonline.model.request.LoginRequest;
import app.ntnt.finalprojectexamonline.model.response.AuthResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IAuthService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTeacher extends AppCompatActivity {
    private ActivityLoginTeacherBinding binding;
    private Button btnLogin;
    private EditText edtUsername ,edtPassword;
    private ProgressBar progressBar;
    private TextView tvLoginStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvLoginStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginTeacher.this,LoginActivity.class));
            }
        });
    }
    private boolean validated(){
        if(binding.edtPassword.getText().toString().trim().isEmpty()){
            binding.edtPassword.setError("Vui lòng nhập password");
            return false;
        }
        if(binding.edtUsername.getText().toString().trim().isEmpty()){
            binding.edtUsername.setError("Vui lòng nhập username");
            return false;
        }
        else {
            return true;
        }
    }
    private void login(){

        if(!validated()){
            return;
        }
        progressBar.setVisibility(binding.getRoot().getVisibility());
        LoginRequest loginRequest = getInputData();
        BaseAPIService.createService(IAuthService.class).login(loginRequest)
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        if (response.body()!=null){
                            AuthResponse authResponse = null;
                            if (!response.body().isError()){
                                //Lấy dữ liệu từ response
                                authResponse = (AuthResponse) AppConstrain.toObject(response.body().getData(), AuthResponse.class);
                                SharedPrefManager.getInstance(getApplicationContext()).saveAuthToken(authResponse);
//                              SharedPrefManager.getInstance(getApplicationContext()).saveUser(authResponse.getUsername());
                                SharedPrefManager.getInstance(getApplicationContext()).saveUser(authResponse.getUserId());
                                Log.d("TAG", "onResponse: "+SharedPrefManager.getInstance(getApplicationContext()).getUserId());

                                if(authResponse.getRoles().get(0).toString().equals("ROLE_teacher"))
                                {
                                    Toast.makeText(LoginTeacher.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginTeacher.this, HomeTeacherActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(LoginTeacher.this, "Đăng nhập thất bại vì không phải tài khoản giáo viên", Toast.LENGTH_LONG).show();
                                }

                            }
                            else {
                                Toast.makeText(LoginTeacher.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                            }
                        }

                        else {
                            Toast.makeText(LoginTeacher.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Toast.makeText(LoginTeacher.this, "Đăng nhập Thất bại", Toast.LENGTH_LONG).show();
                    }
                });
        progressBar.setVisibility(View.INVISIBLE);
    }
    private LoginRequest getInputData(){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        return new LoginRequest(username, password);
    }
    void init()
    {
        edtUsername = binding.edtUsername;
        edtPassword = binding.edtPassword;
        btnLogin = binding.btnLogin;
        progressBar = binding.prbLogin;
        tvLoginStudent = binding.txtSignUp;
    }
}