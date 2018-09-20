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
import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.example.kapil.mediaapplication.recycler_view.callback.OnMediaCellClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomRvAdapter extends RecyclerView.Adapter<CustomRvAdapter.CustomRVViewHolder> {

    private Context mContext;
    private ArrayList<Media> mList;
    private OnMediaCellClick mListner;

    public CustomRvAdapter(Context mContext, ArrayList<Media> mList, OnMediaCellClick mListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListner = mListener;
    }

    @NonNull
    @Override
    public CustomRvAdapter.CustomRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_items, viewGroup, false);
        CustomRvAdapter.CustomRVViewHolder vh = new CustomRvAdapter.CustomRVViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRVViewHolder customRVViewHolder, int i) {
        final Media m = this.mList.get(i);
        customRVViewHolder.title.setText(m.Title);
        customRVViewHolder.year.setText(String.valueOf(m.Year));
        customRVViewHolder.genre.setText(m.Genre);
        customRVViewHolder.director.setText(m.Director);
        customRVViewHolder.rating.setRating(Float.parseFloat(m.Rating.toString())/2);
        customRVViewHolder.duration.setText(String.valueOf(m.Duration));

        Picasso.with(this.mContext)
                .load(m.ThumbnailImage)
                .into(customRVViewHolder.img);

        customRVViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onMovieCellClickedListener(m);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class CustomRVViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView genre;
        private TextView year;
        private RatingBar rating;
        private TextView director;
        private TextView duration;
        private LinearLayout ll;

        public CustomRVViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.rv_img);
            title = itemView.findViewById(R.id.rv_title);
            genre = itemView.findViewById(R.id.rv_genre);
            year = itemView.findViewById(R.id.rv_year);
            rating = itemView.findViewById(R.id.rv_rating);
            director = itemView.findViewById(R.id.rv_director);
            duration = itemView.findViewById(R.id.rv_duration);
            ll = itemView.findViewById(R.id.rv_ll);



        }
    }
}
