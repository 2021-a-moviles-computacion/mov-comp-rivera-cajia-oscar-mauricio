package com.example.deber02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorTrends(
    private val contexto: GRecyclerViewFeed,
    private val listaTrends: List<BTrends>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorTrends.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tema: TextView
        val keyword: TextView
        val ntweets: TextView

        init {
            tema = view.findViewById(R.id.tv_tema)
            keyword = view.findViewById(R.id.tv_keyword)
            ntweets = view.findViewById(R.id.tv_ntweets)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_view_trends,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trend = listaTrends[position]
        holder.tema.text = trend.tema
        holder.keyword.text = trend.keyword
        holder.ntweets.text = trend.ntweets
    }

    override fun getItemCount(): Int {
        return listaTrends.size//para saber cuantos en la lista
    }

}