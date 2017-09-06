package com.jr.app.network

import com.jr.app.models.ExampleData

/**
 * Created by jonathanrichards on 28/07/2017.
 */

interface  RestServiceInterface{
    fun addUser(user: ExampleData)
    fun getAllUsers(): List<ExampleData>
}