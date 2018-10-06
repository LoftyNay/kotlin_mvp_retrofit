package com.example.wizte.myapplication.Interactor

import com.example.wizte.myapplication.App
import com.example.wizte.myapplication.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PhotoInteractor : IPhotoInteractor {

   private lateinit var disposable: Disposable

   override fun getPhotosInService(photoListener: GetPhotoListener) {
      disposable = App.createServiceApi().getPhotos()
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(
                      { photoResponse: List<Photo> ->
                         photoListener.onComplete(photoResponse)
                      },
                      { throwable: Throwable ->
                         photoListener.onError(throwable)
                      }
              )
   }
}