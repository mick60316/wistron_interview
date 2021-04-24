package com.example.wistron_interview

import android.util.Log

class MvpPresenter {
    var TAG : String="MvpPresenter"


    fun test ()
    {
        Model.getUserData(object :MvpCallback{

            override fun onSuccess(data: String) {
                Log.i(TAG,data);
            }

            override fun onFailure(msg: String) {
                TODO("Not yet implemented")
            }
        })

    }
}