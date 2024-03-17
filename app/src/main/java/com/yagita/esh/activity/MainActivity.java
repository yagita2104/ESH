package com.yagita.esh.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.yagita.esh.R;
import com.yagita.esh.adapter.ViewPagerAdapter;
import com.yagita.esh.fragment.HomeFragment;
import com.yagita.esh.fragment.SettingsFragment;
import com.yagita.esh.fragment.StorageFragment;
import com.yagita.esh.fragment.TestFragment;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNav);
        viewPager = findViewById(R.id.viewPager2);

        setupViewPager();
        setupBottomNavigation();
    }

    private void setupViewPager() {
        viewPager.setAdapter(new ViewPagerAdapter(this));
        bottomNavigation.show(0, true);
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }

        });
    }

    private void setupBottomNavigation() {
        bottomNavigation.add(new MeowBottomNavigation.Model(0, R.drawable.home_ic_bottom_nav_1));
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.home_ic_bottom_nav_2));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.home_ic_bottom_nav_3));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.home_ic_bottom_nav_4));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigation.show(position, true);
            }
        });


    }
}