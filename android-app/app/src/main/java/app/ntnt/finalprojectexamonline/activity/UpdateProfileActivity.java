package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.security.interfaces.EdECKey;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.databinding.UpdateProfileBinding;
import app.ntnt.finalprojectexamonline.model.entites.User;
import app.ntnt.finalprojectexamonline.model.request.UserRequest;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IUserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText edtFirstName,edtLastName,edtPhoneNumber,edtEmail ;
    RadioGroup radioGroup ;
    String gender;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        init();





        Bundle bundle = getIntent().getExtras();
        User user = (User)bundle.getSerializable("profile");


        edtFirstName.setText(user.getFirstName());
        edtLastName.setText(user.getLastName());
        edtEmail.setText(user.getEmail());
        edtPhoneNumber.setText(user.getPhoneNumber());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioGroup.setBackgroundColor(Color.TRANSPARENT);
                RadioButton radioButton = radioGroup.findViewById(i);
                gender = radioButton.getText().toString();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAPIService.createService(IUserService.class).updateProfile(getData(user.getUserId())).enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        startActivity(new Intent(UpdateProfileActivity.this,HomeActivity.class));
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {

                    }
                });

            }
        });


    }


    private UserRequest getData(Long id)
    {
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String email = edtEmail.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();

        return  new UserRequest(email,firstName,lastName,phoneNumber,id,gender);



    }
    private void init()
    {
        edtFirstName=findViewById(R.id.edt_username_update);
        edtLastName=findViewById(R.id.edt_lastname_update);
        edtEmail= findViewById(R.id.edt_email_update);
        edtPhoneNumber= findViewById(R.id.edt_phoneNumber_update);
        radioGroup = findViewById(R.id.radioGroup);
        btnUpdate = findViewById(R.id.btn_update_profile);

    }
}