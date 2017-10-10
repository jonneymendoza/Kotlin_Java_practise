package com.jr.app.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jr.app.R
import com.jr.app.presenters.interfaces.UserPresenter
import kotlinx.android.synthetic.main.slide_menu.*

/**
 * Created by jonathanrichards on 26/07/2017.
 */

class MainActivity : UserPresenter, FragmentActivity() {

    private val SIGN_IN = 1123

    private val EXTERNAL_STORAGE_PERMISSION = 23232

    lateinit var mAuth: FirebaseAuth

    override fun deleteUser() {
    }

    override fun addNewUser() {
        if (drawerLayoutContent.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutContent.closeDrawers()
        }

        showScreen(AddUserFragment())
    }

    override fun showAllUsers() {
        if (drawerLayoutContent.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutContent.closeDrawers()
        }
        showScreen(ShowAllUserListFragment())
    }

    fun Any.showScreen(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(main_content_view.id, fragment)
        beginTransaction.addToBackStack(null)
        beginTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.main_activity)
        setupNavigationView()
        setupPermissions()
    }

    private fun setupPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), EXTERNAL_STORAGE_PERMISSION)
        } else {
            setupLogin()
        }
    }

    private fun setupNavigationView() {
        navigation.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_add_new_user -> addNewUser()
                R.id.action_view_users -> showAllUsers()
                R.id.action_home -> displayMainContent()
            }
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            EXTERNAL_STORAGE_PERMISSION -> setupLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInIntent(result)
        }
    }


    ////////////////////////Move these into a seperate util class//////////////////////////////
    private fun setupLogin() {
        if (mAuth.currentUser != null) {
            addNewUser()
        } else {
            //sign in using google
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                    .requestIdToken("9939607014-fc4ojjrt5vg4386h4rndu7gejh38fdjo.apps.googleusercontent.com")
                    .build()

            val mGoogleApiClient = GoogleApiClient.Builder(this)
                    .enableAutoManage(this, onConnectionFailedListener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build()

            startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient), SIGN_IN)
        }
    }

    val onConnectionFailedListener = GoogleApiClient.OnConnectionFailedListener {
        Toast.makeText(this, "Error authenticating", Toast.LENGTH_SHORT).show()
    }

    private fun handleSignInIntent(result: GoogleSignInResult) {
        if (result.isSuccess) {
            Toast.makeText(this, "Welcome " + result.signInAccount!!.email, Toast.LENGTH_LONG).show()
            fireBaseSignIn(result.signInAccount!!)

        }
    }


    private fun fireBaseSignIn(acct: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(acct.idToken, null)

        mAuth.signInWithCredential(credentials).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isComplete) {
                Toast.makeText(this, "Sign in complete", Toast.LENGTH_SHORT).show()
                addNewUser()
            }else{
                Toast.makeText(this, "Sign in incomplete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    private fun displayMainContent() {

    }


}