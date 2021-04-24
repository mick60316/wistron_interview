package com.example.wistron_interview.Fragment

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.Component.RecycleViewAdapter
import com.example.wistron_interview.Component.UserListData
import com.example.wistron_interview.Presenter.UserListPresenter
import com.example.wistron_interview.R
import com.example.wistron_interview.Views.UserListView

class UsetListFragment : Fragment(),UserListView{
    lateinit var recyclerView:RecyclerView
    lateinit var _context: Context
    lateinit var navController:NavController
    lateinit var _UserListData:List<UserListData>
    var userListPresenter : UserListPresenter?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController=findNavController()
        return inflater.inflate(R.layout.user_list_page, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _UserListData= emptyList()
        for (i in 0..10)
        {
            var bitmap = Bitmap.createBitmap(100, 800, Bitmap.Config.RGB_565)

            var UserIndex :UserListData=UserListData(bitmap,""+i,""+i);
            _UserListData+=UserIndex;
        }

        Log.i("TEST",""+_UserListData.size);

        userListPresenter= UserListPresenter(this);
        userListPresenter?.getUserlist();


        recyclerView =view.findViewById(R.id.user_list)

        recyclerView.setAdapter(RecycleViewAdapter(_context, _UserListData, navController))
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context=context
    }

    override fun displayUserList(userDataList: List<UserListData>) {

    }
}