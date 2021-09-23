package com.example.deber02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorMessages(private val contexto: GRecyclerViewFeed,
                                     private val listaMessages: List<BFeedHome>,
                                     private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorMessages.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val usernameTextView: TextView
        val useridTextView: TextView
        val fecha: TextView
        val mensaje: TextView
        val userImage: ImageView


        init {
            usernameTextView = view.findViewById(R.id.tv_username)
            useridTextView = view.findViewById(R.id.tv_tema)
            userImage = view.findViewById(R.id.img_user)
            mensaje = view.findViewById(R.id.tv_message)
            fecha = view.findViewById(R.id.tv_userid2)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_view_messages,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = listaMessages[position]
        holder.usernameTextView.text = user.username
        holder.useridTextView.text = user.userid
        holder.userImage.setImageResource(user.image)
        holder.mensaje.text = user.message
        holder.fecha.text = user.date
    }

    override fun getItemCount(): Int {
        return listaMessages.size//para saber cuantos en la lista
    }
}













