package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {

    var totalLikes = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrenador>()
        val ligaPokemon = DLiga("Kanto", "Liga Kanto")

        listaEntrenador.add(
            BEntrenador("Oscar",
            "333333333",
            ligaPokemon
            )
        )

        listaEntrenador.add(
            BEntrenador("Jelp",
                "666666666",
                ligaPokemon
            )
        )

        listaEntrenador.add(
            BEntrenador("Mujik",
                "000000000",
                ligaPokemon
            )
        )

        val recyclerViewEntrenador = findViewById<RecyclerView>(R.id.rv_entrenadores)

        iniciarRecyclerView(listaEntrenador,
                            this,
                            recyclerViewEntrenador)
    }


    ///
    fun iniciarRecyclerView(lista: List<BEntrenador>,
                            actividad: GRecyclerView,
                            recyclerView: RecyclerView) {

        val adaptador = FRecyclerViewAdaptadorNombreCedula( actividad,
                                                            lista,
                                                            recyclerView)

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val textView = findViewById<TextView>(R.id.tv_total_likes)
        textView.text = totalLikes.toString()
    }

}