package com.example.firebaseuno.dto

data class FirestoreProductoDto(
    //var id: String,
    var nombre: String = "",
    var precio: Double = 0.0
) {


    override fun toString(): String {
        return "${nombre} $${precio}"
    }
}