package com.example.pruebaminimo2;

import com.example.pruebaminimo2.github.GitHub;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private GitHub myApi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GitHub.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(GitHub.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public GitHub getMyApi() {
        return myApi;
    }
}
