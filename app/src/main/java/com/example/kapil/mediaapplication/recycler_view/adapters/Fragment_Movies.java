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

import com.example.kapil.mediaapplication.Detailed_Activity;
import com.example.kapil.mediaapplication.R;
import com.example.kapil.mediaapplication.ServiceSingleton;
import com.example.kapil.mediaapplication.recycler_view.media.Books;
import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.example.kapil.mediaapplication.recycler_view.callback.OnMediaCellClick;
import com.example.kapil.mediaapplication.recycler_view.media.MoviesRes;
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
public class Fragment_Movies extends Fragment implements OnMediaCellClick {

    public RecyclerView movies;
    public ArrayList<Media> mList;
    public CustomRvAdapter mAdapter;


    private static final String TAG = Fragment_Movies.class.getSimpleName();

    public Fragment_Movies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        movies = view.findViewById(R.id.rv_movies);

        movies.setHasFixedSize(true);

        mList = new ArrayList<>();
        callAPI();

//        Media m1 = new Media("http://t0.gstatic.com/images?q=tbn:ANd9GcR7gFSo85szXEdjrPsBO992eUhkX0qwALaQWdCj-BOfcaU0kpH-","http://t0.gstatic.com/images?q=tbn:ANd9GcR7gFSo85szXEdjrPsBO992eUhkX0qwALaQWdCj-BOfcaU0kpH-","Your Name",2016,"Drama/Fantasy","Makoto Shinkai" ,8.4,"1h 52m", "s0wTdCQoc2k");
//        Media m2 = new Media("http://t1.gstatic.com/images?q=tbn:ANd9GcS6MveoDoJOg9-wMvtHE4ak_-HDZeYS1egb9PwRcf8lhrtcppMc","http://t1.gstatic.com/images?q=tbn:ANd9GcS6MveoDoJOg9-wMvtHE4ak_-HDZeYS1egb9PwRcf8lhrtcppMc","Spirated Away",2001, "Fantasy/Mystery ", "Hayao Miyazaki", 8.6, "2h 5m","ByXuk9QqQkk");
//        Media m3 = new Media("http://t2.gstatic.com/images?q=tbn:ANd9GcTvrIHJfasS6poy34esN1O5hZonXaiqfEZb4WbnbAa9qJCIL8_9","http://t2.gstatic.com/images?q=tbn:ANd9GcTvrIHJfasS6poy34esN1O5hZonXaiqfEZb4WbnbAa9qJCIL8_9","Deadpool", 2016,"Science fiction film/Action", "Tim Miller", 8.1,  "1h 49m","Xithigfg7dA");
//        Media m4 = new Media("https://j.b5z.net/i/u/6127364/i/inception_75_ezr2.jpg","https://j.b5z.net/i/u/6127364/i/inception_75_ezr2.jpg","Inception", 2010, "Science fiction film/Thriller", "Christopher Nolan", 8.8,  "2h 28m","YoHD9XEInc0" );
//        Media m5 = new Media("http://www.westalexfilms.com/wp-content/uploads/2016/10/WestalexFilms-The-Prestige-Movie-Poster-Are-You-Watching-Closely.jpg","http://www.westalexfilms.com/wp-content/uploads/2016/10/WestalexFilms-The-Prestige-Movie-Poster-Are-You-Watching-Closely.jpg","The Prestige", 2006, "Science fiction film/Thriller ", "Christopher Nolan", 8.5, "2h 10m","o4gHCmTQDVI");
//        Media m6 = new Media("https://s3-ap-southeast-2.amazonaws.com/fna-wordpress-website06/wp-content/uploads/2018/08/07105721/Shutter-Island-1440x960.jpg","https://s3-ap-southeast-2.amazonaws.com/fna-wordpress-website06/wp-content/uploads/2018/08/07105721/Shutter-Island-1440x960.jpg","Shutter Island", 2010 , "Thriller/Drama", "Martin Scorsese", 8.1, "2h 18m","YDGldPitxic");
//        Media m7 = new Media("https://m.media-amazon.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_UY209_CR0,0,140,209_AL_.jpg","https://m.media-amazon.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_UY209_CR0,0,140,209_AL_.jpg","Avatar",2009,"Action, Adventure, Fantasy","James Cameron ",7.8,"162min","5PSNL1qE6VY");
//        Media m8 = new Media("https://m.media-amazon.com/images/M/MV5BYzllMjJkODAtYjMwMi00YmNhLWFhYzAtZjZjODg5YzEwOGUwXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UY209_CR1,0,140,209_AL_.jpg","https://m.media-amazon.com/images/M/MV5BYzllMjJkODAtYjMwMi00YmNhLWFhYzAtZjZjODg5YzEwOGUwXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UY209_CR1,0,140,209_AL_.jpg","V for Vendetta",2005, "Action, Drama, Sci-Fi"," James McTeigue",8.2,"132min","k_13fFIrhPk");
//        Media m9 = new Media("https://m.media-amazon.com/images/M/MV5BMTY5MzYzNjc5NV5BMl5BanBnXkFtZTYwNTUyNTc2._V1_UY209_CR0,0,140,209_AL_.jpg","https://m.media-amazon.com/images/M/MV5BMTY5MzYzNjc5NV5BMl5BanBnXkFtZTYwNTUyNTc2._V1_UY209_CR0,0,140,209_AL_.jpg","Catch Me If You Can",2002,"Biography, Crime, Drama","Steven Spielberg ",8.1,"141min","71rDQ7z4eFg");
//        Media m10 = new Media("https://m.media-amazon.com/images/M/MV5BN2Y5ZTU4YjctMDRmMC00MTg4LWE1M2MtMjk4MzVmOTE4YjkzXkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_UY209_CR0,0,140,209_AL_.jpg","https://m.media-amazon.com/images/M/MV5BN2Y5ZTU4YjctMDRmMC00MTg4LWE1M2MtMjk4MzVmOTE4YjkzXkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_UY209_CR0,0,140,209_AL_.jpg","Cast Away",2000,"Adventure, Drama, Romance","Robert Zemeckis",7.8,"143min","PJvosb4UCLs");
//
//        mList.add(m1);
//        mList.add(m2);
//        mList.add(m3);
//        mList.add(m4);
//        mList.add(m5);
//        mList.add(m6);
//        mList.add(m7);
//        mList.add(m8);
//        mList.add(m9);
//        mList.add(m10);


        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        movies.setLayoutManager(lm);

        mAdapter = new CustomRvAdapter(getContext(), mList, this);
        movies.setAdapter(mAdapter);


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

        ServiceAPI_Movies mServiceAPI = mRetrofit.create(ServiceAPI_Movies.class);
        Call<MoviesRes> call = mServiceAPI.fetchAllMovies();
        call.enqueue(new Callback<MoviesRes>() {
            @Override
            public void onResponse(Call<MoviesRes> call, Response<MoviesRes> response) {

                MoviesRes res = response.body();

                if (res.status) {
                    mList.addAll(res.movieObjectList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MoviesRes> call, Throwable t) {
                showAlert();
            }
        });


    }

    @Override
    public void onMovieCellClickedListener(Media m) {
        Intent intent = new Intent(getContext(), Detailed_Activity.class);
        intent.putExtra("media", m);
        startActivity(intent);
    }

    public void showAlert() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("No Internet Connection")
                .setMessage("Please connect to Internet")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        callAPI();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }
}
