package com.jr.app.network

import com.jr.app.models.ExampleData
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by jonathanrichards on 28/07/2017.
 */

interface UsersService {

    @GET("usersProfile")
    fun getAllUsers(): Call<List<ExampleData>>

    @POST("usersProfile")
    fun addUser(@Body exampleData: ExampleData): Call<Response>

}