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
    private RecyclerView rvunapp1, rvunapp2, rvunapp3, rvunapp4, rvunapp5, rvunapp6, rvunapp7, rvunapp8, rvunapp9, rvunapp10, rvunapp11, rvunapp12;
    private PostVideoFeedAdapter postVideoFeedAdapter, postVideoFeedAdapter1, postVideoFeedAdapter2, postVideoFeedAdapter3, postVideoFeedAdapter4, postVideoFeedAdapter5, postVideoFeedAdapter6, postVideoFeedAdapter7, postVideoFeedAdapter8, postVideoFeedAdapter9, postVideoFeedAdapter10, postVideoFeedAdapter11;
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

        rvunapp7 = v.findViewById(R.id.unapp_rv_cat7);
        rvunapp7.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp8 = v.findViewById(R.id.unapp_rv_cat8);
        rvunapp8.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp9 = v.findViewById(R.id.unapp_rv_cat9);
        rvunapp9.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp10 = v.findViewById(R.id.unapp_rv_cat10);
        rvunapp10.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp11 = v.findViewById(R.id.unapp_rv_cat11);
        rvunapp11.setLayoutManager(new LinearLayoutManager(getContext()));

        rvunapp12 = v.findViewById(R.id.unapp_rv_cat12);
        rvunapp12.setLayoutManager(new LinearLayoutManager(getContext()));

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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Designing Databases").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter5 = new PostVideoFeedAdapter(options5);
        rvunapp6.setAdapter(postVideoFeedAdapter5);

        FirebaseRecyclerOptions<PostVideoFeed> options6 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Graphics Designing").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter6 = new PostVideoFeedAdapter(options6);
        rvunapp7.setAdapter(postVideoFeedAdapter6);

        FirebaseRecyclerOptions<PostVideoFeed> options7 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Business").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter7 = new PostVideoFeedAdapter(options7);
        rvunapp8.setAdapter(postVideoFeedAdapter7);

        FirebaseRecyclerOptions<PostVideoFeed> options8 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Mathematics").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter8 = new PostVideoFeedAdapter(options8);
        rvunapp9.setAdapter(postVideoFeedAdapter8);

        FirebaseRecyclerOptions<PostVideoFeed> options9 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Networking").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter9 = new PostVideoFeedAdapter(options9);
        rvunapp10.setAdapter(postVideoFeedAdapter9);

        FirebaseRecyclerOptions<PostVideoFeed> options10 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Game Development").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter10 = new PostVideoFeedAdapter(options10);
        rvunapp11.setAdapter(postVideoFeedAdapter10);

        FirebaseRecyclerOptions<PostVideoFeed> options11 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Other").orderByChild("approvalStatus").equalTo("-1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter11 = new PostVideoFeedAdapter(options11);
        rvunapp12.setAdapter(postVideoFeedAdapter11);

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
        postVideoFeedAdapter6.startListening();
        postVideoFeedAdapter7.startListening();
        postVideoFeedAdapter8.startListening();
        postVideoFeedAdapter9.startListening();
        postVideoFeedAdapter10.startListening();
        postVideoFeedAdapter11.startListening();
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
        postVideoFeedAdapter6.startListening();
        postVideoFeedAdapter7.startListening();
        postVideoFeedAdapter8.startListening();
        postVideoFeedAdapter9.startListening();
        postVideoFeedAdapter10.startListening();
        postVideoFeedAdapter11.startListening();
    }

}
