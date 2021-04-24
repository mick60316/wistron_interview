package com.example.wistron_interview.Component

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.R

class RecycleViewAdapter(context: Context, items: List<UserListData>, navController: NavController):
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>(){

    val TAG:String ="RecycleViewAdapter"
    val context :Context?=context
    val items : List<UserListData>?=items
    val navController:NavController?=navController

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemView:View?=view
        val imageView :ImageView?=view?.findViewById(R.id.avatar_url)
        val loginTextView:TextView?= view?.findViewById(R.id.login);
        val siteAdminTextView:TextView?=view?.findViewById(R.id.site_admin);
        val myView:LinearLayout?=view?.findViewById(R.id.myView)
        fun bind(userListData: UserListData?)
        {
            loginTextView?.text=userListData?.login
            siteAdminTextView?.text=userListData?.site_admin

        }



    }
    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position));
        holder.myView?.setOnClickListener(View.OnClickListener {

            Log.i(TAG,"Click "+holder.loginTextView?.text.toString());

        })


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