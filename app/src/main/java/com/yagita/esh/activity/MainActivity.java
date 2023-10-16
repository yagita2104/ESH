package com.yagita.esh.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.yagita.esh.R;
import com.yagita.esh.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);
        viewPager2 = findViewById(R.id.viewPager2);
        setUpViewPager();
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.mnuHome){
                    viewPager2.setCurrentItem(0);
                }else if(item.getItemId() == R.id.mnuStorage){
                    viewPager2.setCurrentItem(1);
                } else if (item.getItemId() == R.id.mnuTest) {
                    viewPager2.setCurrentItem(2);
                } else if (item.getItemId() == R.id.mnuSettings) {
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.getMenu().findItem(R.id.mnuHome).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.mnuStorage).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.mnuTest).setChecked(true);
                        break;
                    case 3:
                        bottomNav.getMenu().findItem(R.id.mnuSettings).setChecked(true);
                        break;
                }

            }
        });
    }
}