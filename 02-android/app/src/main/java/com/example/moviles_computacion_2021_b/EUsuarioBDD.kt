package com.example.moviles_computacion_2021_b

import android.os.Parcel
import android.os.Parcelable

class EUsuarioBDD(
    var id: Int,
    var nombre: String?,
    var descripcion: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(//AQUI LEEMOS
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )
}