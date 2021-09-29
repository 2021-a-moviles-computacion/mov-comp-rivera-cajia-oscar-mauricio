package com.example.proyecto2b

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreRestaurantesDto
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FRecyclerViewAdaptadorRestaurantsFav(private val contexto: GRecyclerViewRestaurantsFav,
                                        //private val listaRestaurantes: List<BNotifications>,
                                        private var listaRestaurantes: ArrayList<FirestoreRestaurantesDto>,
                                        private val recyclerView: RecyclerView,
                                        private val itemClickListener: OnItemClickListenerRestaurantsFav
): RecyclerView.Adapter<FRecyclerViewAdaptadorRestaurantsFav.MyViewHolder>(){


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nombreRestaurante: TextView
        val descripcionRestaurante: TextView
        val logoRestaurante: ImageView

        init {
            nombreRestaurante = view.findViewById(R.id.tv_nombreRestaurante)
            descripcionRestaurante = view.findViewById(R.id.tv_descripcionRestaurante)
            logoRestaurante = view.findViewById(R.id.img_logoRestaurant)


            logoRestaurante.setOnClickListener{

            }

        }


        fun bind(restaurante: FirestoreRestaurantesDto,clickListener: OnItemClickListenerRestaurantsFav)
        {
            nombreRestaurante.text = restaurante.nameR
            descripcionRestaurante.text = restaurante.descriptionR

            itemView.setOnClickListener {
                clickListener.onItemClicked(restaurante, this.position)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_restaurants,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val restaurante = listaRestaurantes[position]
        holder.nombreRestaurante.text = restaurante.nameR
        holder.descripcionRestaurante.text = restaurante.descriptionR
        var referenci = Firebase.storage
        var nombreImg = referenci.reference.child("restaurantes/"+restaurante.nameR.toString()+".jpg")
        Log.i("Firebase-Imagen", "restaurantes/"+restaurante.nameR.toString()+".jpg" )
        nombreImg.getBytes(10024*10024)
            .addOnSuccessListener {
                val bit: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                //Log.i("Firebase-Imagen", "Imagen recuperada->  ${dataDir}" )
                Log.i("Firebase-Imagen", "Imagen recuperada->  ${bit}" )
                //var imagen =findViewById<ImageView>(R.id.imageView).setImageBitmap(bit)
                holder.logoRestaurante.setImageBitmap(bit)
            }

        holder.bind(restaurante,itemClickListener)

    }

    override fun getItemCount(): Int {
        return listaRestaurantes.size//para saber cuantos en la lista
    }
}

interface OnItemClickListenerRestaurantsFav{
    fun onItemClicked(restaurante: FirestoreRestaurantesDto, position: Int)
}
