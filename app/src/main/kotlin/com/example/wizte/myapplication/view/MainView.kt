package com.example.wizte.myapplication.view

import com.arellomobile.mvp.MvpView
import com.example.wizte.myapplication.model.Photo

interface MainView : MvpView {
   fun showLoadPhotos(photos: List<Photo>)
   fun errorLoadPhotos(throwable: Throwable)
}