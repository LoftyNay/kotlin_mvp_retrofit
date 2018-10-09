package com.example.wizte.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.wizte.myapplication.R
import com.example.wizte.myapplication.adapters.OnRecyclerItemClickListener
import com.example.wizte.myapplication.adapters.RecyclerPhotoAdapter
import com.example.wizte.myapplication.model.Photo
import com.example.wizte.myapplication.presenter.MainPresenter
import com.example.wizte.myapplication.view.MainView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : MvpActivity(), MainView, View.OnClickListener, OnRecyclerItemClickListener {

   private val recyclerPhotoAdapter = RecyclerPhotoAdapter(this)

   @InjectPresenter
   lateinit var mainPresenter: MainPresenter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      initRecycler()

      val floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
      floatingActionButton.setOnClickListener(this)
   }

   override fun onClick(ed: View?) {
      mainPresenter.loadPhotosApi()
   }

   override fun showLoadPhotos(photos: List<Photo>) {
      recyclerPhotoAdapter.addPhotoInList(photos)
   }

   override fun errorLoadPhotos(throwable: Throwable) {
      Toast.makeText(applicationContext, throwable.toString(), Toast.LENGTH_SHORT).show()
   }

   override fun recyclerItemClick(pos: Int) {
      Toast.makeText(applicationContext, pos.toString(), Toast.LENGTH_SHORT).show()
      recyclerPhotoAdapter.clearPhotoInPosition(pos)
   }

   private fun initRecycler() {
      val recyclerView = findViewById<RecyclerView>(R.id.recyclerMainActivity)
      recyclerView.layoutManager = LinearLayoutManager(applicationContext)
      recyclerView.adapter = recyclerPhotoAdapter
   }
}
