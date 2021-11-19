package com.example.kenko.Teacher.Manage;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kenko.R;
import com.example.kenko.Teacher.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ManageDetails extends AppCompatActivity {

    private ImageView imgBack;

    private ViewPager2 mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_manage_details);

        initUi();
        switchBack();

    }

    private void initUi() {

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        viewPagerAdapter.addFragment(new InformationCource());
        viewPagerAdapter.addFragment(new MemberCource());
        viewPagerAdapter.addFragment(new NotifytionCource());

        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setUserInputEnabled(false);

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_infor:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_member:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_notify:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }


    private void switchBack(){
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
