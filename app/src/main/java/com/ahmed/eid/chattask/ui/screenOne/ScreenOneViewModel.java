package com.ahmed.eid.chattask.ui.screenOne;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.eid.chattask.data.APIClient;
import com.ahmed.eid.chattask.data.APIEndPointInterface;
import com.ahmed.eid.chattask.pojo.ScreenOneResponse;
import com.ahmed.eid.chattask.pojo.ScreenTwoResponse;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScreenOneViewModel extends ViewModel{
    private MutableLiveData<ScreenOneResponse> mData = new MutableLiveData<>();
    //private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ScreenOneViewModel() {
    }

    public MutableLiveData<ScreenOneResponse> getDataScreenOne() {

//        compositeDisposable.add(APIClient.getInstance().getDataScreenOne()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ScreenOneResponse>() {
//                    @Override
//                    public void accept(ScreenOneResponse screenOneResponse) throws Exception {
//                        mData.setValue(screenOneResponse);
//                    }
//                }));

        APIClient.getInstance().getDataScreenOne().enqueue(new Callback<ScreenOneResponse>() {
            @Override
            public void onResponse(Call<ScreenOneResponse> call, Response<ScreenOneResponse> response) {
                if (response.isSuccessful()) {
                    mData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ScreenOneResponse> call, Throwable t) {
                mData.setValue(null);
            }
        });

        return mData;
    }

}





