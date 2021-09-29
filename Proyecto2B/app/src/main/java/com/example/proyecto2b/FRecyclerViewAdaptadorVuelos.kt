package com.example.proyecto2b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.example.proyecto2b.dto.FirestoreVuelosDto

class FRecyclerViewAdaptadorVuelos(private val contexto: GRecyclerViewVuelos,
                                        //private val listaRestaurantes: List<BNotifications>,
                                    private var listaVuelos: ArrayList<FirestoreVuelosDto>,
                                    private val recyclerView: RecyclerView,
                                    private val itemClickListener: OnItemClickListenerVuelos
): RecyclerView.Adapter<FRecyclerViewAdaptadorVuelos.MyViewHolder>(){


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val origen: TextView
        val destino: TextView
        val horaSalida: TextView
        val horaLlegada: TextView
        val duracion: TextView
        val aerolinea: TextView
        val precio: TextView

        init {
            origen = view.findViewById(R.id.tv_origen)
            destino = view.findViewById(R.id.tv_destino)
            horaSalida = view.findViewById(R.id.tv_horaSalida)
            horaLlegada = view.findViewById(R.id.tv_horaLlegada)
            duracion = view.findViewById(R.id.tv_duracion)
            aerolinea = view.findViewById(R.id.tv_aerolinea)
            precio = view.findViewById(R.id.tv_precioV)


        }


        fun bind(vuelo: FirestoreVuelosDto,clickListener: OnItemClickListenerVuelos)
        {

            itemView.setOnClickListener {
                clickListener.onItemClicked(vuelo, this.position)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_vuelos,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vuelo = listaVuelos[position]
        holder.aerolinea.text = vuelo.aerolineaV
        holder.origen.text = vuelo.origenV
        holder.destino.text = vuelo.destinoV
        holder.horaSalida.text = vuelo.horaSalidaV
        holder.horaLlegada.text = vuelo.horaLlegadaV
        holder.duracion.text = vuelo.duracionV
        holder.precio.text = "$"+ vuelo.precioV.toString()


        holder.bind(vuelo,itemClickListener)

    }

    override fun getItemCount(): Int {
        return listaVuelos.size//para saber cuantos en la lista
    }

}

interface OnItemClickListenerVuelos{
    fun onItemClicked(vuelo: FirestoreVuelosDto, position: Int)
}
