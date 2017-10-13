package com.lmc.bab.libdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.lmc.bab.libdemo.retrofit.RetrofitTestActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RetrofitTestActivity.enterActivity(MainActivity.this);
            }
        }, 1000);
//        doInit();
    }

    private void doInit(){
    }
}
