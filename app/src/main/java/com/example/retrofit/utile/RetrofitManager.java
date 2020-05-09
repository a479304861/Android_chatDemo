package com.example.retrofit.utile;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

   private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(StaticUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
