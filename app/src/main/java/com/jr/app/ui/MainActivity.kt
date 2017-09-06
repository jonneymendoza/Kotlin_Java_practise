package com.jr.app.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.jr.app.R
import com.jr.app.models.ExampleData
import com.jr.app.presenters.interfaces.UserPresenter
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by jonathanrichards on 26/07/2017.
 */

class MainActivity:UserPresenter, FragmentActivity() {


    override fun deleteUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAllUsers(): ExampleData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(main_content_view.id, AddUserFragment())
        beginTransaction.commit()

    }


}