package com.example.wizte.myapplication.event

class EmptyRecyclerAdapterEvent(var empty: Boolean) {
   fun isEmpty(): Boolean {
      return empty
   }
}