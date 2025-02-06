package com.example.meme.Memes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        memes = new ArrayList<>();
        RecyclerView rv = view.findViewById(R.id.memes);
        MemesAdapter md = new MemesAdapter(getContext(), memes, this);
        ImageView noNet = view.findViewById(R.id.noNet);
        noNet.setVisibility(View.GONE);

        rv.setAdapter(md);

        memes.add(new Meme("25995ffe-9480-4de1-8192-e1e912451906","1","Nikola_24"));
        md.notifyItemInserted(memes.size() - 1);
        memes.add(new Meme("2da52c8b-ace9-4aa9-b50a-94c8c7340cf8","1","Nikola_24"));
        md.notifyItemInserted(memes.size() - 1);
        memes.add(new Meme("13734294-e074-477a-9cc7-a388aba645ac","1","Nikola_24"));
        md.notifyItemInserted(memes.size() - 1);
        memes.add(new Meme("773e2429-17ec-4c54-8c52-96847ccfa84c","1","Nikola_24"));
        md.notifyItemInserted(memes.size() - 1);

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
}