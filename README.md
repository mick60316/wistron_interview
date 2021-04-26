# wistron_interview




## 架構
為了遵守MVP架構，我設計了兩個View分別對應兩個Presenter，最後指向同一個Model進行管理。
### UsetListFragment

### API Get
* Get User List
```
curl \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/users
```
```
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
```
* Get A User Info
```
curl \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/users/USERNAME
```
```
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
```
## 套件使用
1. OkHttp ->負責Https Get 跟API要資料
2. Picasso -> 負責下載圖片存成bitmap，並且顯示於imageview上




[Demo影片](https://www.youtube.com/watch?v=JTDxLW9Tg7M)   
[APK下載](https://drive.google.com/file/d/1GoyhAXjOV48WSj5C3ZrRbum8N0mOeXvv/view?usp=sharing)
