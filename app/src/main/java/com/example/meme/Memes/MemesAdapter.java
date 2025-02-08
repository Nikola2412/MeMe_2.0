package com.example.meme.Memes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meme.R;
import com.example.meme.databinding.MemeItemBinding;
import com.example.meme.objects.Meme;

import java.util.ArrayList;

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.MemeViewHolder> {

    private final MemesInterface memesInterface;
    static Context context;
    ArrayList<Meme> memeArrayList;

    public void setMemeArrayList(ArrayList<Meme> memeArrayList) {
        this.memeArrayList = memeArrayList;
    }

    public MemesAdapter(Context context, ArrayList<Meme> memes, MemesInterface memesInterface){
        this.context = context;
        this.memeArrayList = memes;
        this.memesInterface = memesInterface;
    }

    @NonNull
    @Override
    public MemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(context).inflate(R.layout.meme_item,parent,false);

        return new MemeViewHolder(MemeItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false) , memesInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MemeViewHolder holder, int position) {
        Meme meme = memeArrayList.get(position);
        holder.curent = meme;
        holder.setMeme(meme.id);
        ViewCompat.setTransitionName(holder.binding.mim, meme.id);
    }

    @Override
    public int getItemCount() {
        return memeArrayList.size();
    }

    public static class MemeViewHolder extends RecyclerView.ViewHolder {
        public Meme curent;
        public MemeItemBinding binding;

        //String path;

        String ip = context.getString(R.string.ip);
        public void setMeme(String path) {
            //this.path = path;
            Glide.with(context).load(ip + "id_memea=" + path)
                    .error(R.drawable.baseline_running_with_errors_24)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.mim);
            //Toast.makeText(context,ip + "id_memea=" + path,Toast.LENGTH_LONG).show();
        }
        public void setMeme(Meme meme){
            this.curent = meme;
        }


        public MemeViewHolder(@NonNull MemeItemBinding binding, MemesInterface recycleViewInterface) {
            super(binding.getRoot());
            this.binding = binding;

            View view = binding.getRoot();
            view.setOnClickListener(v -> {
                if(recycleViewInterface !=null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                        recycleViewInterface.openImageTransition(curent, binding.mim);
                }
            });
            view.setOnLongClickListener(v -> {
                String linkToShare = ip + "meme=" + curent.id;
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, linkToShare);
                context.startActivity(Intent.createChooser(shareIntent, "Share link via"));
                return true;
            });

        }
    }
}
