package com.example.meme.Memes;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meme.objects.Meme;

import java.util.ArrayList;

public class MemesViewModel extends ViewModel {
    private ArrayList<Meme> list = null;
    private RecyclerView.LayoutManager layoutManager = null;

    public ArrayList<Meme> getList() {
        return list;
    }

    public void setList(ArrayList<Meme> list) {
        this.list = list;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
}
