package com.example.kapil.mediaapplication.recycler_view.adapters;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kapil.mediaapplication.Books_Detail_Activity;
import com.example.kapil.mediaapplication.Detailed_Activity;
import com.example.kapil.mediaapplication.R;
import com.example.kapil.mediaapplication.ServiceSingleton;
import com.example.kapil.mediaapplication.recycler_view.callback.OnBookCellClick;
import com.example.kapil.mediaapplication.recycler_view.media.BookRes;
import com.example.kapil.mediaapplication.recycler_view.media.Books;
import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.example.kapil.mediaapplication.recycler_view.media.MoviesRes;
import com.example.kapil.mediaapplication.services.ServiceAPI_Books;
import com.example.kapil.mediaapplication.services.ServiceAPI_Movies;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Books extends Fragment implements OnBookCellClick {
    public RecyclerView books;
    public ArrayList<Books> bList;
    public CustomBooksAdapter mAdapter;


    private static final String TAG = Fragment_Movies.class.getSimpleName();
    public Fragment_Books() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        books = view.findViewById(R.id.rv_books);
        books.setHasFixedSize(true);
        bList = new ArrayList<>();

        callAPI();


        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        books.setLayoutManager(lm);

        mAdapter = new CustomBooksAdapter(getContext(), bList, this);
        books.setAdapter(mAdapter);
    }

    private void callAPI() {

        //The below code is added to a Service Singleton class so that it need not be used in all three classes.
//        HttpLoggingInterceptor i = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.e(TAG, "connection: " + message);
//            }
//        });
//
//        i.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient c = new OkHttpClient.Builder()
//                .addInterceptor(i)
//                .build();
//
//        Retrofit mRetrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://pure-island-11123.herokuapp.com/")
//                .client(c)
//                .build();

        Retrofit mRetrofit = ServiceSingleton.getInstance();

        ServiceAPI_Books mServiceAPI = mRetrofit.create(ServiceAPI_Books.class);
        Call<BookRes> call = mServiceAPI.fetchAllBooks();
        call.enqueue(new Callback<BookRes>() {
            @Override
            public void onResponse(Call<BookRes> call, Response<BookRes> response) {

                BookRes res = response.body();

                if (res.status) {
                    bList.addAll(res.booksObjectList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BookRes> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage() );
            }
        });
    }

    @Override
    public void onBookCellClickedListner(Books m) {
        Intent intent = new Intent(getContext(), Books_Detail_Activity.class);
        intent.putExtra("books", m);
        startActivity(intent);
    }


}
