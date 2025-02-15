package com.example.meme;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.meme.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentItemId = 0;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        ViewPager2 viewPager = binding.ViewPager;
        mainVewPagerAdapter adapter = new mainVewPagerAdapter(this.getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter(adapter);

        navView = binding.navView;

        navView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            //String itemName = (String) item.getTitle();

            if (itemId == currentItemId) {
                return false;
            }

            //Toast.makeText(this,Integer.toString(itemId) + " " + Integer.toString(currentItemId),Toast.LENGTH_SHORT).show();

            if (itemId == R.id.memesFragment) {
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.videosFragment) {
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.uploadsFragment) {
                viewPager.setCurrentItem(2);
                hideBottomNav();
            } else {
                return false;
            }
            currentItemId = itemId;
            return true;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                navView.getMenu().getItem(position).setChecked(true);
                if(position==2) hideBottomNav();
            }
        });

        viewPager.setCurrentItem(1,false);
        currentItemId = R.id.videosFragment;

    }
    private void hideBottomNav() {
        navView.animate().translationY(navView.getHeight()).setDuration(200).start();
    }

    private void showBottomNav() {
        navView.animate().translationY(0).setDuration(200).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(getApplicationContext()).clearMemory();
    }
}