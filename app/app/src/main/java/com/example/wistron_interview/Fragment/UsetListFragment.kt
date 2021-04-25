package com.example.wistron_interview.Fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wistron_interview.Component.RecycleViewAdapter
import com.example.wistron_interview.Component.UserData
import com.example.wistron_interview.Presenter.UserListPresenter
import com.example.wistron_interview.R
import com.example.wistron_interview.Views.UserListView

class UsetListFragment : Fragment(),UserListView{
    lateinit var recyclerView:RecyclerView
    lateinit var _context: Context
    lateinit var navController:NavController
    lateinit var _UserData:List<UserData>
    val UPDATE_RECYCLE_ITEMS=1
    private val mHandler = object : Handler() {

        override fun handleMessage(msg: Message) {

            when(msg.what)
            {
                UPDATE_RECYCLE_ITEMS->
                {
                    val userDataList:List<UserData>?= msg.obj as List<UserData>?
                    recyclerView.setAdapter(RecycleViewAdapter(_context, userDataList, navController))
                }
            }
        }
    }

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

        _UserData= emptyList()
//        for (i in 0..10)
//        {
//            var bitmap = Bitmap.createBitmap(100, 800, Bitmap.Config.RGB_565)
//
//            var UserIndex :UserListData=UserListData(bitmap,""+i,""+i);
//            _UserListData+=UserIndex;
//        }

        Log.i("TEST",""+_UserData.size);

        userListPresenter= UserListPresenter(this,_context);
        userListPresenter?.getUserlist();

        recyclerView =view.findViewById(R.id.user_list)
        recyclerView.setAdapter(RecycleViewAdapter(_context, _UserData, navController))
        recyclerView.setLayoutManager(LinearLayoutManager(_context))
        super.onViewCreated(view, savedInstanceState)


    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context=context
    }

    override fun displayUserList(userDataList: List<UserData>) {
        val msg =Message()
        msg.what=UPDATE_RECYCLE_ITEMS
        msg.obj=userDataList
        mHandler.sendMessage(msg)

    }
}