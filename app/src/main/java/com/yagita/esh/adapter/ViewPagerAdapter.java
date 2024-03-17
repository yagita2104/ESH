package com.yagita.esh.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yagita.esh.fragment.HomeFragment;
import com.yagita.esh.fragment.SettingsFragment;
import com.yagita.esh.fragment.StorageFragment;
import com.yagita.esh.fragment.TestFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment(); // Thay thế HomeFragment bằng Fragment đầu tiên của bạn
            case 1:
                return new StorageFragment(); // Thay thế SearchFragment bằng Fragment thứ hai của bạn
            case 2:
                return new TestFragment(); // Thay thế ProfileFragment bằng Fragment thứ ba của bạn
            case 3:
                return new SettingsFragment();// Trả về một Fragment trống nếu không có vị trí nào khớp
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Số lượng trang trong ViewPager2 của bạn
    }
}
