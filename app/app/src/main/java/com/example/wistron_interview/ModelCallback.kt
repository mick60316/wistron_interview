package com.example.wistron_interview

import android.graphics.Bitmap

public interface ModelCallback {
    fun onSuccess(data:String );
    fun onFailure(msg:String);

}