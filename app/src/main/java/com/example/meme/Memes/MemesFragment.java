package com.example.meme.Memes;

import static android.app.Activity.RESULT_CANCELED;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
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
import com.example.meme.databinding.FragmentMemeBinding;
import com.example.meme.objects.Meme;


import java.util.ArrayList;

public class MemesFragment extends Fragment implements MemesInterface {
    private FragmentMemeBinding binding;
    private ArrayList<Meme> memes;


    //private MemesViewModel viewModel;
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.memes, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        //viewModel = new ViewModelProvider(requireActivity()).get(MemesViewModel.class);

        recyclerView = binding.memes;
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        //if(viewModel.getMemeList() == null || viewModel.getMemeList().isEmpty())
        loadData();


        adapter = new MemesAdapter(getContext(), memes, this);
        recyclerView.setAdapter(adapter);

        adapter.notifyItemRangeInserted(0, memes.size());


        SwipeRefreshLayout swipeRefreshLayout = binding.swiper;
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //Toast.makeText(getContext(), "refreshed", Toast.LENGTH_LONG).show();
            int k = memes.size();
            memes.clear();
            adapter.notifyItemRangeRemoved(0, k);
            memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906", "1", "Nikola_24"));
            //viewModel.setMemeList(memes);
            adapter.notifyItemRangeInserted(0,memes.size());
            swipeRefreshLayout.setRefreshing(false);
        });

    }
    private void loadData() {
        memes = new ArrayList<>();
        memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906", "1", "Nikola_24"));
        memes.add(new Meme("2da52c8b-ace9-4aa9-b50a-94c8c7340cf8", "1", "Nikola_24"));
        memes.add(new Meme("13734294-e074-477a-9cc7-a388aba645ac", "1", "Nikola_24"));
        memes.add(new Meme("773e2429-17ec-4c54-8c52-96847ccfa84c", "1", "Nikola_24"));
        memes.add(new Meme("b3cdf2af-34fa-4bf0-91f7-d84168c3b2b9", "1", "Nikola_24"));
        memes.add(new Meme("9bae790b-2273-4e64-8f42-09a11e2ae9dd", "1", "Nikola_24"));
        memes.add(new Meme("34207dfb-6828-497c-90b6-1d1332912d76", "1", "Nikola_24"));
        //viewModel.setMemeList(memes); // Set the loaded data in the ViewModel
    }

    @Override
    public void openImageTransition(Meme meme, ImageView sharedMeme) {
        Intent intent = new Intent(getActivity(), FullMeMe.class);
        intent.putExtra("meme", meme);

        Pair<View, String> p1 = Pair.create(sharedMeme, "image");

        //noinspection unchecked
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), p1);

        //startActivityForResult(intent, REQUEST_CODE_FULL_MEME, optionsCompat.toBundle());
        someActivityResultLauncher.launch(intent,optionsCompat);
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
        /*
        if(o.getResultCode() == RESULT_CANCELED) {
            Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
        }

         */
    });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        recyclerView.setAdapter(null);
        recyclerView = null;
        adapter = null;
    }
}