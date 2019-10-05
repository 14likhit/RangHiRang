package com.likhit.ranghirang;

import android.app.Application;

import com.likhit.ranghirang.sharedPreference.PreferenceHelper;

public class RangHiRang extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceHelper.initialize(this);
    }
}
