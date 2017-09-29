package com.jr.app.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import com.jr.app.R
import com.jr.app.presenters.interfaces.UserPresenter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.slide_menu.*
import java.util.jar.Manifest

/**
 * Created by jonathanrichards on 26/07/2017.
 */

class MainActivity:UserPresenter, FragmentActivity() {

    override fun deleteUser() {
    }

    override fun addNewUser() {
        if(drawerLayoutContent.isDrawerOpen(GravityCompat.START)){
            drawerLayoutContent.closeDrawers()
        }

        showScreen(AddUserFragment())
    }

    override fun showAllUsers(){
        if(drawerLayoutContent.isDrawerOpen(GravityCompat.START)){
            drawerLayoutContent.closeDrawers()
        }
       showScreen(ShowAllUserListFragment())
    }

    fun Any.showScreen(fragment: Fragment){
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(main_content_view.id, fragment )
        beginTransaction.addToBackStack(null)
        beginTransaction.commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        addNewUser()
        setupNavigationView()
    }

    private fun setupPermissions(){
        if(ContextCompat.checkSelfPermission(this, Manifest. ))
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