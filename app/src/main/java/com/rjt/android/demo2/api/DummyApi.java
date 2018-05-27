package com.rjt.android.demo2.api;

import com.rjt.android.demo2.pojo.Example;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DummyApi {

    @GET("data/{type}/{count}/{page}")
    Observable<Example> getExample(@Path("type") String type,
                                   @Path("count") int count,
                                   @Path("page") int page);

}
