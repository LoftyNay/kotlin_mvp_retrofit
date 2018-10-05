package com.example.wizte.myapplication.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Photo(var albumId: Int = 0,
                 var id: Int = 0,
                 var title: String? = null,
                 var url: String? = null,
                 var thumbnailUrl: String? = null) : RealmObject()