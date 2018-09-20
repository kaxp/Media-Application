package com.example.kapil.mediaapplication;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceSingleton {


    private static Retrofit mRetrofit;


    private static final String TAG = ServiceSingleton.class.getSimpleName();

    public static Retrofit getInstance(){
        if (mRetrofit == null) {

            HttpLoggingInterceptor http = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e(TAG, "log: " + message );
                }
            });

            http.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient c = new OkHttpClient.Builder()
                    .addInterceptor(http)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://pure-island-11123.herokuapp.com/")
                    .client(c)
                    .build();


        }

        return mRetrofit;
    }
}
