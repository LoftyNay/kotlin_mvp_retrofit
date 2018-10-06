package com.example.wizte.myapplication.Interactor

import com.example.wizte.myapplication.model.Photo

interface GetPhotoListener {
   fun onComplete(photos: List<Photo>)
   fun onError(throwable: Throwable)
}