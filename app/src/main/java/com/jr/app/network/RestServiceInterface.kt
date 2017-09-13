package com.jr.app.network

import com.jr.app.models.ExampleData
import retrofit2.Callback

/**
 * Created by jonathanrichards on 28/07/2017.
 */

interface  RestServiceInterface{
    fun addUser(user: ExampleData)
    fun getAllUsers(callback: Callback<List<ExampleData>>)
}