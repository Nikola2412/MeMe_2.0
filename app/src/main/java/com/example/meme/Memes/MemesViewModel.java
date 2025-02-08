package com.example.meme.Memes;

import android.os.Parcelable;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meme.objects.Meme;

import java.util.ArrayList;

public class MemesViewModel extends ViewModel {
    private ArrayList<Meme> memeList = null; // Store the list of Memes
    private RecyclerView.LayoutManager layoutManager;
    //private Parcelable recyclerViewState;

    // Getters and setters for memeList, layoutManager, and recyclerViewState
    public ArrayList<Meme> getMemeList() {
        return memeList;
    }

    public void setMemeList(ArrayList<Meme> memeList) {
        this.memeList = memeList;
    }
}