package app.ntnt.finalprojectexamonline.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.ntnt.finalprojectexamonline.fragment.AccountFragment;
import app.ntnt.finalprojectexamonline.fragment.HistoryFragment;
import app.ntnt.finalprojectexamonline.fragment.HomeFragment;
import app.ntnt.finalprojectexamonline.fragment.teacher.CreateTestFragment;
import app.ntnt.finalprojectexamonline.fragment.teacher.TeacherHomeFragment;


public class MyViewPagerAdapterTeacher extends FragmentStateAdapter {
    public MyViewPagerAdapterTeacher(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new TeacherHomeFragment();
            case 1:
                return new CreateTestFragment();
            case 2:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
