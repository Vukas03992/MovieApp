package com.prvulovic.vukasin.data.network.state;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NetworkStateManager {

    private final ConnectivityManager manager;

    @Inject
    public NetworkStateManager(Context context) {
        manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean getConnection(){
        if (manager!=null){
            NetworkInfo networkInfo=manager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }
}
