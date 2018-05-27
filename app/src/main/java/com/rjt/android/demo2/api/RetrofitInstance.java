package com.rjt.android.demo2.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit sRetrofit = null;
    private static final String BASE_URL = "http://gank.io/api/";
    public static Retrofit getRetrofitIns(){
        if(sRetrofit==null){
            sRetrofit = new retrofit2.Retrofit
                    .Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return sRetrofit;
    }
}
