package app.ntnt.finalprojectexamonline.activity.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.MyViewPagerAdapter;
import app.ntnt.finalprojectexamonline.adapter.MyViewPagerAdapterTeacher;
import app.ntnt.finalprojectexamonline.fragment.UploadImageFragment;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;

public class HomeTeacherActivity extends AppCompatActivity implements UploadImageFragment.OnImageUploadedListener {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);

        viewPager2= findViewById(R.id.view_page_teacher);
        bottomNavigationView= findViewById(R.id.bottom_nav_teacher);

        MyViewPagerAdapterTeacher myViewPagerAdapter = new MyViewPagerAdapterTeacher(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        SharedPrefManager.getInstance(getApplicationContext()).getAuthToken();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch(position)
                {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home_teacher).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.create_teacher).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.account_teacher).setChecked(true);
                        break;

                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                if(id==R.id.home_teacher)
                {
                    viewPager2.setCurrentItem(0);
                }
                else if(id ==R.id.create_teacher)
                {
                    viewPager2.setCurrentItem(1);
                }
                else if(id ==R.id.account_teacher)
                {
                    viewPager2.setCurrentItem(2);
                }


                return true;
            }
        });
    }

    @Override
    public void onImageUploaded(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}