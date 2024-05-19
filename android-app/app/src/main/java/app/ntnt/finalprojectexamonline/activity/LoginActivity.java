package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import app.ntnt.finalprojectexamonline.activity.teacher.LoginTeacher;
import app.ntnt.finalprojectexamonline.databinding.ActivityLoginBinding;
import app.ntnt.finalprojectexamonline.model.request.LoginRequest;
import app.ntnt.finalprojectexamonline.model.response.AuthResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IAuthService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.ContextUtil;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private app.ntnt.finalprojectexamonline.databinding.ActivityLoginBinding binding;
    private Button btnLogin;
    private TextView tvSignup ,tvForgotPassword,tvLoginTeacher;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ContextUtil.context = getApplicationContext();
//        //Ánh xạ
        init();
        //Chuyển trang đăng kí
        tvLoginTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, LoginTeacher.class));
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //Xét sự kiện login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResetPassword.class));
            }
        });
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




                                if(authResponse.getRoles().get(0).toString().equals("ROLE_student"))
                                {
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Đăng thất bại vì không phải tài khoản học sinh", Toast.LENGTH_LONG).show();
                                }

                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                            }
                        }

                        else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập Thất bại", Toast.LENGTH_LONG).show();
                    }
                });
        progressBar.setVisibility(View.INVISIBLE);
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
    private LoginRequest getInputData(){
        String username = binding.edtUsername.getText().toString();
        String password = binding.edtPassword.getText().toString();
        return new LoginRequest(username, password);
    }

    private void init()
    {
        progressBar = binding.prbLogin;
        btnLogin = binding.btnLogin;
        tvSignup = binding.txtSignUp;
        tvForgotPassword= binding.txtForgotPassword;
        tvLoginTeacher= binding.tvLoginTeacher;
    }
}