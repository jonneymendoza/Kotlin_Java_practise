package com.jr.app.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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
class ShowAllUserListFragment : Fragment() {

    var listUsers: MutableList<ExampleData> = mutableListOf<ExampleData>()

    var pd: ProgressDialog? = null

    var adapter: UserListAdapter? = null

    inner class CallbackAllUsers : Callback<MutableList<ExampleData>> {
        override fun onFailure(call: Call<MutableList<ExampleData>>?, t: Throwable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onResponse(call: Call<MutableList<ExampleData>>?, response: Response<MutableList<ExampleData>>?) {
            if (response != null) {
                pd!!.dismiss()
                listUsers.clear()
                listUsers.addAll(response.body()!!)
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.list_view_screen, null)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity.applicationContext)
        adapter = UserListAdapter(listUsers)
        recyclerView.adapter = adapter
        getListOfUsers()
//        Fragment().showS
    }

    private fun getListOfUsers() {
        pd = ProgressDialog(activity)
        pd!!.setMessage("Loading users list")
        pd!!.show()
        RestService().getAllUsers(CallbackAllUsers())
    }

}