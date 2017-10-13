package com.lmc.bab.libdemo.retrofit;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by limin on 2017/10/10.
 */

public interface DeviceService {
    @POST("app/version/latest")
    Call<JsonObject> checkUpgrade(@Body RequestBody body, @Header("Internal-Code") String code);

    @GET("lmcBab/libDemo/master/app/src/main/assets/test.txt")
    Call<JsonObject> getUrl();
}
