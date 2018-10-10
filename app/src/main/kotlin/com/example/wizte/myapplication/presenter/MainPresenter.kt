package com.example.wizte.myapplication.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.wizte.myapplication.Interactor.GetPhotoListener
import com.example.wizte.myapplication.Interactor.PhotoInteractor
import com.example.wizte.myapplication.model.Photo
import com.example.wizte.myapplication.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), IMainPresenter, GetPhotoListener {

   private var photoInteractor = PhotoInteractor()

   override fun loadPhotosApi() {
      viewState.showProgressBar()
      viewState.hideTextEmptyRecycler()
      photoInteractor.getPhotosInService(this)
   }

   override fun onComplete(photos: List<Photo>) {
      viewState.hideProgressBar()
      if (photos.isEmpty()) viewState.showTextEmptyRecycler() else viewState.hideTextEmptyRecycler()
      //Отдаем фото на экран
      viewState.showLoadPhotos(photos)
   }

   override fun onError(throwable: Throwable) {
      //Ошибка
      viewState.errorLoadPhotos(throwable)
   }
}

