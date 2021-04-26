# wistron_interview




## 架構
為了遵守MVP架構，我設計了兩個View分別對應兩個Presenter，最後指向同一個Model進行管理。
### UsetListFragment
主要組成為RecycleView，因此物件同時具有Scroll與Linear Layout的特性，能夠減少開發時間。透過displayUserList獲得由Presenter將Json字串拆解並組裝成指定物件後，透過此function傳至View。然而因為還要下載圖片，需要透過handler轉接至main thread。   
### DetialDisplayFragment
透過displayUserData function將Presenter獲得的資料傳送至View上，並且因為下載圖片的關係，透過handler至main thread運作。


### UserListPresenter
利用Callback的方式向Model獲得資料，將資料解析之後獲得三個String資料，將其打包成UserData此資料結構。接著透過view.callback 的方式傳遞至view上顯示。

### DetailDisplayPresenter
利用Callback的方式向Model獲得資料，將資料解析並製作成UserDetialData此資料結構。接著透過view.callback 的方式傳遞至view上顯示。

### Model 
負責串接兩個需要透過API獲得資料的功能。分別是Get User List以及Get User Info。此class會將獲得的資料透過callback的方式傳送至Presenter。





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
