package com.smit.cegepe_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CourseVideoFeedActivity extends AppCompatActivity {
    private RecyclerView rvFeed;
    private PostVideoFeedAdapter postVideoFeedAdapter;
    String categoryFetchName;
    TextView nothingToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefsTheme = getSharedPreferences("saveTheme", MODE_PRIVATE);
        Boolean restoredTheme = prefsTheme.getBoolean("valueTheme", true);
        if (restoredTheme) {
            setTheme(R.style.MainActivityDarkThemeActionBar);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_video_feed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvFeed = findViewById(R.id.videofeed_rv_feed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        Intent i = getIntent();
        categoryFetchName = i.getStringExtra("catregoryValue");
        setTitle(categoryFetchName);

        FirebaseRecyclerOptions<PostVideoFeed> options =
                new FirebaseRecyclerOptions.Builder<PostVideoFeed>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videos").child(categoryFetchName).orderByChild("approvalStatus").equalTo("1"), PostVideoFeed.class)
                        .build();

        postVideoFeedAdapter = new PostVideoFeedAdapter(options);
        rvFeed.setAdapter(postVideoFeedAdapter);
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
