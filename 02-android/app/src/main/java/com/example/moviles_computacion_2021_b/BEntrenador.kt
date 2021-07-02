package com.example.moviles_computacion_2021_b

import android.os.Parcel
import android.os.Parcelable
//aber
class BEntrenador(
    val nombre: String?,
    val descripcion: String?,
                            ) :Parcelable{
    constructor(parcel: Parcel) : this(//AQUI LEEMOS
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {//AQUI ESCRBIIMOSS
        //if(parcel = null){
        //parcel.writeString(nombre)}
        //parcel.writeString(descripcion)
        parcel?.writeString(nombre)
        parcel?.writeString(descripcion)

        //TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }

    override fun describeContents(): Int {
        return 0
        //TODO("Not yet implemented")
    }
    /*
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }*/

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}