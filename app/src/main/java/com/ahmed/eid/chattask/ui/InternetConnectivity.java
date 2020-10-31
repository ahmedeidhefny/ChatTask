package com.ahmed.eid.chattask.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class InternetConnectivity {

    private Context mContext;
    private static ConnectivityManager mConMgr;

    public InternetConnectivity(Context mContext) {
        this.mContext = mContext;
        mConMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public Boolean isConnected() {

        NetworkInfo activeNetwork = mConMgr.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

    }
}
