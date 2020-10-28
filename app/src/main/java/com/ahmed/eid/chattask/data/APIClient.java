package com.ahmed.eid.chattask.data;

import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.pojo.ScreenTwoResponse;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class APIClient {

    private static final String BASE_URL = "https://emlenotes.com/challenges/android/";
    private APIEndPointInterface apiEndPointInterface;
    private static APIClient mINSTANCE;


    public APIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiEndPointInterface = retrofit.create(APIEndPointInterface.class);
    }

    public static APIClient getInstance (){

        if (null == mINSTANCE){
            mINSTANCE = new APIClient();
        }

        return mINSTANCE;
    }

    public Call<ScreenOneResponse> getDataScreenOne(){
        return apiEndPointInterface.getDataScreenOne();
    }

    public Call<ScreenTwoResponse> getDataScreenTwo(){
        return apiEndPointInterface.getDataScreenTwo();
    }
}
