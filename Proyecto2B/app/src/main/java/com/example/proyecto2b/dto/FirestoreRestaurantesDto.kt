package com.example.proyecto2b.dto

import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.example.proyecto2b.R

data class FirestoreRestaurantesDto(
    var nameR: String = "",
    var locationR: String = "",
    var typeR: String = "",
    var hoursR: String = "",
    var phoneR: Int = 0,
    var descriptionR: String = "",
    var favorites: ArrayList<Any> = arrayListOf("")
) {
    override fun toString(): String {
        return "${nameR} - ${locationR} - ${typeR} - ${hoursR} - ${phoneR}"
    }
}