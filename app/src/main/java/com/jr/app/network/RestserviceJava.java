package com.jr.app.network;

import com.jr.app.models.ExampleData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by jonathanrichards on 01/08/2017.
 */

public class RestserviceJava implements RestServiceInterface {

    private String hello;
    @Override
    public void addUser(@NotNull ExampleData user) {

    }

    @NotNull
    @Override
    public List<ExampleData> getAllUsers() {
        return null;
    }


    private OkHttpClient getHttpAuthClient(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic("aUsername", "aPassword"));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        hello = "dsadsadsa";

        return okHttpClient;
    }
}
