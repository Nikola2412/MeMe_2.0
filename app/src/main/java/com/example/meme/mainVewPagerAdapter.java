package com.example.meme;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.meme.Memes.MemesFragment;
import com.example.meme.Videos.VideosFragment;

public class mainVewPagerAdapter extends FragmentStateAdapter {
    public mainVewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public mainVewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public mainVewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 2:
                return new add();
            case 1:
                return new VideosFragment();
            case 0:
                return new MemesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() { return 3; }
}
