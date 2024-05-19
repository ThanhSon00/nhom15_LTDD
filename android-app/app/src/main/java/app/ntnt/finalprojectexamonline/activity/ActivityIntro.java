package app.ntnt.finalprojectexamonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.ntnt.finalprojectexamonline.activity.teacher.HomeTeacherActivity;
import app.ntnt.finalprojectexamonline.databinding.ActivityIntroBinding;
import app.ntnt.finalprojectexamonline.utils.ContextUtil;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;

public class ActivityIntro extends AppCompatActivity {
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ContextUtil.context = getApplicationContext();
        if (SharedPrefManager.getInstance(ContextUtil.context).isLoggedIn()) {
            if (SharedPrefManager.getInstance(ContextUtil.context).getAuthToken().getRoles().get(0).toString().equals("ROLE_teacher")) {
                Intent intent = new Intent(ActivityIntro.this, HomeTeacherActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ActivityIntro.this, HomeActivity.class);
                startActivity(intent);
            }

        } else {
            Intent intent = new Intent(ActivityIntro.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
