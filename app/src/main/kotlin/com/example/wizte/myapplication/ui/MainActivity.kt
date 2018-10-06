package com.example.wizte.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.MvpFacade
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.wizte.myapplication.R
import com.example.wizte.myapplication.model.Photo
import com.example.wizte.myapplication.presenter.MainPresenter
import com.example.wizte.myapplication.view.MainView
import com.google.android.material.floatingactionbutton.FloatingActionButton

//fixme
class MainActivity : MvpActivity(), MainView, View.OnClickListener {

   @InjectPresenter
   lateinit var mainPresenter: MainPresenter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      val floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
      floatingActionButton.setOnClickListener(this)
   }

   override fun onClick(ed: View?) {
      mainPresenter.loadPhotosApi()
   }

   override fun showLoadPhotos(photos: List<Photo>) {
      Toast.makeText(applicationContext, photos.size.toString(), Toast.LENGTH_SHORT).show()
   }

   override fun errorLoadPhotos(throwable: Throwable) {
      Toast.makeText(applicationContext, throwable.toString(), Toast.LENGTH_SHORT).show()
   }
}
