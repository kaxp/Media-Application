package com.example.kapil.mediaapplication.services;

import com.example.kapil.mediaapplication.recycler_view.media.TV_ShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI_TV_Shows {
    @GET("tv_show/getAllTV_Show")
    Call<TV_ShowResponse> fetchAllTV_Show();
}
