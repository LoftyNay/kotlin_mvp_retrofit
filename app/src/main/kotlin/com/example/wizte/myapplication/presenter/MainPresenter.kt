package com.example.wizte.myapplication.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.wizte.myapplication.model.Photo
import com.example.wizte.myapplication.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), IMainPresenter, GetPhotoListener {

   private var interactorMainPresenter : InteractorMainPresenter = InteractorMainPresenter()

   override fun loadPhotosApi() {
      interactorMainPresenter.getPhotosInService(this)
   }

   override fun onComplete(photos: List<Photo>) {
      //Отдаем фото на экран
      viewState.showLoadPhotos(photos)
   }

   override fun onError(throwable: Throwable) {
      //Ошибка
      viewState.errorLoadPhotos(throwable)
   }
}

