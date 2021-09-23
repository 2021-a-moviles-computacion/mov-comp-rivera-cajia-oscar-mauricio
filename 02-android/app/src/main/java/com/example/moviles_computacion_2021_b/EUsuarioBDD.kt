package com.example.moviles_computacion_2021_b

import android.os.Parcel
import android.os.Parcelable

class EUsuarioBDD(
    var id: Int?,
    var nombre: String?,
    var descripcion: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(//AQUI LEEMOS
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //parcel.writeInt(id)
        id?.let { parcel?.writeInt(it) }
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${id} - ${nombre} - ${descripcion}"
    }


    companion object CREATOR : Parcelable.Creator<EUsuarioBDD> {
        override fun createFromParcel(parcel: Parcel): EUsuarioBDD {
            return EUsuarioBDD(parcel)
        }

        override fun newArray(size: Int): Array<EUsuarioBDD?> {
            return arrayOfNulls(size)
        }
    }
}