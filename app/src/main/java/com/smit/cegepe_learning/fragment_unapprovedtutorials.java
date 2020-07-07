package com.smit.cegepe_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class fragment_unapprovedtutorials extends Fragment {
    private RecyclerView rvunapp1, rvunapp2, rvunapp3, rvunapp4, rvunapp5, rvunapp6;
    private PostVideoFeedAdapter postVideoFeedAdapter, postVideoFeedAdapter1, postVideoFeedAdapter2, postVideoFeedAdapter3, postVideoFeedAdapter4, postVideoFeedAdapter5;
    Button buttonApproved, buttonNotApproved;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_unapprovedtutorials, container, false);

        rvunapp1 = v.findViewById(R.id.unapp_rv_cat1);
        rvunapp1.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp2 = v.findViewById(R.id.unapp_rv_cat2);
        rvunapp2.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp3 = v.findViewById(R.id.unapp_rv_cat3);
        rvunapp3.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp4 = v.findViewById(R.id.unapp_rv_cat4);
        rvunapp4.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp5 = v.findViewById(R.id.unapp_rv_cat5);
        rvunapp5.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp6 = v.findViewById(R.id.unapp_rv_cat6);
        rvunapp6.setLayoutManager(new LinearLayoutManager(getContext()));

        buttonApproved = v.findViewById(R.id.btn_approved);
        buttonNotApproved = v.findViewById(R.id.btn_notApproved);

        FirebaseRecyclerOptions<PostVideoFeed> options =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("App Guide").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter = new PostVideoFeedAdapter(options);
        rvunapp1.setAdapter(postVideoFeedAdapter);

        FirebaseRecyclerOptions<PostVideoFeed> options1 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Android").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter1 = new PostVideoFeedAdapter(options1);
        rvunapp2.setAdapter(postVideoFeedAdapter1);

        FirebaseRecyclerOptions<PostVideoFeed> options2 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Web Designing").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter2 = new PostVideoFeedAdapter(options2);
        rvunapp3.setAdapter(postVideoFeedAdapter2);

        FirebaseRecyclerOptions<PostVideoFeed> options3 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("JAVA").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter3 = new PostVideoFeedAdapter(options3);
        rvunapp4.setAdapter(postVideoFeedAdapter3);

        FirebaseRecyclerOptions<PostVideoFeed> options4 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("JavaScript").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter4 = new PostVideoFeedAdapter(options4);
        rvunapp5.setAdapter(postVideoFeedAdapter4);

        FirebaseRecyclerOptions<PostVideoFeed> options5 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Other").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter5 = new PostVideoFeedAdapter(options5);
        rvunapp6.setAdapter(postVideoFeedAdapter5);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        postVideoFeedAdapter.startListening();
        postVideoFeedAdapter1.startListening();
        postVideoFeedAdapter2.startListening();
        postVideoFeedAdapter3.startListening();
        postVideoFeedAdapter4.startListening();
        postVideoFeedAdapter5.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        postVideoFeedAdapter.stopListening();
        postVideoFeedAdapter1.startListening();
        postVideoFeedAdapter2.startListening();
        postVideoFeedAdapter3.startListening();
        postVideoFeedAdapter4.startListening();
        postVideoFeedAdapter5.startListening();
    }

}
