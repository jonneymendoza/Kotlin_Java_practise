package com.jr.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Jonathan on 12/09/2017.
 */

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.ViewHolder>{
    constructor() : super()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder: RecyclerView.ViewHolder{
        constructor(itemView: View?) : super(itemView)

    }
}
