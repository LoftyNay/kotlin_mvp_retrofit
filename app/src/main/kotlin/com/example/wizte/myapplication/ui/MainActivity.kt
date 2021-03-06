package com.example.wizte.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.wizte.myapplication.R
import com.example.wizte.myapplication.adapter.OnRecyclerItemClickListener
import com.example.wizte.myapplication.adapter.RecyclerPhotoAdapter
import com.example.wizte.myapplication.model.Photo
import com.example.wizte.myapplication.presenter.MainPresenter
import com.example.wizte.myapplication.view.MainView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : MvpActivity(), MainView, View.OnClickListener, OnRecyclerItemClickListener {

   private val recyclerPhotoAdapter = RecyclerPhotoAdapter(this)
   private lateinit var progressBar: ProgressBar
   private lateinit var emptyTextView: View

   @InjectPresenter
   lateinit var mainPresenter: MainPresenter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      initRecycler()

      emptyTextView = findViewById(R.id.emptyText)
      progressBar = findViewById(R.id.progressBarMainActivity)

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

   override fun showTextEmptyRecycler() {
      emptyTextView.visibility = View.VISIBLE
   }

   override fun hideTextEmptyRecycler() {
      emptyTextView.visibility = View.GONE
   }

   override fun recyclerItemClick(pos: Int) {
      Toast.makeText(applicationContext, pos.toString(), Toast.LENGTH_SHORT).show()
      recyclerPhotoAdapter.clearPhotoInPosition(pos)
   }

   override fun showProgressBar() {
      progressBar.visibility = View.VISIBLE
   }

   override fun hideProgressBar() {
      progressBar.visibility = View.GONE
   }

   private fun initRecycler() {
      val recyclerView = findViewById<RecyclerView>(R.id.recyclerMainActivity)
      recyclerView.layoutManager = LinearLayoutManager(applicationContext)
      recyclerView.adapter = recyclerPhotoAdapter
   }
}
