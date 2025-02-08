package com.example.meme.Memes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meme.FullMeMe;
import com.example.meme.R;
import com.example.meme.databinding.FragmentMemeBinding;
import com.example.meme.objects.Meme;


import java.util.ArrayList;

public class MemesFragment extends Fragment implements MemesInterface {
    private FragmentMemeBinding binding;
    private ArrayList<Meme> memes;


    private MemesViewModel viewModel;
    private RecyclerView recyclerView;
    private MemesAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemeBinding.inflate(inflater, container, false);

        return binding.getRoot();
        //View view = inflater.inflate(R.layout.fragment_meme, container, false);
        //return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MemesViewModel.class);

        recyclerView = binding.memes;
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        if(viewModel.getMemeList() == null || viewModel.getMemeList().isEmpty())
            loadMemeData();


        adapter = new MemesAdapter(getContext(), memes, this);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        // using ViewPager2 so i do not have to restore
//        if (viewModel.getRecyclerViewState() != null) {
//            recyclerView.getLayoutManager().onRestoreInstanceState(viewModel.getRecyclerViewState());
//        }

        //Toast.makeText(getContext(),"123",Toast.LENGTH_SHORT).show();

        SwipeRefreshLayout swipeRefreshLayout = binding.swiper;
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //Toast.makeText(getContext(), "refreshed", Toast.LENGTH_LONG).show();
            int k = memes.size();
            memes.clear();
            adapter.notifyItemRangeRemoved(0, k);
            memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906", "1", "Nikola_24"));
            viewModel.setMemeList(memes);
            adapter.notifyItemInserted(memes.size() - 1);
            swipeRefreshLayout.setRefreshing(false);
        });

    }
    private void loadMemeData() {
        memes = new ArrayList<>();
        memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906", "1", "Nikola_24"));
        memes.add(new Meme("2da52c8b-ace9-4aa9-b50a-94c8c7340cf8", "1", "Nikola_24"));
        memes.add(new Meme("13734294-e074-477a-9cc7-a388aba645ac", "1", "Nikola_24"));
        memes.add(new Meme("773e2429-17ec-4c54-8c52-96847ccfa84c", "1", "Nikola_24"));
        memes.add(new Meme("b3cdf2af-34fa-4bf0-91f7-d84168c3b2b9", "1", "Nikola_24"));
        memes.add(new Meme("9bae790b-2273-4e64-8f42-09a11e2ae9dd", "1", "Nikola_24"));
        memes.add(new Meme("34207dfb-6828-497c-90b6-1d1332912d76", "1", "Nikola_24"));
        viewModel.setMemeList(memes); // Set the loaded data in the ViewModel
    }

    private static final int REQUEST_CODE_FULL_MEME = 1;

    @Override
    public void openImageTransition(Meme meme, ImageView sharedMeme) {
        //Toast.makeText(getActivity(),meme.id,Toast.LENGTH_LONG).show();
        //sharedId.setSharedId(meme.id_kanala);
        //sharedUsername.setSharedUsername(meme.naziv_kanala);

        Intent intent = new Intent(getActivity(), FullMeMe.class);
        intent.putExtra("meme",meme);
        Pair<View, String> p1 = Pair.create(sharedMeme,"image");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                (getActivity(),p1);
        startPostponedEnterTransition();
        startActivityForResult(intent,REQUEST_CODE_FULL_MEME,optionsCompat.toBundle());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        recyclerView.setAdapter(null);
        recyclerView = null;
        adapter = null;
    }
}