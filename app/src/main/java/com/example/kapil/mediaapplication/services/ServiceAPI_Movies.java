package com.example.kapil.mediaapplication.services;

import com.example.kapil.mediaapplication.recycler_view.media.MoviesRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI_Movies {
    @GET("movies/getAllMovies")
    Call <MoviesRes> fetchAllMovies();
}
