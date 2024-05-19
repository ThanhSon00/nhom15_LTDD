package app.ntnt.finalprojectexamonline.activity.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.fragment.UploadImageFragment;
import app.ntnt.finalprojectexamonline.model.response.RespRegister;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.ISubjectService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubjectActivity extends AppCompatActivity implements UploadImageFragment.OnImageUploadedListener{
    Button btnOk, btnCancel;
    UploadImageFragment uploadImageFragment;
    EditText editText;
    Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_subject);

        btnOk = findViewById(R.id.dialog_btn_Ok);
        btnCancel = findViewById(R.id.dialog_btn_Cancel);
        editText =findViewById(R.id.editTextTextPersonName);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubject();
            }
        });

        initFragMent();

    }
    private void addSubject()
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), editText.getText().toString());
        MultipartBody.Part avatar = AppConstrain.toPart(this, mUri);
        Log.d("TAG", "onResponse: "+avatar);


        BaseAPIService.createService(ISubjectService.class).addSubject(requestBody,avatar).enqueue(new Callback<RespRegister>() {
            @Override
            public void onResponse(Call<RespRegister> call, Response<RespRegister> response) {
                if(response.body()!=null)
                {
                    Toast.makeText(AddSubjectActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: Thêm thành công");
                    Intent intent = new Intent(AddSubjectActivity.this,HomeTeacherActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("TAG", "onResponse: Bạn đã tạo môn học trước đó, nên không thể tạo môn học mới ");
                    Toast.makeText(AddSubjectActivity.this, "Bạn đã tạo môn học trước đó, nên không thể tạo môn học mới", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSubjectActivity.this,HomeTeacherActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<RespRegister> call, Throwable t) {
                Toast.makeText(AddSubjectActivity.this, "Môn học đã tồn tại", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onResponse: Bạn đã tạo môn học, không thể tạo môn học mới");
            }
        });

    }
    private void initFragMent() {
        uploadImageFragment = UploadImageFragment.getInstance();
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.img_dialog_addImage, uploadImageFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onImageUploaded(Uri uri) {
        mUri = uri;
    }
}