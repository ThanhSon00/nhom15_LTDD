package app.ntnt.finalprojectexamonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import app.ntnt.finalprojectexamonline.R;

public class SplashActivity extends AppCompatActivity {
    private TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appName = findViewById(R.id.app_name);
        Typeface typeface;

        Animation anim = AnimationUtils.loadAnimation( this, R.anim.myanim);
        appName.setAnimation(anim);

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Intent intent = new Intent(SplashActivity.this, ActivityIntro.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }.start();
    }
}