package com.smit.cegepe_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fragment_tutorialapprovals extends Fragment {
    private RecyclerView rvTutorialApprovals, rvCat1, rvCat2, rvCat3, rvCat4, rvCat5, rvCat6, rvCat7, rvCat8, rvCat9, rvCat10, rvCat11;
    private PostVideoFeedAdapter postVideoFeedAdapter, postVideoFeedAdapter1, postVideoFeedAdapter2, postVideoFeedAdapter3, postVideoFeedAdapter4, postVideoFeedAdapter5, postVideoFeedAdapter6, postVideoFeedAdapter7, postVideoFeedAdapter8, postVideoFeedAdapter9, postVideoFeedAdapter10, postVideoFeedAdapter11;
    Button buttonApproved, buttonNotApproved;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tutorialapprovals, container, false);

        rvTutorialApprovals = v.findViewById(R.id.rv_tutorialApprovals);
        rvTutorialApprovals.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat1 = v.findViewById(R.id.tutapprovals_rv_cat1);
        rvCat1.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat2 = v.findViewById(R.id.tutapprovals_rv_cat2);
        rvCat2.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat3 = v.findViewById(R.id.tutapprovals_rv_cat3);
        rvCat3.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat4 = v.findViewById(R.id.tutapprovals_rv_cat4);
        rvCat4.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat5 = v.findViewById(R.id.tutapprovals_rv_cat5);
        rvCat5.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat6 = v.findViewById(R.id.tutapprovals_rv_cat6);
        rvCat6.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat7 = v.findViewById(R.id.tutapprovals_rv_cat7);
        rvCat7.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat8 = v.findViewById(R.id.tutapprovals_rv_cat8);
        rvCat8.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat9 = v.findViewById(R.id.tutapprovals_rv_cat9);
        rvCat9.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat10 = v.findViewById(R.id.tutapprovals_rv_cat10);
        rvCat10.setLayoutManager(new LinearLayoutManager(getContext()));

        rvCat11 = v.findViewById(R.id.tutapprovals_rv_cat11);
        rvCat11.setLayoutManager(new LinearLayoutManager(getContext()));

        buttonApproved = v.findViewById(R.id.btn_approved);
        buttonNotApproved = v.findViewById(R.id.btn_notApproved);

        FirebaseRecyclerOptions<PostVideoFeed> options =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("App Guide").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter = new PostVideoFeedAdapter(options);
        rvTutorialApprovals.setAdapter(postVideoFeedAdapter);

        FirebaseRecyclerOptions<PostVideoFeed> options1 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Android").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter1 = new PostVideoFeedAdapter(options1);
        rvCat1.setAdapter(postVideoFeedAdapter1);

        FirebaseRecyclerOptions<PostVideoFeed> options2 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Web Designing").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter2 = new PostVideoFeedAdapter(options2);
        rvCat2.setAdapter(postVideoFeedAdapter2);

        FirebaseRecyclerOptions<PostVideoFeed> options3 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("JAVA").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter3 = new PostVideoFeedAdapter(options3);
        rvCat3.setAdapter(postVideoFeedAdapter3);

        FirebaseRecyclerOptions<PostVideoFeed> options4 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("JavaScript").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter4 = new PostVideoFeedAdapter(options4);
        rvCat4.setAdapter(postVideoFeedAdapter4);

        FirebaseRecyclerOptions<PostVideoFeed> options5 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Designing Databases").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter5 = new PostVideoFeedAdapter(options5);
        rvCat5.setAdapter(postVideoFeedAdapter5);

        FirebaseRecyclerOptions<PostVideoFeed> options6 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Graphics Designing").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter6 = new PostVideoFeedAdapter(options6);
        rvCat6.setAdapter(postVideoFeedAdapter6);

        FirebaseRecyclerOptions<PostVideoFeed> options7 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Business").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter7 = new PostVideoFeedAdapter(options7);
        rvCat7.setAdapter(postVideoFeedAdapter7);

        FirebaseRecyclerOptions<PostVideoFeed> options8 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Mathematics").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter8 = new PostVideoFeedAdapter(options8);
        rvCat8.setAdapter(postVideoFeedAdapter8);

        FirebaseRecyclerOptions<PostVideoFeed> options9 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Networking").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter9 = new PostVideoFeedAdapter(options9);
        rvCat9.setAdapter(postVideoFeedAdapter9);

        FirebaseRecyclerOptions<PostVideoFeed> options10 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Game Development").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter10 = new PostVideoFeedAdapter(options10);
        rvCat10.setAdapter(postVideoFeedAdapter10);

        FirebaseRecyclerOptions<PostVideoFeed> options11 =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Other").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter11 = new PostVideoFeedAdapter(options11);
        rvCat11.setAdapter(postVideoFeedAdapter11);

        return v;
    }


    public void approve() {
        System.out.println("h");
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
        postVideoFeedAdapter1.stopListening();
        postVideoFeedAdapter2.stopListening();
        postVideoFeedAdapter3.stopListening();
        postVideoFeedAdapter4.stopListening();
        postVideoFeedAdapter5.stopListening();
        postVideoFeedAdapter6.stopListening();
        postVideoFeedAdapter7.stopListening();
        postVideoFeedAdapter8.stopListening();
        postVideoFeedAdapter9.stopListening();
        postVideoFeedAdapter10.stopListening();
        postVideoFeedAdapter11.stopListening();
    }
}
