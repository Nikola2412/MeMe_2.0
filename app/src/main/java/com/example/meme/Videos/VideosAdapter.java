package com.example.meme.Videos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meme.R;
import com.example.meme.objects.Video;
import com.example.meme.databinding.VideoItemBinding;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {

    private ArrayList<Video> videoArrayList;
    Context context;
    private final VideosInterface videosInterface;

    public VideosAdapter(Context context, ArrayList<Video> videoArrayList, VideosInterface videosInterface) {
        this.videoArrayList = videoArrayList;
        this.context = context;
        this.videosInterface = videosInterface;
    }

    @NonNull
    @Override
    public VideosAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(VideoItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false),videosInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.VideoViewHolder holder, int position) {
        Video video = videoArrayList.get(position);
        holder.current = video;

        holder.setThubnaile(video.getVideoID());
        holder.binding.videoName.setText(video.getVideoName());
        holder.binding.canalName.setText(video.getCanalName());

        //ViewCompat.setTransitionName(holder.binding.mim, meme.id);
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoItemBinding binding;
        Video current;

        String ip = context.getString(R.string.ip);

        public VideoViewHolder(@NonNull VideoItemBinding binding, VideosInterface recycleViewInterface) {
            super(binding.getRoot());
            this.binding = binding;

            View view = binding.getRoot();
            view.setOnClickListener(v -> {
                if(recycleViewInterface !=null){
                    int pos = getAbsoluteAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                        recycleViewInterface.startVideo(pos);
                }
            });

        }

        public void setThubnaile(String id) {
            Glide.with(context).load(ip + "/thubnails/" + id + ".jpg")
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerInside()
                    .error(R.drawable.baseline_running_with_errors_24)
                    .into(binding.videoThumbnail);
        }
    }
}