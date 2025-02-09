package com.example.meme.Memes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meme.R;
import com.example.meme.objects.Meme;

import java.util.ArrayList;

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.MemeViewHolder> {

    private final MemesInterface memesInterface;
    Context context;
    ArrayList<Meme> memeArrayList;

    public void setMemeArrayList(ArrayList<Meme> memeArrayList) {
        this.memeArrayList = memeArrayList;
    }


    public MemesAdapter(Context context, ArrayList<Meme> memes, MemesInterface memesInterface){
        this.context = context;
        this.memeArrayList = memes;
        this.memesInterface = memesInterface;
    }

    @Override
    public long getItemId(int position) {
        return memeArrayList.get(position).getMemeID().hashCode(); // Ensure unique stable IDs
    }


    @NonNull
    @Override
    public MemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.meme_item,parent,false);

        //return new MemeViewHolder(MemeItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false) , memesInterface);
        return new MemesAdapter.MemeViewHolder(v,memesInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MemeViewHolder holder, int position) {
        Meme meme = memeArrayList.get(position);
        holder.current = meme;
        holder.setMeme(meme.getMemeID());
        ViewCompat.setTransitionName(holder.meme, meme.getMemeID());
    }

    @Override
    public int getItemCount() {
        return memeArrayList.size();
    }

    public class MemeViewHolder extends RecyclerView.ViewHolder {
        ImageView meme;
        public Meme current;
        View view;

        //String path;

        String ip = context.getString(R.string.ip);
        public void setMeme(String path) {
            //this.path = path;
            meme = view.findViewById(R.id.mim);
            Glide.with(context).load(ip + "id_memea=" + path)
                    .error(R.drawable.baseline_running_with_errors_24)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(meme);
        }


        public MemeViewHolder(@NonNull View view, MemesInterface recycleViewInterface) {
            super(view);
            this.view = view;
            view.setOnClickListener(v -> {
                if (recycleViewInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                        recycleViewInterface.openImageTransition(current, meme);
                }
            });
            view.setOnLongClickListener(v -> {
                String linkToShare = ip + "meme=" + current.getMemeID();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, linkToShare);
                context.startActivity(Intent.createChooser(shareIntent, "Share link via"));
                return true;
            });

        }
    }
}
