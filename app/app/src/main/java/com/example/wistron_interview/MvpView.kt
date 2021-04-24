package com.example.wistron_interview

import com.example.wistron_interview.Component.UserListData

public interface MvpView {
    fun updateUserList (data : List<UserListData>)
    fun updateUserInfo ()

}