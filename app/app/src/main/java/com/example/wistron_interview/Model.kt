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
//        @JvmStatic fun getBitmapByUrl (context: Context, url:String,callback: ModelCallback){
//                Picasso.with(context).load(url).into(object: com.squareup.picasso.Target {
//                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
//                        System.out.println("onPrepareLoad")
//
//                    }
//                    override fun onBitmapFailed(errorDrawable: Drawable?) {
//                        System.out.println("onBitmapFailed")
//
//                    }
//                })
//
//        }
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
        fun test() {
            val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build()
            val request: Request = Request.Builder()
                .url("https://script.google.com/macros/s/AKfycbyvk9Gxa5UisCySFYasojru2sZnsazNxHqP1FrjduFeHN-5RCs/exec?action=all")
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
                    /**取得回傳 */
                    val result = response.body!!.string()
                    Log.i(TAG, result)


                }
            })

        }
    }


}