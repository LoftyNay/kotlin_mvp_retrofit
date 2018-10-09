package com.example.wizte.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.wizte.myapplication.R
import com.example.wizte.myapplication.model.Photo
import com.squareup.picasso.Picasso

class RecyclerPhotoAdapter(private var onRecyclerItemClickListener: OnRecyclerItemClickListener) :
        RecyclerView.Adapter<RecyclerPhotoAdapter.ViewHolder>() {

   private var photos: ArrayList<Photo> = ArrayList()

   fun addPhotoInList(photos: List<Photo>) {
      this.photos.addAll(photos)
      notifyDataSetChanged()
   }

   fun clearPhotoList(photos: List<Photo>) {
      this.photos.clear()
      notifyDataSetChanged()
   }

   fun clearPhotoInPosition(position: Int) {
      this.photos.removeAt(position)
      notifyDataSetChanged()
   }

   fun addPhotoInPosition(photo: Photo, position: Int) {
      photos.add(position, photo)
      notifyDataSetChanged()
   }

   fun setOnItemRecyclerClickListener(onRecyclerItemClickListener: OnRecyclerItemClickListener) {
      this.onRecyclerItemClickListener = onRecyclerItemClickListener
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val cardView: CardView = LayoutInflater.from(parent.context)
              .inflate(R.layout.card_item_recycler_main_activity, parent, false) as CardView
      return ViewHolder(cardView)
   }

   override fun getItemCount() = photos.size

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      Picasso.get()
              .load(photos[position].thumbnailUrl)
              .into(holder.imageView)
      holder.textView.text = photos[position].title
      holder.view.setOnClickListener { onRecyclerItemClickListener.recyclerItemClick(position) }
   }

   class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
      val imageView = view.findViewById<AppCompatImageView>(R.id.imageRecyclerItem)!!
      val textView = view.findViewById<TextView>(R.id.textViewRecyclerItem)!!
   }
}