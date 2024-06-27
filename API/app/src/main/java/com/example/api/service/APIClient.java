package com.example.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static String baseURL = "https://65e1533ed3db23f7624ac737.mockapi.io/se160104/lab10/";
//    private static String baseURL = "https://667da8f9297972455f65f5be.mockapi.io/trainees/";
    private static Retrofit retrofit;
    public static Retrofit getClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
