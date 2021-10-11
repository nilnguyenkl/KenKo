package com.example.kenko.Teacher;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kenko.R;
import com.example.kenko.Teacher.CreateClass.CreateClassFragment;
import com.example.kenko.Teacher.Home.HomeFragment;
import com.example.kenko.Teacher.Manage.ManageFragment;
import com.example.kenko.Teacher.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TeacherDashBoard extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private View mView;
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
        viewPagerAdapter.addFragment(new CreateClassFragment());
        viewPagerAdapter.addFragment(new ManageFragment());
        viewPagerAdapter.addFragment(new ProfileFragment());

        mViewPager.setAdapter(viewPagerAdapter);

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

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.menuHome).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.menuCreate).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.menuManage).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.menuProfile).setChecked(true);
                        break;
                }
            }
        });
    }
}