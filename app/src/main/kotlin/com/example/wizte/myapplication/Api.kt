package com.example.wizte.myapplication

import com.example.wizte.myapplication.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

   @GET("/photos")
   fun getPhotos(): Observable<List<Photo>>

}