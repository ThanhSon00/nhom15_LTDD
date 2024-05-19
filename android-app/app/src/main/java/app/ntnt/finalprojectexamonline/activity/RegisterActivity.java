package app.ntnt.finalprojectexamonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.databinding.ActivityRegisterBinding;
import app.ntnt.finalprojectexamonline.fragment.UploadImageFragment;
import app.ntnt.finalprojectexamonline.model.request.RegisterRequest;
import app.ntnt.finalprojectexamonline.model.response.RespRegister;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;

import app.ntnt.finalprojectexamonline.services.IAuthService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements UploadImageFragment.OnImageUploadedListener {
    private ActivityRegisterBinding binding;
    private Uri mUri;
    UploadImageFragment uploadImageFragment;
    private String gender;
    private void initFragMent(){
       uploadImageFragment = UploadImageFragment.getInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.uploadImage, uploadImageFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onImageUploaded(Uri uri) {
        mUri = uri;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initFragMent();

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioGroup.setBackgroundColor(Color.TRANSPARENT);
                RadioButton radioButton = radioGroup.findViewById(i);
                gender = radioButton.getText().toString();
            }
        });
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    //Gọi API đăng kí
                    createAccount();
                }
            }
        });
    }

    private boolean validate(){
        if (binding.edtEmail.getText().toString().trim().isEmpty())
        {
            binding.edtEmail.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (binding.edtFirstName.getText().toString().trim().isEmpty())
        {
            binding.edtFirstName.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (binding.edtLastName.getText().toString().trim().isEmpty())
        {
            binding.edtLastName.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (binding.edtPhoneNumber.getText().toString().trim().isEmpty())
        {
            binding.edtPhoneNumber.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (binding.edtUsernameSignup.getText().toString().trim().isEmpty())
        {
            binding.edtUsernameSignup.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (binding.edtPasswordSignup.getText().toString().trim().isEmpty())
        {
            binding.edtPasswordSignup.setError("Vui lòng nhập thông tin");
            return false;
        }
        if(gender == null){
            RadioGroup radioGroup = findViewById(R.id.radioGroup);
            radioGroup.setBackgroundColor(Color.RED);
            return false;
        }
        else{
            return true;
        }
    }


    private RegisterRequest getInputData(){
        RegisterRequest userRegister = new RegisterRequest();
        userRegister.setFirstName(binding.edtFirstName.getText().toString());
        userRegister.setLastName(binding.edtLastName.getText().toString());
        userRegister.setEmail(binding.edtEmail.getText().toString());
        userRegister.setPhoneNumber(binding.edtPhoneNumber.getText().toString());
        userRegister.setUsername(binding.edtUsernameSignup.getText().toString());
        userRegister.setPassword(binding.edtPasswordSignup.getText().toString());
        userRegister.setGender(gender);
        return userRegister;
    }
    public void createAccount()
    {
        MultipartBody.Part avatar = AppConstrain.toPart(this, mUri);
       if (!validate())
           return;
       RegisterRequest userRegister = getInputData();
        BaseAPIService.createService(IAuthService.class).register(userRegister, avatar)
                .enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity> call, @NonNull Response<ResponseEntity> response) {
                if (response.body()!= null){
                    if (!response.body().isError()){
                        Toast.makeText(RegisterActivity.this,"Đăng ký thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, response.body().getData().toString(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_LONG).show();
            }
        });
    }

}