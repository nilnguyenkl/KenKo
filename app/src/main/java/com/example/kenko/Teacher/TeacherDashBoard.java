package com.example.kenko.Teacher;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.kenko.R;
import com.example.kenko.Teacher.CreateCource.CreateCourceFragment;
import com.example.kenko.Teacher.Home.HomeFragment;
import com.example.kenko.Teacher.Manage.ManageFragment;
import com.example.kenko.Teacher.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TeacherDashBoard extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_dashboard);

        initUi();

    }

    private void initUi() {

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        viewPagerAdapter.addFragment(new HomeFragment());
        viewPagerAdapter.addFragment(new CreateCourceFragment());
        viewPagerAdapter.addFragment(new ManageFragment());
        viewPagerAdapter.addFragment(new ProfileFragment());

        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setUserInputEnabled(false);

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuHome:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.menuCreate:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.menuManage:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.menuProfile:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    // https://stackoverflow.com/questions/40520149/how-to-refresh-recyclerview-in-one-fragment-when-data-changed-in-another-fragmen
    // https://stackoverflow.com/questions/50115471/refresh-recyclerview-fragment-from-another-fragments-adapter/50115975
}