package com.example.kapil.mediaapplication.recycler_view.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TV_ShowResponse {
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("tv_showList")
    public List<Media> tv_showObjectList;
}
