package com.example.proyecto2b.dto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable

class FirestoreTiendasDto(
    var nameT: String = "",
    var locationT: String = "",
    var typeT: String = "",
    var hoursT: String = "",
    var phoneT: Int = 0,
    var descriptionT: String = "",
    var favorites: ArrayList<Any> = arrayListOf("")
) {
    override fun toString(): String {
        return "${nameT} - ${locationT} - ${typeT} - ${hoursT} - ${phoneT}"
    }
}