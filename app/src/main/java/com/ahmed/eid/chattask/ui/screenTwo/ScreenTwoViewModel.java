package com.ahmed.eid.chattask.ui.screenTwo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.eid.chattask.data.APIClient;
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

public class ScreenTwoViewModel extends ViewModel {

    private MutableLiveData<ScreenTwoResponse> mData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ScreenTwoViewModel() {
    }

    public MutableLiveData<ScreenTwoResponse> getDataScreenTwo() {

        compositeDisposable.add(APIClient.getInstance().getDataScreenTwo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<ScreenTwoResponse>() {
            @Override
            public void accept(ScreenTwoResponse mAllData) throws Exception {

                mData.setValue(mAllData);

            }
        }));

//        APIClient.getInstance().getDataScreenTwo().enqueue(new Callback<ScreenTwoResponse>() {
//            @Override
//            public void onResponse(Call<ScreenTwoResponse> call, Response<ScreenTwoResponse> response) {
//                if (response.isSuccessful()) {
//                    mData.setValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ScreenTwoResponse> call, Throwable t) {
//                mData.setValue(null);
//            }
//        });

        return mData;
    }
}
