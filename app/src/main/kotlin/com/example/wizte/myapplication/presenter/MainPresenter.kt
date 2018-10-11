package com.example.wizte.myapplication.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.wizte.myapplication.Interactor.GetPhotoListener
import com.example.wizte.myapplication.Interactor.PhotoInteractor
import com.example.wizte.myapplication.event.EmptyRecyclerAdapterEvent
import com.example.wizte.myapplication.model.Photo
import com.example.wizte.myapplication.view.MainView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), IMainPresenter, GetPhotoListener {

   private var photoInteractor = PhotoInteractor()

   init {
      EventBus.getDefault().register(this) //fixme!
   }

   override fun loadPhotosApi() {
      viewState.showProgressBar()
      viewState.hideTextEmptyRecycler()
      photoInteractor.getPhotosInService(this)
   }

   override fun onComplete(photos: List<Photo>) {
      viewState.hideProgressBar()
      if (photos.isEmpty()) viewState.showTextEmptyRecycler() else viewState.hideTextEmptyRecycler()
      viewState.showLoadPhotos(photos)
   }

   override fun onError(throwable: Throwable) {
      viewState.errorLoadPhotos(throwable)
   }

   @Subscribe
   fun onEvent(event: EmptyRecyclerAdapterEvent) {
      viewState.showTextEmptyRecycler()
   }
}

