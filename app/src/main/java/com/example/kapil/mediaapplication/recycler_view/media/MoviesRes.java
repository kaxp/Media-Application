package com.example.kapil.mediaapplication.recycler_view.media;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesRes {
    @SerializedName("status")
    @Expose
    public  boolean status;
    @SerializedName("moviesList")
    public List<Media> movieObjectList;
}
