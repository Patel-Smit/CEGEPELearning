package com.smit.cegepe_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CourseVideoFeedActivity extends AppCompatActivity {
    private RecyclerView rvFeed;
    private PostVideoFeedAdapter postVideoFeedAdapter;
    String categoryFetchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_video_feed);

        rvFeed = findViewById(R.id.videofeed_rv_feed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        Intent i = getIntent();
        categoryFetchName = i.getStringExtra("catregoryValue");

        FirebaseRecyclerOptions<PostVideoFeed> options =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child(categoryFetchName), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter = new PostVideoFeedAdapter(options);
        rvFeed.setAdapter(postVideoFeedAdapter);

        System.out.println(categoryFetchName);
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
