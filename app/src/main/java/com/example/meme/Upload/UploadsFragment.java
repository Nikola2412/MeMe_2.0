package com.example.meme.Upload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meme.R;
import com.example.meme.databinding.FragmentUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class UploadsFragment extends Fragment {

    private FragmentUploadBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.test.setOnClickListener(k -> {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.meme_video_bottomsheet, null);

            bottomSheetView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    ViewCompat.setOnApplyWindowInsetsListener(bottomSheetView, (view, insets) -> {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        view.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
                        return insets;
                    });
                    bottomSheetView.removeOnLayoutChangeListener(this);
                }
            });

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }
}