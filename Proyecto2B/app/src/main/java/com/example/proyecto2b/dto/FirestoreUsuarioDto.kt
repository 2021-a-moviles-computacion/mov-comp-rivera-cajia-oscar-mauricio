package com.example.proyecto2b.dto

data class FirestoreUsuarioDto(
    var uid: String = "",
    var email: String = "",
    var name: String = "",
    var city: String = "",
    var gender: String = "",
    var phone: Int = 0,

) {

}