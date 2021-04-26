package com.example.wistron_interview.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wistron_interview.Presenter.DetailDisplayPresenter
import com.example.wistron_interview.R
import com.example.wistron_interview.Views.DetailDisplayView

class DetialDisplayFragment  : Fragment(),DetailDisplayView {
    private val TAG :String = "DetialDisplayFragment"
    private var _context:Context?=null;
    private val detailDisplayPresenter: DetailDisplayPresenter?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.detail_display_page, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val login=bundle?.getString("login")
        Log.i(TAG,login.toString());

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context=context
    }

}