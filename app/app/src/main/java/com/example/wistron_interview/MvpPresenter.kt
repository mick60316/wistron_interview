package com.example.wistron_interview

import android.util.Log
import android.view.View

class MvpPresenter {
    var TAG : String="MvpPresenter"
    var userListFragmentView : View?=null;
    var detailDisplayFragmentView: View?=null;

    fun setUserListFragment (view: View)
    {
        userListFragmentView=view;
    }
    @JvmName("setDetailDisplayFragmentView1")
    fun setDetailDisplayFragmentView (view:View)
    {

        detailDisplayFragmentView=view;
    }
    fun test ()
    {
        Model.getAllUserData(object :ModelCallback{

            override fun onSuccess(data: String) {
                Log.i(TAG,data);
            }

            override fun onFailure(msg: String) {
                TODO("Not yet implemented")
            }
        })


    }
    fun test2 ()
    {
        Model.getUserData("mojombo",object :ModelCallback{
            override fun onSuccess(data: String) {
                Log.i(TAG,data)
            }

            override fun onFailure(msg: String) {
                TODO("Not yet implemented")
            }
        })

    }
}