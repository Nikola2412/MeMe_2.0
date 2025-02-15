package com.example.meme.Upload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meme.Experiment.GalleryAdapter;
import com.example.meme.Experiment.TemplateTest;
import com.example.meme.Experiment.UploadInterface;
import com.example.meme.R;
import com.example.meme.databinding.FragmentUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;


public class UploadsFragment extends Fragment implements TemplateTest, UploadInterface {

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
            /*
            TemplateDialogFragment bottomSheet = new TemplateDialogFragment(this);
            Bundle args = new Bundle();
            args.putInt("item_count", 50);
            bottomSheet.setArguments(args);
            bottomSheet.show(getParentFragmentManager(), "TemplateDialogFragment");

             */
            /*
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0;i<50;i++)
                list.add(R.drawable.baseline_video_library_24);
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_upload, null);
            RecyclerView galleryRecyclerView = bottomSheetView.findViewById(R.id.images);
            GalleryAdapter adapter = new GalleryAdapter(list,this);
            Toast.makeText(getContext(), Integer.toString(adapter.getItemCount()), Toast.LENGTH_SHORT).show();
            galleryRecyclerView.setAdapter(adapter);


            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.getContext(),R.style.Theme_MeMe_20_BottomSheet);
            bottomSheetDialog.setContentView(bottomSheetView);

            // Set the shape drawable as the background
            //bottomSheetView.setBackgroundResource(R.drawable.rounded_bottom_sheet_background);

            // Show the Bottom Sheet
            bottomSheetDialog.show();

             */
        });
    }

    @Override
    public void f(String a) {
        Toast.makeText(getContext(),a,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void test() {

    }
}