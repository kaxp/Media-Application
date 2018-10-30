package com.example.kapil.mediaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kapil.mediaapplication.recycler_view.media.Books;
import com.squareup.picasso.Picasso;

public class Books_Detail_Activity extends AppCompatActivity {

    public TextView tv_title;
    public TextView tv_author;
    public RatingBar tv_ratingbar;
    public TextView tv_reviews;
    public TextView tv_description;
    public TextView tv_aboutAuthor;
    public ImageView tv_bannerImage;
    private ImageView btn_back;
    Books m;

    private static final String TAG = "Detailed_Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books__detail_);

        Log.d(TAG, "onCreate: Starting.");

        tv_title=findViewById(R.id.book_detail_title);
        tv_author = findViewById(R.id.book_detail_author);
        tv_ratingbar = findViewById(R.id.book_detail_ratingbar);
        tv_reviews = findViewById(R.id.book_detail_reviews);
        tv_description = findViewById(R.id.book_description);
        tv_aboutAuthor = findViewById(R.id.book_author_detail);
        tv_bannerImage = findViewById(R.id.books_banner);
        btn_back=findViewById(R.id.btn_back);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");


        Intent intent = getIntent();
        m = intent.getParcelableExtra("books");

        Picasso.with(this)
                .load(m.BannerImage)
                .into(tv_bannerImage);


        tv_title.setText(m.Title);
        tv_author.setText(m.Author);
        tv_ratingbar.setRating(Float.parseFloat(m.Rating.toString()));
        tv_reviews.setText(String.valueOf(m.Reviews));
        tv_description.setText(m.Description);
        tv_aboutAuthor.setText(m.AboutAuthor);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
