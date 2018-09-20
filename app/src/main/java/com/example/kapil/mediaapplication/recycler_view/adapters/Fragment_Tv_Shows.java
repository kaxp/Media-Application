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
import com.example.kapil.mediaapplication.recycler_view.media.Media;
import com.example.kapil.mediaapplication.recycler_view.callback.OnMediaCellClick;
import com.example.kapil.mediaapplication.recycler_view.media.TV_ShowResponse;
import com.example.kapil.mediaapplication.services.ServiceAPI_TV_Shows;

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
public class Fragment_Tv_Shows extends Fragment implements OnMediaCellClick{


    public RecyclerView tv_shows;
    public ArrayList<Media> sList;
    public CustomRvAdapter mAdapter;
    private static final String TAG = Fragment_Movies.class.getSimpleName();

    public Fragment_Tv_Shows() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tv_shows = view.findViewById(R.id.rv_tv_shows);
        tv_shows.setHasFixedSize(true);
        sList =  new ArrayList<>();
        callAPI();

//        Media s1 = new Media("https://m.media-amazon.com/images/M/MV5BMjE3NTQ1NDg1Ml5BMl5BanBnXkFtZTgwNzY2NDA0MjI@._V1_QL50_SY1000_CR0,0,674,1000_AL_.jpg","https://m.media-amazon.com/images/M/MV5BMjE3NTQ1NDg1Ml5BMl5BanBnXkFtZTgwNzY2NDA0MjI@._V1_QL50_SY1000_CR0,0,674,1000_AL_.jpg","Games of Thrones",2011,"Action, Adventure, Drama"," David Benioff",9.5,"~50mins","v5gsVRxzzI4");
//        Media s2 = new Media("https://m.media-amazon.com/images/M/MV5BMTcwMDAzMDk3OF5BMl5BanBnXkFtZTgwMjY0MzcyNjM@._V1_QL50_SY1000_CR0,0,674,1000_AL_.jpg","https://m.media-amazon.com/images/M/MV5BMTcwMDAzMDk3OF5BMl5BanBnXkFtZTgwMjY0MzcyNjM@._V1_QL50_SY1000_CR0,0,674,1000_AL_.jpg","The Walking Dead",2011,"Drama, Horror, Sci-Fi 0","Frank Darabont",8.6,"~55mins","R1v0uFms68U");
//        Media s3 = new Media("https://m.media-amazon.com/images/M/MV5BMTUyODg0NzM4OV5BMl5BanBnXkFtZTgwMDkyOTg1MzI@._V1_UY268_CR16,0,182,268_AL__QL50.jpg","https://m.media-amazon.com/images/M/MV5BMTUyODg0NzM4OV5BMl5BanBnXkFtZTgwMDkyOTg1MzI@._V1_UY268_CR16,0,182,268_AL__QL50.jpg","Supernatural",2005," Drama, Fantasy, Horror","Eric Kripke", 8.5, "~1hr","t-775JyzDTk");
//        Media s4 = new Media("https://m.media-amazon.com/images/M/MV5BNDVkYjU0MzctMWRmZi00NTkxLTgwZWEtOWVhYjZlYjllYmU4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UY268_CR0,0,182,268_AL__QL50.jpg","https://m.media-amazon.com/images/M/MV5BNDVkYjU0MzctMWRmZi00NTkxLTgwZWEtOWVhYjZlYjllYmU4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UY268_CR0,0,182,268_AL__QL50.jpg","Friends", 1994, "Comedy, Romance ","David Crane",8.9,"22min","hDNNmeeJs1Q");
//        Media s5 = new Media("https://m.media-amazon.com/images/M/MV5BMjUwMDgzOTg3Nl5BMl5BanBnXkFtZTgwNTI4MDk5MzI@._V1_UX182_CR0,0,182,268_AL__QL50.jpg","https://m.media-amazon.com/images/M/MV5BMjUwMDgzOTg3Nl5BMl5BanBnXkFtZTgwNTI4MDk5MzI@._V1_UX182_CR0,0,182,268_AL__QL50.jpg","Stranger Things",2016," Drama, Fantasy, Horror","Matt Duffer", 8.9,"51min","XWxyRG_tckY");
//        Media s6 = new Media("https://m.media-amazon.com/images/M/MV5BMTk1MjYzOTU2Nl5BMl5BanBnXkFtZTgwMzAxMTg5MTE@._V1_UX182_CR0,0,182,268_AL__QL50.jpg","https://m.media-amazon.com/images/M/MV5BMTk1MjYzOTU2Nl5BMl5BanBnXkFtZTgwMzAxMTg5MTE@._V1_UX182_CR0,0,182,268_AL__QL50.jpg", "Suits",2011," Comedy, Drama ","Aaron Korsh", 8.6, "44min","85z53bAebsI");
//        Media s7 = new Media("https://m.media-amazon.com/images/M/MV5BNDc3NzQxODAwOV5BMl5BanBnXkFtZTgwODkwMjU2MzI@._V1_UX182_CR0,0,182,268_AL__QL50.jpg","https://m.media-amazon.com/images/M/MV5BNDc3NzQxODAwOV5BMl5BanBnXkFtZTgwODkwMjU2MzI@._V1_UX182_CR0,0,182,268_AL__QL50.jpg", "Brooklyn Nine-Nine",2013,"Comedy, Crime"," Andre Braugher",8.4,"22min","sEOuJ4z5aTc");
//        Media s8 = new Media("https://m.media-amazon.com/images/M/MV5BZDNhNzhkNDctOTlmOS00NWNmLWEyODQtNWMxM2UzYmJiNGMyXkEyXkFqcGdeQXVyNTMxMjgxMzA@._V1_UY98_CR1,0,67,98_AL_.jpg","https://m.media-amazon.com/images/M/MV5BZDNhNzhkNDctOTlmOS00NWNmLWEyODQtNWMxM2UzYmJiNGMyXkEyXkFqcGdeQXVyNTMxMjgxMzA@._V1_UY98_CR1,0,67,98_AL_.jpg", "Breaking Bad",2008,"Crime, Drama, Thriller","Bryan Cranston",9.5,"49min","HhesaQXLuRY");
//        Media s9 = new Media("https://m.media-amazon.com/images/M/MV5BMjRiNDRhNGUtMzRkZi00MThlLTg0ZDMtNjc5YzFjYmFjMmM4XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY98_CR0,0,67,98_AL_.jpg","https://m.media-amazon.com/images/M/MV5BMjRiNDRhNGUtMzRkZi00MThlLTg0ZDMtNjc5YzFjYmFjMmM4XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY98_CR0,0,67,98_AL_.jpg","Rick and Morty",2013,"Animation, Adventure, Comedy"," Justin Roiland",9.3,"23min","WNhH00OIPP0");
//        Media s10 = new Media("https://m.media-amazon.com/images/M/MV5BMjA3MTE5ODM3M15BMl5BanBnXkFtZTgwNTIyMjQ5NTM@._V1_UX67_CR0,0,67,98_AL_.jpg","https://m.media-amazon.com/images/M/MV5BMjA3MTE5ODM3M15BMl5BanBnXkFtZTgwNTIyMjQ5NTM@._V1_UX67_CR0,0,67,98_AL_.jpg","Orange Is the New Black",2013,"Comedy, Crime, Drama","Taylor Schilling",8.2,"59min","vY0qzXi5oJg");
//
//        sList.add(s1);
//        sList.add(s2);
//        sList.add(s3);
//        sList.add(s4);
//        sList.add(s5);
//        sList.add(s6);
//        sList.add(s7);
//        sList.add(s8);
//        sList.add(s9);
//        sList.add(s10);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        tv_shows.setLayoutManager(lm);

        mAdapter = new CustomRvAdapter(getContext(),sList, this);
        tv_shows.setAdapter(mAdapter);

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
        ServiceAPI_TV_Shows mServiceAPI = mRetrofit.create(ServiceAPI_TV_Shows.class);
        Call<TV_ShowResponse> call = mServiceAPI.fetchAllTV_Show();
        call.enqueue(new Callback<TV_ShowResponse>() {
            @Override
            public void onResponse(Call<TV_ShowResponse> call, Response<TV_ShowResponse> response) {
                TV_ShowResponse res = response.body();

                if (res.status){
                    sList.addAll(res.tv_showObjectList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TV_ShowResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onMovieCellClickedListener(Media m) {
        Intent intent = new Intent(getContext(), Detailed_Activity.class);
        intent.putExtra("media",m);
        startActivity(intent);
    }


    }

