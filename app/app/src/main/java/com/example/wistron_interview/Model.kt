package com.example.wistron_interview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class Model {
    companion object {
        val TAG: String = "MODEL"
        @JvmStatic fun getUserData (login:String ,callback: ModelCallback)
        {
            val client = OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build()
            val request: Request = Request.Builder()
                    .addHeader("Accept","application/vnd.github.v3+json")
                    .url("https://api.github.com/users/"+login)
                    .build()

            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(
                            TAG,
                            "Http get fail " + e.message
                    )
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {

                    val result = response.body!!.string()
                    callback.onSuccess(result)



                }
            })


        }

        @JvmStatic fun getAllUserData (callback: ModelCallback){

            val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build()
            val request: Request = Request.Builder()
                .addHeader("Accept","application/vnd.github.v3+json")
                .url("https://api.github.com/users")
                .build()

            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(
                        TAG,
                        "Http get fail " + e.message
                    )
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {

                    val result = response.body!!.string()
                    callback.onSuccess(result)



                }
            })

        }

    }
}


