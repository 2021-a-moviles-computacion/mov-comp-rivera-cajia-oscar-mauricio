package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class EModelosBDD(
    var id: Int,
    var id_fk: Int,
    var nombreModelo: String?,
    var precioModelo: Double,
    var nPuertasModelo: Int?,
    var puntExpModelo: Double,
    var serieModelo: String?
                   ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        /*id?.let { parcel?.writeInt(it) }
        //id?.let { parcel?.writeInt(it) }
        id_fk?.let { parcel?.writeInt(it) }
        parcel?.writeString(nombreModelo)
        precioModelo?.let { parcel?.writeDouble(it) }
        nPuertasModelo?.let { parcel?.writeInt(it) }
        puntExpModelo?.let { parcel?.writeDouble(it) }
        parcel?.writeString(serieModelo)*/
        parcel.writeInt(id)
        parcel.writeInt(id_fk)
        parcel?.writeString(nombreModelo)
        parcel.writeDouble(precioModelo)
        nPuertasModelo?.let { parcel?.writeInt(it) }
        parcel.writeDouble(puntExpModelo)
        parcel?.writeString(serieModelo)
    }

    override fun toString(): String {
        return "${id}-${id_fk}- ${nombreModelo} - ${precioModelo} $ - ${nPuertasModelo} ptas. - ${puntExpModelo} - ${serieModelo}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EModelosBDD> {
        override fun createFromParcel(parcel: Parcel): EModelosBDD {
            return EModelosBDD(parcel)
        }

        override fun newArray(size: Int): Array<EModelosBDD?> {
            return arrayOfNulls(size)
        }
    }
}