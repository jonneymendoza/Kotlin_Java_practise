package com.jr.app.ui

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jr.app.R
import com.jr.app.adapters.UserListAdapter
import com.jr.app.models.ExampleData
import com.jr.app.network.RestService
import kotlinx.android.synthetic.main.list_view_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Jonathan on 11/09/2017.
 */
class ShowAllUserList : Fragment() {

    var listUsers : List<ExampleData>? = null

    inner class CallbackAllUsers : Callback<List<ExampleData>> {
        override fun onFailure(call: Call<List<ExampleData>>?, t: Throwable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onResponse(call: Call<List<ExampleData>>?, response: Response<List<ExampleData>>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.add_user_layout, null)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity.applicationContext)
        recyclerView.adapter = UserListAdapter(listUsers!!)

    }

    private fun getListOfUsers(){
        RestService().getAllUsers(CallbackAllUsers())
    }

}