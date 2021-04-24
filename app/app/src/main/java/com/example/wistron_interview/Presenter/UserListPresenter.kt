package com.example.wistron_interview.Presenter

import android.os.Message
import android.util.Log
import com.example.wistron_interview.Model
import com.example.wistron_interview.ModelCallback
import com.example.wistron_interview.Views.UserListView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class UserListPresenter(view: UserListView) {

    val TAG :String ="UserListPresenter";
    val view :UserListView=view
    fun getUserlist ()
    {
        Model.getAllUserData(object : ModelCallback {
            override fun onSuccess(data: String) {
                Log.i(TAG, " Get data success")
                convertJsonStrToUserList(data)
                //System.out.println(data)
            }

            override fun onFailure(msg: String) {
                Log.i(TAG, " Get data fail : " + msg)
            }
        })

    }
    private fun convertJsonStrToUserList(jsonStr: String)
    {
        try {
            val userArray = JSONArray(jsonStr)
            for (i in 0 until userArray.length()) {
                val userObj = userArray.getJSONObject(i)
                val login =userObj.getString("login");
                System.out.println(login);
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

    }


}