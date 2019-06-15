package com.dpcsa.stock;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.dpcsa.compon.single.DeclareParam;
import com.dpcsa.stock.param.SkladAppParams;
import com.dpcsa.stock.param.SkladDeclareScreens;

public class SkladApp extends MultiDexApplication {
    private static SkladApp instance;
    private Context context;

    public static SkladApp getInstance() {
        if (instance == null) {
            instance = new SkladApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

        DeclareParam.build(context)
                .setNetworkParams(new SkladAppParams())
                .setDeclareScreens(new SkladDeclareScreens());
    }

}
