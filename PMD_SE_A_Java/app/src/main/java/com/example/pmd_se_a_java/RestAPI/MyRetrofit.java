package com.example.pmd_se_a_java.RestAPI;

import androidx.concurrent.futures.ResolvableFuture;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit  {
    public static Retrofit retrofit;
    public static String URL = "https://jsonplaceholder.typicode.com/";
    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
