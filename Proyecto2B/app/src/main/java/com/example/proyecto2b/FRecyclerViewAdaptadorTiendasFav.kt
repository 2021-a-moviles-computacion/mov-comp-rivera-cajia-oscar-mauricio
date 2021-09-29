package com.example.proyecto2b

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FRecyclerViewAdaptadorTiendasFav(private val contexto: GRecyclerViewTiendasFav,
                                        //private val listaRestaurantes: List<BNotifications>,
                                    private var listaTiendas: ArrayList<FirestoreTiendasDto>,
                                    private val recyclerView: RecyclerView,
                                    private val itemClickListener: OnItemClickListenerTiendasFav
): RecyclerView.Adapter<FRecyclerViewAdaptadorTiendasFav.MyViewHolder>(){


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nombreTienda: TextView
        val descripcionTienda: TextView
        val logoTienda: ImageView

        init {
            nombreTienda = view.findViewById(R.id.tv_nombreTienda)
            descripcionTienda = view.findViewById(R.id.tv_descripcionTienda)
            logoTienda = view.findViewById(R.id.img_logoTienda)


            logoTienda.setOnClickListener{

            }

        }


        fun bind(tienda: FirestoreTiendasDto,clickListener: OnItemClickListenerTiendasFav)
        {
            nombreTienda.text = tienda.nameT
            descripcionTienda.text = tienda.descriptionT

            itemView.setOnClickListener {
                clickListener.onItemClicked(tienda, this.position)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_tiendas,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tienda = listaTiendas[position]
        holder.nombreTienda.text = tienda.nameT
        holder.descripcionTienda.text = tienda.descriptionT
        var referenci = Firebase.storage
        var nombreImg = referenci.reference.child("tiendas/"+tienda.nameT.toString()+".jpg")
        Log.i("Firebase-Imagen", "restaurantes/"+tienda.nameT.toString()+".jpg" )
        nombreImg.getBytes(10024*10024)
            .addOnSuccessListener {
                val bit: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                //Log.i("Firebase-Imagen", "Imagen recuperada->  ${dataDir}" )
                Log.i("Firebase-Imagen", "Imagen recuperada->  ${bit}" )
                //var imagen =findViewById<ImageView>(R.id.imageView).setImageBitmap(bit)
                holder.logoTienda.setImageBitmap(bit)
            }

        holder.bind(tienda,itemClickListener)

    }

    override fun getItemCount(): Int {
        return listaTiendas.size//para saber cuantos en la lista
    }

}

interface OnItemClickListenerTiendasFav{
    fun onItemClicked(tienda: FirestoreTiendasDto, position: Int)
}
