package com.example.meme.Videos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meme.databinding.FragmentVideoBinding;
import com.example.meme.objects.Video;

import java.util.ArrayList;

public class VideosFragment extends Fragment implements VideosInterface{

    private FragmentVideoBinding binding;
    private ArrayList<Video> videos;


    private VideosAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setOnApplyWindowInsetsListener(binding.videos, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = binding.videos;

        loadData();

        adapter = new VideosAdapter(getContext(),videos,this);
        recyclerView.setAdapter(adapter);

        adapter.notifyItemRangeInserted(0,videos.size());

        SwipeRefreshLayout swipeRefreshLayout = binding.swiper;
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //Toast.makeText(getContext(), "refreshed", Toast.LENGTH_LONG).show();
            int k = videos.size();
            videos.clear();
            adapter.notifyItemRangeRemoved(0, k);
            videos.add(new Video("16","helis","1","Nikola_24"));
            //viewModel.setMemeList(videos);
            adapter.notifyItemRangeInserted(0,videos.size());
            swipeRefreshLayout.setRefreshing(false);
        });

    }

    private void loadData(){
        videos = new ArrayList<>();
        videos.add(new Video("16","helis","1","Nikola_24"));
        videos.add(new Video("15","helis","1","Nikola_24"));
        videos.add(new Video("14","helis","1","Nikola_24"));
        videos.add(new Video("13","helis","1","Nikola_24"));
        videos.add(new Video("12","helis","1","Nikola_24"));
        videos.add(new Video("11","helis","1","Nikola_24"));
        videos.add(new Video("10","helis","1","Nikola_24"));
        videos.add(new Video("9","helis","1","Nikola_24"));
        videos.add(new Video("8","helis","1","Nikola_24"));
        videos.add(new Video("7","helis","1","Nikola_24"));
        videos.add(new Video("6","helis","1","Nikola_24"));
        videos.add(new Video("5","helis","1","Nikola_24"));
        videos.add(new Video("4","helis","1","Nikola_24"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void startVideo(Integer pos) {
        Toast.makeText(getContext(),Integer.toString(pos),Toast.LENGTH_SHORT).show();
    }
}