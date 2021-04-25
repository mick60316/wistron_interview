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

class UserListPresenter(view: UserListView,context:Context) {

    val TAG :String ="UserListPresenter";
    val view :UserListView=view
    val context:Context=context;

    fun getUserlist ()
    {


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



//            val dataJsonObject: String = jsonObject.getString("data")
//            val attractionsObjArray = JSONArray(dataJsonObject)
//            for (i in 0 until attractionsObjArray.length()) {
//                val attractionObj = attractionsObjArray.getJSONObject(i)
//                val id = attractionObj.getInt("id")
//                val name = attractionObj.getString("name")
//                val attraction = Attraction(id, name)
//                attractions.add(attraction)
//            }
//            val msg = Message.obtain()
//            msg.what = UPDATE_RECYCLE_VIEW
//            msg.obj = attractions
//            mHandler.sendMessage(msg)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return userDataList
    }





}