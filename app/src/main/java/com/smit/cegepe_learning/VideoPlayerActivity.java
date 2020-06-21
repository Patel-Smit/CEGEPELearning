package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    String videoFetchLink, videoFetchTitle, videoFetchDescription;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        title = findViewById(R.id.videoplayer_title);
        description = findViewById(R.id.videoplayer_description);

        Intent i = getIntent();
        videoFetchLink = i.getStringExtra("linkValue");
        videoFetchTitle = i.getStringExtra("titleValue");
        videoFetchDescription = i.getStringExtra("descriptionValue");

        title.setText(videoFetchTitle);
        description.setText(videoFetchDescription);

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
