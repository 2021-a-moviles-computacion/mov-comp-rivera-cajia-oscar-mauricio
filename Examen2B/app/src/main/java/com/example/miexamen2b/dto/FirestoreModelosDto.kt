package com.example.miexamen2b.dto

data class FirestoreModelosDto(
    var idF: String = "",
    var nomM: String ="",
    var preM: Double =0.0,
    var nPM: Int = 0,
    var puntosM: Double = 0.0,
    var serieMF: String="",
    var latM: Double = 0.0,
    var longM: Double = 0.0
) {
    override fun toString(): String {
        return "${nomM} - ${preM} $ - ${nPM} ptas. - ${puntosM} - ${serieMF}"
    }
}