package com.example.deber02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNotifications(private val contexto: GRecyclerViewFeed,
                                          private val listaNotifications: List<BNotifications>,
                                          private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNotifications.MyViewHolder>(){


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mensaje: TextView
        val tweet: TextView
        val notiIcon: ImageView

        val uNoti0: ImageView
        val uNoti1: ImageView
        val uNoti2: ImageView
        val uNoti3: ImageView
        val uNoti4: ImageView
        val uNoti5: ImageView



        init {
            mensaje = view.findViewById(R.id.tv_messageN)
            tweet = view.findViewById(R.id.tv_tweetN)
            notiIcon = view.findViewById(R.id.img_noti)

            uNoti0 = view.findViewById(R.id.img_unoti0)
            uNoti1 = view.findViewById(R.id.img_unoti1)
            uNoti2 = view.findViewById(R.id.img_unoti2)
            uNoti3 = view.findViewById(R.id.img_unoti3)
            uNoti4 = view.findViewById(R.id.img_unoti4)
            uNoti5 = view.findViewById(R.id.img_unoti5)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_view_notifications,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val noti = listaNotifications[position]
        holder.mensaje.text = noti.notification
        holder.tweet.text = noti.tweet

        holder.notiIcon.setImageResource(noti.imageFav)

        noti.image0?.let { holder.uNoti0.setImageResource(it) }
        noti.image1?.let { holder.uNoti1.setImageResource(it) }
        noti.image2?.let { holder.uNoti2.setImageResource(it) }
        noti.image3?.let { holder.uNoti3.setImageResource(it) }
        noti.image4?.let { holder.uNoti4.setImageResource(it) }
        noti.image5?.let { holder.uNoti5.setImageResource(it) }


    }

    override fun getItemCount(): Int {
        return listaNotifications.size//para saber cuantos en la lista
    }

}