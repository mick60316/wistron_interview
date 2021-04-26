package com.example.wistron_interview.Presenter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.example.wistron_interview.Component.UserData
import com.example.wistron_interview.Model
import com.example.wistron_interview.ModelCallback
import com.example.wistron_interview.Views.UserListView
import org.json.JSONArray
import org.json.JSONException

class UserListPresenter(view: UserListView) {

    val TAG :String ="UserListPresenter";
    val view :UserListView=view


    fun getUserlist ()
    {
        /*
            Get user list from model

            sent data from callback to view

         */
        Model.getAllUserData(object : ModelCallback {
            override fun onSuccess(data: String) {
                Log.i(TAG, " Get data success")
                var userDataList=  convertJsonStrToUserList(data)
                view.displayUserList(userDataList);
                for (i in 0 until userDataList.size) {
                    var index =userDataList.get(i)
                    System.out.println(index.login+" "+index.site_admin)
                }
                //System.out.println(data)
            }

            override fun onFailure(msg: String) {
                Log.i(TAG, " Get data fail : " + msg)
            }
        })

    }
    private fun convertJsonStrToUserList(jsonStr: String) :List<UserData>
    {

        /*
            Convert string to custom class UserData
            Argv :
                 jsonStr : json string which from github api

            return UserData

         */

        var userDataList:List<UserData>?=null
        userDataList= emptyList()
        try {
            val userArray = JSONArray(jsonStr)
            for (i in 0 until userArray.length()) {
                val userObj = userArray.getJSONObject(i)
                val login =userObj.getString("login");
                val avatar_url =userObj.getString("avatar_url")
                val site_admin =userObj.getBoolean("site_admin")
                var userData: UserData=UserData(avatar_url,login,site_admin)
                userDataList+=userData
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return userDataList
    }





}