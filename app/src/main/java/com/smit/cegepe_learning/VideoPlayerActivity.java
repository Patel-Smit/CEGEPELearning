package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    String videoFetchLink, videoFetchTitle, videoFetchDescription, videoFetchCreator, videoFetchCreatorId, videoFetchVideoId;
    TextView title, description, creator;
    Button btnDelete;

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
        btnDelete = findViewById(R.id.videoplayer_btn_delete);

        Intent i = getIntent();
        videoFetchLink = i.getStringExtra("linkValue");
        videoFetchTitle = i.getStringExtra("titleValue");
        videoFetchDescription = i.getStringExtra("descriptionValue");
        videoFetchCreator = i.getStringExtra("creatorValue");
        videoFetchCreatorId = i.getStringExtra("userIdValue");
        videoFetchVideoId = i.getStringExtra("videoIdValue");

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (videoFetchCreatorId.equals(firebaseUser.getUid()) || MainActivity.uusertype.toLowerCase().equals("admin")) {
            btnDelete.setVisibility(View.VISIBLE);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query query = ref.child("videos").orderByChild(videoFetchVideoId);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.child(videoFetchVideoId).getRef().removeValue();
                            Toast.makeText(getApplication(), "Video deleted Successfully", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

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
