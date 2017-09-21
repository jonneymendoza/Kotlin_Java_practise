package com.jr.app.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.jr.app.R
import com.jr.app.presenters.interfaces.UserPresenter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.slide_menu.*

/**
 * Created by jonathanrichards on 26/07/2017.
 */

class MainActivity:UserPresenter, FragmentActivity() {

    override fun deleteUser() {
    }

    override fun addNewUser() {
//        if(drawerLayoutContent.isDrawerOpen(GravityCompat.START)){
//            drawerLayoutContent.closeDrawers()
//        }

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(main_content_view.id, AddUserFragment())
        beginTransaction.addToBackStack(null)
        beginTransaction.commit()
    }

    override fun showAllUsers(){
//        if(drawerLayoutContent.isDrawerOpen(GravityCompat.START)){
//            drawerLayoutContent.closeDrawers()
//        }

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(main_content_view.id, ShowAllUserListFragment())
        beginTransaction.addToBackStack(null)
        beginTransaction.commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        addNewUser()
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigation.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_add_new_user -> addNewUser()
                R.id.action_view_users -> showAllUsers()
                R.id.action_home  -> displayMainContent()
            }
            true
        }
    }

    private fun displayMainContent(){

    }


}