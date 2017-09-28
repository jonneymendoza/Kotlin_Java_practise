package com.jr.app.network;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.jr.app.models.ExampleData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;

/**
 * Created by jonathanrichards on 01/08/2017.
 */

public class RestserviceJava implements RestServiceInterface {

    private String hello;
    @Override
    public void addUser(@NotNull ExampleData user) {

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

        EditText editText = new EditText(null);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == 2){
                    int sup = 2;
                }

                return false;
            }
        });


        return okHttpClient;
    }

    @Override
    public void getAllUsers(@NotNull Callback<List<ExampleData>> callback) {

    }
}
