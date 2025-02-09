package com.example.meme.Memes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meme.FullMeMe;
import com.example.meme.databinding.FragmentMemeBinding;
import com.example.meme.objects.Meme;


import java.util.ArrayList;

public class MemesFragment extends Fragment implements MemesInterface {
    private FragmentMemeBinding binding;
    private ArrayList<Meme> memes;


    private MemesRecyclerViewState recyclerViewState;


    private RecyclerView recyclerView;
    private MemesAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            recyclerViewState = savedInstanceState.getParcelable("recycler_state");
        }
        memes = new ArrayList<>();
        recyclerView = binding.memes;

        adapter = new MemesAdapter(getContext(), memes, this);
        recyclerView.setAdapter(adapter);

        if (recyclerViewState != null) {
            memes = recyclerViewState.getMemes();
            adapter.setMemeArrayList(memes);
        } else {
            loadData();
        }

        adapter.notifyDataSetChanged();


        SwipeRefreshLayout swipeRefreshLayout = binding.swiper;
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //Toast.makeText(getContext(), "refreshed", Toast.LENGTH_LONG).show();
            memes.clear();
            adapter.notifyDataSetChanged();
            memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906", "1", "Nikola 24"));
            memes.add(new Meme("5cac1756-f8c9-438f-96d5-fabe120c4626", "1", "Nikola 24"));
            memes.add(new Meme("692b5cac-fb02-47d0-a24f-ec6075fc2c91", "1", "Nikola 24"));
            memes.add(new Meme("08c26e71-2cd9-495a-b823-0ccd409c0551", "1", "Nikola 24"));
            memes.add(new Meme("34207dfb-6828-497c-90b6-1d1332912d76", "1", "Nikola 24"));
            memes.add(new Meme("6da92429-515f-4e70-a573-737a95c0b63e", "1", "Nikola 24"));
            memes.add(new Meme("d6b64321-80a2-4122-a16a-6380862e7706", "1", "Nikola 24"));
            memes.add(new Meme("6639de9f-6b76-4ab3-8998-b0d18b355ae9", "1", "Nikola 24"));
            memes.add(new Meme("b5dae03e-a54f-4cf5-83b1-227ffaaba753", "1", "Nikola 24"));
            memes.add(new Meme("11e20a00-8d82-4581-9bc8-b661c7d418cb", "1", "Nikola 24"));
            memes.add(new Meme("3a65ba6f-2b3a-4a3c-8e57-ba220a7dc8d6", "1", "Nikola 24"));
            memes.add(new Meme("c2eb1976-5aec-47bf-91b4-c99f4c5080e5", "1", "Nikola 24"));
            recyclerViewState = new MemesRecyclerViewState(memes);
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });

    }
    private void loadData() {
        memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906", "1", "Nikola 24"));
        memes.add(new Meme("5cac1756-f8c9-438f-96d5-fabe120c4626", "1", "Nikola 24"));
        memes.add(new Meme("692b5cac-fb02-47d0-a24f-ec6075fc2c91", "1", "Nikola 24"));
        memes.add(new Meme("08c26e71-2cd9-495a-b823-0ccd409c0551", "1", "Nikola 24"));
        memes.add(new Meme("34207dfb-6828-497c-90b6-1d1332912d76", "1", "Nikola 24"));
        memes.add(new Meme("6da92429-515f-4e70-a573-737a95c0b63e", "1", "Nikola 24"));
        memes.add(new Meme("d6b64321-80a2-4122-a16a-6380862e7706", "1", "Nikola 24"));
        memes.add(new Meme("6639de9f-6b76-4ab3-8998-b0d18b355ae9", "1", "Nikola 24"));
        memes.add(new Meme("b5dae03e-a54f-4cf5-83b1-227ffaaba753", "1", "Nikola 24"));
        memes.add(new Meme("11e20a00-8d82-4581-9bc8-b661c7d418cb", "1", "Nikola 24"));
        memes.add(new Meme("3a65ba6f-2b3a-4a3c-8e57-ba220a7dc8d6", "1", "Nikola 24"));
        memes.add(new Meme("c2eb1976-5aec-47bf-91b4-c99f4c5080e5", "1", "Nikola 24"));
        memes.add(new Meme("1038fb44-725d-4efb-944c-25e301f9db90", "1", "Nikola 24"));
        memes.add(new Meme("b3cdf2af-34fa-4bf0-91f7-d84168c3b2b9", "1", "Nikola 24"));
        memes.add(new Meme("28c9efef-f2e2-4be9-b86b-1fb5172072a9", "1", "Nikola 24"));
        memes.add(new Meme("fbf3cc08-6808-4af1-b9bb-dcff2d2afab8", "1", "Nikola 24"));
        memes.add(new Meme("eea0910f-6168-461f-950c-d1900c611951", "1", "Nikola 24"));
        memes.add(new Meme("8c3c4f61-f0c5-40d0-b9ca-2c61e3cd624b", "1", "Nikola 24"));
        memes.add(new Meme("c914efe0-71c6-44ca-bebe-781d53b56900", "1", "Nikola 24"));
        memes.add(new Meme("9bae790b-2273-4e64-8f42-09a11e2ae9dd", "1", "Nikola 24"));
        recyclerViewState = new MemesRecyclerViewState(memes);

    }
    private static final int REQUEST_CODE_FULL_MEME = 1;


    @Override
    public void openImageTransition(Meme meme, ImageView sharedMeme) {
        Intent intent = new Intent(getActivity(), FullMeMe.class);
        intent.putExtra("meme", meme);


        Pair<View, String> p1 = Pair.create(sharedMeme, "image");
        //Pair<View, String> p1 = Pair.create(sharedMeme, ViewCompat.getTransitionName(sharedMeme));

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                (getActivity(),p1);
        startPostponedEnterTransition();

        startActivityForResult(intent, REQUEST_CODE_FULL_MEME, optionsCompat.toBundle());
        //someActivityResultLauncher.launch(intent,optionsCompat);
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {

    });

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FULL_MEME) {

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("recycler_state", recyclerViewState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}