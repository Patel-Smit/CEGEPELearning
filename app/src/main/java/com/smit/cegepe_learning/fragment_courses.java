package com.smit.cegepe_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class fragment_courses extends Fragment {

    private RecyclerView rvcourseCategories;
    private PostVideoAdapter postVideoAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courses, container, false);

        rvcourseCategories = (RecyclerView) v.findViewById(R.id.courses_rv_courseCategory);
        rvcourseCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<PostVideos> options =
                new FirebaseRecyclerOptions.Builder<PostVideos>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videocategories"), PostVideos.class)
                        .build();

        postVideoAdapter = new PostVideoAdapter(options);
        rvcourseCategories.setAdapter(postVideoAdapter);
        postVideoAdapter.startListening();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        postVideoAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        postVideoAdapter.stopListening();
    }
}
