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
    private RecyclerView rvTutorialApprovals;
    private PostVideoFeedAdapter postVideoFeedAdapter;
    Button buttonApproved, buttonNotApproved;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tutorialapprovals, container, false);

        rvTutorialApprovals = v.findViewById(R.id.rv_tutorialApprovals);
        rvTutorialApprovals.setLayoutManager(new LinearLayoutManager(getContext()));

        buttonApproved = v.findViewById(R.id.btn_approved);
        buttonNotApproved = v.findViewById(R.id.btn_notApproved);

        FirebaseRecyclerOptions<PostVideoFeed> options =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child("Android").orderByChild("approvalStatus").equalTo("0"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter = new PostVideoFeedAdapter(options);
        rvTutorialApprovals.setAdapter(postVideoFeedAdapter);

        return v;
    }


    public void approve() {
        System.out.println("h");
    }

    @Override
    public void onStart() {
        super.onStart();
        postVideoFeedAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        postVideoFeedAdapter.stopListening();
    }
}
