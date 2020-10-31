package com.ahmed.eid.chattask.data;

import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.pojo.ScreenTwoResponse;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEndPointInterface {




    @GET("Screen_1.json")
    Call<ScreenOneResponse> getDataScreenOne();

    @GET("Screen_2.json")
    Observable<ScreenTwoResponse> getDataScreenTwo();

}
