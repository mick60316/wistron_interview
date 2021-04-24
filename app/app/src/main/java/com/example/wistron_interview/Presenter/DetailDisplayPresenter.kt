package com.example.wistron_interview.Presenter

import android.util.Log
import com.example.wistron_interview.Model
import com.example.wistron_interview.ModelCallback

class DetailDisplayPresenter {
    val TAG :String ="DetailDisplayPresenter";

    fun getUserInfo (login:String)
    {
        Model.getUserData(login,object : ModelCallback {

            override fun onSuccess(data: String) {
                Log.i(TAG, " Get data success")

            }

            override fun onFailure(msg: String) {
                Log.i(TAG, " Get data fail : "+msg)
            }
        })


    }


}