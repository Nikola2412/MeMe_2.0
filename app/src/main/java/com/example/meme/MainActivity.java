package com.example.meme;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.meme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public NavController navController;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        ViewPager2 viewPager = binding.ViewPager;
        mainVewPagerAdapter adapter = new mainVewPagerAdapter(this.getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter(adapter);
        //viewPager.setCurrentItem(1,false);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(getApplicationContext()).clearMemory();
    }
}