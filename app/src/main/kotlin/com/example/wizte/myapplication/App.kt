package com.example.wizte.myapplication

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

   companion object {
      fun createServiceApi(): Api {
         val retrofit = Retrofit.Builder()
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .baseUrl(Constants.BASE_URL)
                 .build()
         return retrofit.create(Api::class.java)
      }
   }

   override fun onCreate() {
      super.onCreate()
      Realm.init(this)
      createServiceApi()
   }
}