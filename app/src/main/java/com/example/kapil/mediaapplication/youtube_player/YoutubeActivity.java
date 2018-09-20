package com.example.kapil.mediaapplication.youtube_player;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kapil.mediaapplication.R;
import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity {

    private View tv_youTube;

    private static final String TAG = "YoutubeView";

    private YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListner;
    private Media m;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_view);

        tv_youTube = findViewById(R.id.youtube_detail);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);


        m = getIntent().getParcelableExtra("Media_Video");
        tv_youTube.setVisibility(View.VISIBLE);
        Log.d(TAG, "onCreate: Video Started");


        mYouTubePlayerView = findViewById(R.id.youtube_detail);

        mOnInitializedListner = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                Log.d(TAG, "onInitializationSuccess: Done Initialize");

                youTubePlayer.loadVideo(m.VideoLink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Log.d(TAG, "onInitializationFailure: Failed to Initialize");
            }
        };


        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(),mOnInitializedListner);




    }
}
