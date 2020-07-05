package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    String videoFetchLink, videoFetchTitle, videoFetchDescription, videoFetchCreator;
    TextView title, description, creator;

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
        setContentView(R.layout.activity_video_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.videoplayer_title);
        description = findViewById(R.id.videoplayer_description);
        creator = findViewById(R.id.videoplayer_creator);

        Intent i = getIntent();
        videoFetchLink = i.getStringExtra("linkValue");
        videoFetchTitle = i.getStringExtra("titleValue");
        videoFetchDescription = i.getStringExtra("descriptionValue");
        videoFetchCreator = i.getStringExtra("creatorValue");
        System.out.println(videoFetchCreator);

        title.setText(videoFetchTitle);
        description.setText(videoFetchDescription);
        creator.setText(videoFetchCreator);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.videoplayer_player);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = videoFetchLink;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}
