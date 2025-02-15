package com.example.meme.Upload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meme.databinding.FragmentGalleryDialogListDialogBinding;
import com.example.meme.databinding.FragmentGalleryDialogListDialogItemBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TemplateDialogFragment extends BottomSheetDialogFragment{

    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentGalleryDialogListDialogBinding binding;
    private TemplateTest templateTest;

    public TemplateDialogFragment(TemplateTest templateTest) {
        this.templateTest = templateTest;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentGalleryDialogListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new MemeVideoAdapter(getArguments().getInt(ARG_ITEM_COUNT),templateTest));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;

        ViewHolder(FragmentGalleryDialogListDialogItemBinding binding,TemplateTest t) {
            super(binding.getRoot());
            text = binding.text;
            text.setOnClickListener(v -> t.f((String) text.getText()));
        }
        ViewHolder(FragmentGalleryDialogListDialogItemBinding binding) {
            super(binding.getRoot());
            text = binding.text;
        }

    }

    private class MemeVideoAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;
        private TemplateTest t;

        MemeVideoAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        MemeVideoAdapter(int itemCount,TemplateTest t) {
            mItemCount = itemCount;
            this.t = t;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(FragmentGalleryDialogListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),t);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }
}