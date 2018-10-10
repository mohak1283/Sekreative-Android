package com.sekreative.sekreative;

import android.app.Application;

import com.sekreative.sekreative.utils.PrefUtils;

public class SeKreative extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PrefUtils.init(this);
    }
}
