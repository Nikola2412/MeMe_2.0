package com.example.meme;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.meme.databinding.ActivityFullMeMeBinding;
import com.example.meme.objects.Meme;

public class FullMeMe extends AppCompatActivity {
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    ImageView meme;
    private Meme meme_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFullMeMeBinding binding = ActivityFullMeMeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        supportPostponeEnterTransition();
        postponeEnterTransition();

        meme = binding.viewMeme;
        TextView canalName = binding.canalName;

        Bundle extras = getIntent().getExtras();
        meme_data  = extras.getParcelable("meme");
        canalName.setText(meme_data.getCanalName());

        ViewCompat.setTransitionName(meme,meme_data.getMemeID());

        Glide.with(this).load(getString(R.string.ip) + "id_memea=" + meme_data.getMemeID()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        return false;
                    }
                }).into(this.meme);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        binding.footer.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            getOnBackPressedDispatcher().onBackPressed();
            //finish();
        });
        findViewById(R.id.backButton).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            getOnBackPressedDispatcher().onBackPressed();
        });
        findViewById(R.id.share).setOnClickListener(v -> {
            String linkToShare = getString(R.string.ip) + "meme=" + meme_data.getMemeID();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, linkToShare);
            startActivity(Intent.createChooser(shareIntent, "Share link via"));
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(1f, Math.min(mScaleFactor, 10.0f));
            meme.setScaleX(mScaleFactor);
            meme.setScaleY(mScaleFactor);
            return true;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //recreate();
    }
}