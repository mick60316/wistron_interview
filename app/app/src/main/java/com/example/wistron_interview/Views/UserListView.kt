package com.example.wistron_interview.Views

import com.example.wistron_interview.Component.UserListData

public interface UserListView {
    fun displayUserList (userDataList : List <UserListData>)
}