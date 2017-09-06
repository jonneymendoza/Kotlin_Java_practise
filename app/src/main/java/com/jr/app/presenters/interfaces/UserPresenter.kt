package com.jr.app.presenters.interfaces

import com.jr.app.models.ExampleData

/**
 * Created by jonathanrichards on 26/07/2017.
 */

interface UserPresenter{
    fun deleteUser()
    fun addNewUser()
    fun showAllUsers(): ExampleData;
}