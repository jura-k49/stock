package com.dpcsa.compon.single;

import android.content.Context;
import android.util.Log;

import com.dpcsa.compon.base.BaseDB;
import com.dpcsa.compon.base.DeclareScreens;
import com.dpcsa.compon.param.AppParams;
import com.dpcsa.compon.param.ParamModel;

public class DeclareParam {

    private static DeclareParam dp;
    private Context context;
    private ComponGlob componGlob;
    private ComponPrefTool preferences;
    private ComponTools componTools;

    public DeclareParam(Context context) {
        this.context = context;
        Injector.initInjector(context);
        componGlob = Injector.getComponGlob();
        preferences = Injector.getPreferences();
        componTools = Injector.getComponTools();
    }
    
    public static DeclareParam build(Context context) {
        if (dp == null) {
            dp = new DeclareParam(context);
        }
        return dp;
    }

    public DeclareParam setNetworkParams(AppParams params) {
        componGlob.appParams = params;
        if (params.defaultMethod != ParamModel.GET) {
            ParamModel.setDefaultMethod(params.defaultMethod);
        }
        return this;
    }

    public DeclareParam setDeclareScreens(DeclareScreens declareScreens) {
        declareScreens.initScreen();
        return this;
    }

    public DeclareParam addParam(String name, String value) {
        componGlob.addParamValue(name, value);
        return this;
    }

    public DeclareParam setLocale(String locale, String nameParam) {
        String loc = preferences.getLocale();
        if (loc.length() == 0 ) {
            loc = locale;
            preferences.setLocale(locale);
        }
        componGlob.addParamValue(nameParam, loc);
        return this;
    }

    public DeclareParam setDB(BaseDB baseDB) {
        Injector.setBaseDB(baseDB);
        return this;
    }

    public ComponTools getComponTools() {
        return componTools;
    }
}
