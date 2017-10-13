package com.lmc.bab.lib.base;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by limin on 2017/10/13.
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance;

    public static BaseApplication getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FileDownloader.setup(getApplicationContext());
    }
}
