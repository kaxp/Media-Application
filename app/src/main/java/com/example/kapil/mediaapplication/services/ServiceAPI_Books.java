package com.example.kapil.mediaapplication.services;

import com.example.kapil.mediaapplication.recycler_view.media.BookRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI_Books {
    @GET("books/getAllBooks")
    Call<BookRes> fetchAllBooks();
}
