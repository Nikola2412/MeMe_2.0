package com.example.meme.Upload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meme.databinding.FragmentUploadBinding;


public class UploadsFragment extends Fragment implements TemplateTest{

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
        binding.test.setOnClickListener(v -> {
            TemplateDialogFragment bottomSheet = new TemplateDialogFragment(this);
            Bundle args = new Bundle();
            args.putInt("item_count", 30);
            bottomSheet.setArguments(args);
            bottomSheet.show(getParentFragmentManager(), "TemplateDialogFragment");
        });
    }

    @Override
    public void f(String a) {
        Toast.makeText(getContext(),a,Toast.LENGTH_SHORT).show();
    }
}