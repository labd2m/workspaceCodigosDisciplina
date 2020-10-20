package com.example.bancodadosexample;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
        Log.i("BANCO_DADOS", "onCreate() " + getClass().getName() + " para obter context!");
    }

    public static Context getAppContext(){
        return MyApp.context;
    }
}
