package com.example.wistron_interview

public interface ModelCallback {
    fun onSuccess(data:String );
    fun onFailure(msg:String);
}