package com.example.miexamen2b

import android.os.Parcel
import android.os.Parcelable

class EFabricanteBDD(
    var id: Int?,
    var nomFabricante: String?,
    var tipoFabricante: String?,
    var sedeFabricante: String?,
    var fechaFabricante: String?,
    var fundadorFabricante: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(//AQUI LEEMOS
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //parcel?.writeInt(id)
        id?.let { parcel?.writeInt(it) }
        parcel?.writeString(nomFabricante)
        parcel?.writeString(tipoFabricante)
        parcel?.writeString(sedeFabricante)
        parcel?.writeString(fechaFabricante)
        parcel?.writeString(fundadorFabricante)
    }
    override fun toString(): String {
        return "${id} - ${nomFabricante} - ${tipoFabricante} - ${sedeFabricante} - ${fechaFabricante} - ${fundadorFabricante}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EFabricanteBDD> {
        override fun createFromParcel(parcel: Parcel): EFabricanteBDD {
            return EFabricanteBDD(parcel)
        }

        override fun newArray(size: Int): Array<EFabricanteBDD?> {
            return arrayOfNulls(size)
        }
    }
}