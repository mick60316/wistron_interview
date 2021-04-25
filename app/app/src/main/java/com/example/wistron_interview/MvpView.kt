package com.example.wistron_interview

import com.example.wistron_interview.Component.UserData

public interface MvpView {
    fun updateUserList (data : List<UserData>)
    fun updateUserInfo ()

}