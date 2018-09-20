package com.example.kapil.mediaapplication.recycler_view.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookRes {
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("booksList")
    public List<Books> booksObjectList;



    }
