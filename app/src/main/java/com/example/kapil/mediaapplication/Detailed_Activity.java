package com.example.kapil.mediaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.squareup.picasso.Picasso;

import com.example.kapil.mediaapplication.youtube_player.*;
import java.text.NumberFormat;

public class Detailed_Activity extends YouTubeBaseActivity {


    public TextView tv_title;
    public TextView tv_year;
    public TextView tv_rating;
    public TextView tv_reviews;
    public TextView tv_genre;
    public TextView tv_duration;
    public TextView tv_summary;
    private ImageView img_thumb;
    private ImageView thumbnailImage;
    private Button detail_button;
    private ImageView btn_back;
    Media m;



    private static final String TAG = "Detailed_Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_);

        Log.d(TAG, "onCreate: Starting.");


        tv_title=findViewById(R.id.detail_title);
        tv_year = findViewById(R.id.detail_year);
        tv_rating= findViewById(R.id.detail_rating);
        tv_reviews=findViewById(R.id.detail_review);
        tv_genre = findViewById(R.id.detail_genre);
        tv_duration = findViewById(R.id.detail_duration);
        tv_summary = findViewById(R.id.detail_summary);
        thumbnailImage = findViewById(R.id.detail_image);
        detail_button= findViewById(R.id.detail_button);
        img_thumb = findViewById(R.id.img_thumb);

        btn_back=findViewById(R.id.btn_back);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Log.d(TAG, "onClick: Initializing YouTube Player");
//              mYouTubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInitializedListner);
//
//              mYouTubePlayerView.setVisibility(View.VISIBLE);

                Intent intent = new Intent(Detailed_Activity.this,YoutubeActivity.class);
                intent.putExtra("Media_Video", m);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        m = intent.getParcelableExtra("media");


        tv_title.setText(m.Title);

        Picasso.with(this)
                .load(m.ThumbnailImage)
                .into(thumbnailImage);

        tv_year.setText(String.valueOf(m.Year));

        tv_rating.setText(String.valueOf(m.Rating));


        NumberFormat f = NumberFormat.getInstance();

        Picasso.with(this)
                .load("https://img.youtube.com/vi/"+m.VideoLink+"/0.jpg")
                .into(img_thumb);


       tv_summary.setText(m.Summary);



        tv_genre.setText(m.Genre);

        tv_duration.setText(m.Duration);






    }
    }

