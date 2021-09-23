package com.example.miexamen2b.dto

data class FirestoreFabricantesDto(
    //var id: String = "",
    var nomF: String = "",
    var tipoF: String= "",
    var sedeF: String= "",
    var fechaF: String= "",
    var fundF: String = ""
) {
    override fun toString(): String {
        return "${nomF} - ${tipoF} - ${sedeF} - ${fechaF} - ${fundF}"
    }

}