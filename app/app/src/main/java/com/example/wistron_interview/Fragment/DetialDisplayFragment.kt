package com.example.wistron_interview.Fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wistron_interview.Component.RecycleViewAdapter
import com.example.wistron_interview.Component.UserData
import com.example.wistron_interview.Component.UserDetailData
import com.example.wistron_interview.Presenter.DetailDisplayPresenter
import com.example.wistron_interview.R
import com.example.wistron_interview.Views.DetailDisplayView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DetialDisplayFragment  : Fragment(),DetailDisplayView {
    private val TAG :String = "DetialDisplayFragment"
    private var _context:Context?=null;
    private var detailDisplayPresenter: DetailDisplayPresenter?=null
    private var avatarImageView:ImageView?=null
    private var loginTextview :TextView?=null
    private var nameTextview:TextView?=null
    private var bioTextView:TextView?=null
    private var siteAdminTextView:TextView?=null
    private var locationTextView:TextView?=null
    private var blogTextView:TextView?=null
    private val UPDATE_USER_INFO=1
    private val handler = object : Handler() {

        override fun handleMessage(msg: Message) {
            when(msg.what)
            {
                UPDATE_USER_INFO-> {
                    val userDatailData: UserDetailData? = msg.obj as UserDetailData?
                    var url :String= userDatailData?.avatar_url.toString()
                    Log.i (TAG,url)
                    Picasso.with(_context).load(url).into(avatarImageView)
                    loginTextview?.setText(userDatailData?.login);
                    nameTextview?.setText(userDatailData?.name)
                    blogTextView?.setText(userDatailData?.blog)
                    locationTextView?.setText(userDatailData?.location)
                    bioTextView?.setText(userDatailData?.bio)
                    siteAdminTextView?.setText(userDatailData?.site_admin.toString())

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.detail_display_page, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatarImageView=view.findViewById(R.id.avatar_image)
        loginTextview=view.findViewById(R.id.login_text)
        nameTextview=view.findViewById(R.id.name_text)
        blogTextView=view.findViewById(R.id.blog_text)
        locationTextView=view.findViewById(R.id.location_text)
        bioTextView=view.findViewById(R.id.blo_text)
        siteAdminTextView=view.findViewById(R.id.site_admin_text)
        blogTextView?.setAutoLinkMask(Linkify.WEB_URLS);
        blogTextView?.setMovementMethod(LinkMovementMethod.getInstance());

        val bundle = arguments
        val login=bundle?.getString("login")
        detailDisplayPresenter=DetailDisplayPresenter(this)

        if (login != null) {
            detailDisplayPresenter?.getUserInfo(login)
        }

        Log.i(TAG,login.toString());

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context=context
    }

    override fun displayUserData(userData: UserDetailData) {
        val msg =Message()
        msg.what=UPDATE_USER_INFO
        msg.obj=userData
        handler.sendMessage(msg)



    }
}