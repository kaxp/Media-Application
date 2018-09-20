package com.example.kapil.mediaapplication.recycler_view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kapil.mediaapplication.R;
import com.example.kapil.mediaapplication.recycler_view.callback.OnBookCellClick;
import com.example.kapil.mediaapplication.recycler_view.callback.OnMediaCellClick;
import com.example.kapil.mediaapplication.recycler_view.media.Books;
import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomBooksAdapter extends RecyclerView.Adapter<CustomBooksAdapter.CustomBooksViewHolder> {

    private Context mContext;
    private ArrayList<Books> bList;
    private OnBookCellClick mListner;

    public CustomBooksAdapter(Context mContext, ArrayList<Books> bList, OnBookCellClick mListner) {
        this.mContext = mContext;
        this.bList = bList;
        this.mListner = mListner;
    }

    @NonNull
    @Override
    public CustomBooksAdapter.CustomBooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.books_list_items, parent, false);
        CustomBooksAdapter.CustomBooksViewHolder vh = new CustomBooksAdapter.CustomBooksViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomBooksViewHolder customBooksViewHolder, int position) {

        final Books m = this.bList.get(position);
        customBooksViewHolder.title.setText(m.Title);
        customBooksViewHolder.author.setText(m.Author);

        customBooksViewHolder.rating.setRating(Float.parseFloat(m.Rating.toString()));
     
        customBooksViewHolder.reviews.setText(String.valueOf(m.Reviews));
        Picasso.with(this.mContext)
                .load(m.BannerImage)
                .into(customBooksViewHolder.bannerImage);

        customBooksViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onBookCellClickedListner(m);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bList != null ? bList.size() : 0;
    }

    public class CustomBooksViewHolder extends RecyclerView.ViewHolder {
        private ImageView bannerImage;
        private TextView title;
        private TextView author;
        private RatingBar rating;
        private TextView reviews;

        private LinearLayout ll;

        public CustomBooksViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerImage = itemView.findViewById(R.id.books_banner);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            rating = itemView.findViewById(R.id.books_rating);
            reviews = itemView.findViewById(R.id.books_reviews);

            ll = itemView.findViewById(R.id.books_ll);

        }
    }
}
