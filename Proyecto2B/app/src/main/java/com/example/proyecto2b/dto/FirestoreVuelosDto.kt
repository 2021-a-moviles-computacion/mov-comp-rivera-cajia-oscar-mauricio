package com.example.proyecto2b.dto

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

data class FirestoreVuelosDto(
    var aerolineaV: String = "",
    var fechaSalidaV: String = "",
    var fechaLlegadaV: String = "",
    var origenV: String = "",
    var destinoV: String = "",
    var precioV: Int = 0,
    var horaSalidaV: String = "",
    var horaLlegadaV: String = "",
    var duracionV: String = "",
    var urlV: String = "",
    var telefonoV: Int = 0,
) {
}