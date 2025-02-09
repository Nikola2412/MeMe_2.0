package com.example.meme;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.meme.objects.Meme;

public class FullMeMe extends AppCompatActivity {
    //private ScaleGestureDetector scaleGestureDetector;
    ImageView meme;
    private Meme meme_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_me_me);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        supportPostponeEnterTransition();
        postponeEnterTransition();

        meme = findViewById(R.id.view_meme);
        TextView canalName = findViewById(R.id.naziv_kanala);

        Bundle extras = getIntent().getExtras();
        meme_data  = extras.getParcelable("meme");
        canalName.setText(meme_data.getCanalName());

        ViewCompat.setTransitionName(meme, meme_data.getMemeID());

        Glide.with(this).load(getString(R.string.ip) + "id_memea=" + meme_data.getMemeID()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        getOnBackPressedDispatcher().onBackPressed();
                        Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        return false;
                    }
                }).into(this.meme);

        //scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        findViewById(R.id.opis).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            getOnBackPressedDispatcher().onBackPressed();
            //finish();
        });
        findViewById(R.id.back_buttom).setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        findViewById(R.id.share).setOnClickListener(v -> {
            String linkToShare = getString(R.string.ip) + "meme=" + meme_data.getMemeID();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, linkToShare);
            startActivity(Intent.createChooser(shareIntent, "Share link via"));
        });
    }
}