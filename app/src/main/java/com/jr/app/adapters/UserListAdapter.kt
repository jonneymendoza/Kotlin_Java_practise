package com.jr.app.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jr.app.R
import com.jr.app.models.ExampleData
import kotlinx.android.synthetic.main.user_list_item.view.*

/**
 * Created by Jonathan on 12/09/2017.
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    var userListItems: List<ExampleData>? = null

    constructor(userListItemData: List<ExampleData>) {
        userListItems = userListItemData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = userListItems!![position]
        holder.firstName!!.text = userData.firstName
        holder.surnName!!.text = userData.secondName
    }

    override fun getItemCount(): Int {
      return  userListItems!!.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var firstName: TextView? = null
        var surnName: TextView? = null

        constructor(itemView: View) : super(itemView) {
            firstName = itemView.first_name_text
            surnName = itemView.second_name_text
        }

    }
}
