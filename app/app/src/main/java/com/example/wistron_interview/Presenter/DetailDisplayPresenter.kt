package com.example.wistron_interview.Presenter

import android.util.Log
import com.example.wistron_interview.Component.UserData
import com.example.wistron_interview.Component.UserDetailData
import com.example.wistron_interview.Model
import com.example.wistron_interview.ModelCallback
import com.example.wistron_interview.Views.DetailDisplayView
import com.example.wistron_interview.Views.UserListView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DetailDisplayPresenter (view:DetailDisplayView){
    val TAG :String ="DetailDisplayPresenter";
    val view : DetailDisplayView =view
    fun getUserInfo (login:String)
    {
        Model.getUserData(login,object : ModelCallback {

            override fun onSuccess(data: String) {
                //System.out.println(data)
                var userDetailData=convertJsonStrToUserDetailData(data)
                view.displayUserData(userDetailData)
                Log.i(TAG, " Get data success")

            }

            override fun onFailure(msg: String) {
                Log.i(TAG, " Get data fail : "+msg)
            }
        })
    }
    fun convertJsonStrToUserDetailData (jsonStr:String):UserDetailData
    {

        var  userDetailData:UserDetailData?=null
        try {
            val userData = JSONObject(jsonStr)
            val login=userData.getString("login")
            val avatar_url=userData.getString("avatar_url")
            val bio=userData.getString("bio")
            val name=userData.getString("name")
            val site_admin=userData.getBoolean("site_admin")
            val location=userData.getString("location")
            val blog=userData.getString("blog")




            userDetailData=UserDetailData(avatar_url,name,bio,login,site_admin,location,blog)




        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return userDetailData!!
    }


}