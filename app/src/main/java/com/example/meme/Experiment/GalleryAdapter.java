package com.example.meme.Experiment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meme.R;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private final UploadInterface uploadInterface;


    private final ArrayList<Integer> imageUris;

    public GalleryAdapter(ArrayList<Integer> imageUris, UploadInterface uploadInterface) {
        this.imageUris = imageUris;
        this.uploadInterface = uploadInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer imageUri = imageUris.get(position);
        holder.set(imageUri);
    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public void set(Integer id){
            img.setImageResource(id);
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.gallery_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadInterface.test();
                }
            });
        }
    }
}
