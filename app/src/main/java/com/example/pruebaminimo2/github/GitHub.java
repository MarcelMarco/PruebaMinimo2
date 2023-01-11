package com.example.pruebaminimo2.github;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHub {

    String URL = "https://api.github.com/";

    @GET("/users/{owner}/followers")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
