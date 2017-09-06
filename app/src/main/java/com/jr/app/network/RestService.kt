package com.jr.app.network

import com.jr.app.BuildConfig
import com.jr.app.models.ExampleData
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jonathanrichards on 28/07/2017.
 */

class RestService : RestServiceInterface {

    val authorizeHeader: String = "Authorization"

    val userName: String = "d9d14f34-f6c6-4b46-b47e-1617ef3774ed"

    val password: String = "07aa135d-2db9-46af-bfa3-54f174215aa5"

    var callbackResponse: Callback<Response>? = null

    public fun addNewUserService(user: ExampleData, callback: Callback<Response>) {
        callbackResponse = callback;
        addUser(user)

    }


    override fun getAllUsers(): List<ExampleData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: ExampleData) {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.DEMO_SERVER_URL)
                .client(httpAuthClient)
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build()
        val userService = retrofit.create(UsersService::class.java);
        userService.addUser(user).enqueue(callbackResponse);

    }

    private val httpAuthClient: OkHttpClient
        get() {
            val okHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
                val originalRequest = chain.request()

                val builder = originalRequest.newBuilder().header(authorizeHeader,
                        Credentials.basic(userName, password))

                val newRequest = builder.build()
                chain.proceed(newRequest)
            }.build()

            return okHttpClient
        }
}