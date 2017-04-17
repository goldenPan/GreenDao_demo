package com.eleolive.greendao_demo;

import android.app.Application;
import android.content.Context;

/**
 * @project_name GreenDao_demo
 * @class name：com.eleolive.greendao_demo
 * @anthor yj
 * @time 2017-04-17 3:02
 * @class describe
 */
public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        sContext = getApplicationContext();
    }


    public static Context getContext() {
        return sContext;
    }
}
