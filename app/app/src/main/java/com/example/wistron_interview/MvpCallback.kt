package com.example.wistron_interview

public interface MvpCallback {

    fun onSuccess(data:String );
    fun onFailure(msg:String);

}