package com.jr.app.network

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.UploadTask
import com.jr.app.models.ExampleData
import retrofit2.Callback

/**
 * Created by jonathanrichards on 28/07/2017.
 */

interface RestServiceInterface {
    fun addUser(user: ExampleData)
    fun getAllUsers(callback: Callback<MutableList<ExampleData>>)
    fun uploadProfileImage(imageFilePath: String, userId: String, onSuccessListener: OnSuccessListener<UploadTask.TaskSnapshot>, onFailureListener: OnFailureListener)
}