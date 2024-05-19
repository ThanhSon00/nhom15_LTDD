package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.databinding.ActivityLoginBinding;
import app.ntnt.finalprojectexamonline.databinding.ActivityResetPasswordBinding;
import app.ntnt.finalprojectexamonline.model.request.ResetPasswordRequest;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IAuthService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {

    ActivityResetPasswordBinding binding;
    EditText edtUsername, edtPassword, edtPhoneNumber,edtEmail;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }

    private void resetPassword()
    {
        BaseAPIService.createService(IAuthService.class).resetPassword(getData()).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                Log.d("TAG", "onResponse: success");
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Log.d("TAG", "onResponse: failed");
            }
        });
    }
    private ResetPasswordRequest getData()
    {
        String username = edtUsername.getText().toString() ;
        String password = edtPassword.getText().toString() ;
        String phoneNumber = edtPhoneNumber.getText().toString() ;
        String email = edtEmail.getText().toString() ;
        return new ResetPasswordRequest(username,password,email,phoneNumber);
    }
    private void init()
    {
        edtUsername= binding.edtUsernameReset;
        edtPassword = binding.edtPasswordReset;
        edtEmail = binding.edtPassword2;
        edtPhoneNumber=binding.edtPassword3;
        btnReset = binding.btnResetPassword;
    }
}