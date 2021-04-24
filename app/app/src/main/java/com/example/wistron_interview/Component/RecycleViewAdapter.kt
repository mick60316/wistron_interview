package com.example.wistron_interview.Component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.R

class RecycleViewAdapter(context: Context, items: List<UserListData>, navController: NavController):
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>(){

    val context :Context?=context
    val items : List<UserListData>?=items
    val navController:NavController?=navController

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imageView :ImageView?=view?.findViewById(R.id.avatar_url)
        val login:TextView?= view?.findViewById(R.id.login);
        val site_admin:TextView?=view?.findViewById(R.id.site_admin);
        fun bind(userListData: UserListData?)
        {
            login?.text=userListData?.login
            site_admin?.text=userListData?.site_admin

        }



    }
    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position));
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView : View=LayoutInflater.from(parent?.context).inflate(
            R.layout.user_recycle_item,
            parent,
            false
        );
        return ViewHolder(itemView)
    }
}