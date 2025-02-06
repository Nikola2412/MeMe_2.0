package com.example.meme.Memes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
        View v = LayoutInflater.from(context).inflate(R.layout.meme_item,parent,false);

        return new MemeViewHolder(v, memesInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MemeViewHolder holder, int position) {
        Meme meme = memeArrayList.get(position);
        holder.setMeme(meme.id);
        holder.setMeme(meme);
        ViewCompat.setTransitionName(holder.meme, meme.id);
    }

    @Override
    public int getItemCount() {
        return memeArrayList.size();
    }

    public static class MemeViewHolder extends RecyclerView.ViewHolder {
        Meme curent;
        ImageView meme;
        View view;

        //String path;

        String ip = context.getString(R.string.ip);
        public void setMeme(String path) {
            //this.path = path;
            meme = view.findViewById(R.id.mim);
            Glide.with(context).load(ip+"id_memea=" + path)
                    .error(R.drawable.baseline_running_with_errors_24)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(meme);
            Toast.makeText(context,ip + "id_memea=" + path,Toast.LENGTH_LONG).show();
            //Glide.with(context).load().diskCacheStrategy(DiskCacheStrategy.NONE).into(meme);
        }
        public void setMeme(Meme meme){
            this.curent = meme;
        }


        public MemeViewHolder(@NonNull View itemView, MemesInterface recycleViewInterface) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(v -> {
                if(recycleViewInterface !=null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                        recycleViewInterface.openImageTransition(curent,meme);
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
