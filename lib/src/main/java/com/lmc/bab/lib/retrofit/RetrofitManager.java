package com.lmc.bab.lib.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by limin on 2017/10/10.
 */

public class RetrofitManager {
    private static final int TimeOut = 15;

    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.42.37.86:10601/syj-rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        return retrofit;
    }

    public static RequestBody getBody(Map inMap){
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(inMap));
        return body;
    }

    public static OkHttpClient getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("DeviceId", "a3d9cc8b-238f-30f1-9e1d-87756bca48ca")
                                .addHeader("Device-Code", "XKYD58928869")
                                .build();
                        Response response = chain.proceed(request);
                        return response;
                    }

                })
                .connectTimeout(TimeOut, TimeUnit.SECONDS)
                .writeTimeout(TimeOut, TimeUnit.SECONDS)
                .readTimeout(TimeOut, TimeUnit.SECONDS)
                .build();

        return httpClient;
    }
}
