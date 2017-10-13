package com.lmc.bab.libdemo.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lmc.bab.lib.base.BaseActivity;
import com.lmc.bab.lib.retrofit.RetrofitManager;
import com.lmc.bab.libdemo.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by limin on 2017/10/10.
 */

public class RetrofitTestActivity extends BaseActivity {
    //view
    private TextView mTvSend;
    private TextView mTvResult;
    private Handler mHandler;
    //data

    public static void enterActivity(Context context){
        Intent intent = new Intent(context, RetrofitTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        initView();
        initEvent();
        initData();
    }

    private void initView(){
        mTvSend = (TextView) findViewById(R.id.tv_send);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mHandler = new Handler();
    }

    private void initEvent(){
        mTvSend.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_send:
                    doGet();
                    break;
            }
        }
    };

    private void initData(){

    }

    private void doSend(){
        Retrofit retrofit = RetrofitManager.getRetrofit();
        DeviceService service = retrofit.create(DeviceService.class);
        Map inMap = new HashMap();
        inMap.put("appVersion", "010000");
        Call<JsonObject> call = service.checkUpgrade(RetrofitManager.getBody(inMap), "d13eec76f6b24d5990a8a4a7111c93ed");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, final Response<JsonObject> response) {
                if(mHandler != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTvResult.setText(response.body().toString());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, final Throwable t) {
                if(mHandler != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTvResult.setText(t.getMessage());
                        }
                    });
                }
            }
        });
    }

    private void doGet(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "text/plain;charset=utf-8")
                                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                                .addHeader("Accept-Encoding", "none")
                                .addHeader("Accept-Language", "zh-CN,en-US;q=0.8")
                                .addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 7.0; zh-cn; Mi-4c Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.146 Mobile Safari/537.36 XiaoMi/MiuiBrowser/9.2.3")
                                .build();
                        okhttp3.Response response = chain.proceed(request);
                        return response;
                    }

                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
        DeviceService service = retrofit.create(DeviceService.class);
        Call<JsonObject> call = service.getUrl();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, final Response<JsonObject> response) {
                if(mHandler != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTvResult.setText(response.body().toString());
                        }
                    });
                }
            }

            @Override
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                if(mHandler != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTvResult.setText(t.getMessage());
                        }
                    });
                }
            }
        });
    }
}
