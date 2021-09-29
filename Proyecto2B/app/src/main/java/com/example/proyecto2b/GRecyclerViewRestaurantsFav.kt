package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreRestaurantesDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GRecyclerViewRestaurantsFav : AppCompatActivity(), OnItemClickListenerRestaurantsFav {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var restaurante: FirestoreRestaurantesDto? = null
    var listaRestaurantesFav = arrayListOf<FirestoreRestaurantesDto>()
    var listIDRestauranteFirestore: ArrayList<String>? = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view_restaurants_fav)


        var botonDatosPrueb = findViewById<Button>(R.id.btn_mostrarRestaurantes)
        botonDatosPrueb.setOnClickListener {
            abrirActividad(GRecyclerViewRestaurants::class.java)
        }

        val db = Firebase.firestore
        val refTiendas = db.collection("restaurantes")
        refTiendas
            .whereArrayContainsAny("favorites", arrayListOf(BAuthUsuario.usuario!!.email))
            .get()
            .addOnSuccessListener {
                Log.i("consultas", "${it.documents}")
                for (document in it){
                    restaurante = document.toObject(FirestoreRestaurantesDto::class.java)
                    val nombreR: String = restaurante!!.nameR

                    listIDRestauranteFirestore!!.add(document.id)

                    Log.i("firebase-firestore", "${nombreR}")

                    listaRestaurantesFav.add(
                        FirestoreRestaurantesDto(
                            nombreR,
                            restaurante!!.locationR,
                            restaurante!!.typeR,
                            restaurante!!.hoursR,
                            restaurante!!.phoneR,
                            restaurante!!.descriptionR,
                            restaurante!!.favorites
                        )
                    )
                }



                val recyclerViewFeedHome = findViewById<RecyclerView>(R.id.rv_tiendas)

                iniciarRecyclerView(
                    listaRestaurantesFav,
                    this,
                    recyclerViewFeedHome
                )


            }

    }

    fun iniciarRecyclerView(
        lista: ArrayList<FirestoreRestaurantesDto>,
        actividad: GRecyclerViewRestaurantsFav,
        recyclerView: RecyclerView
    ) {

        val adaptador = FRecyclerViewAdaptadorRestaurantsFav(
            actividad,
            lista,
            recyclerView,
            this
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    override fun onItemClicked(restaurante: FirestoreRestaurantesDto, position: Int) {

        val posicionSeleccionada = position
        val idRestauranteSeleccionado: String = listIDRestauranteFirestore!![position]
        Toast.makeText(this,"User name ${restaurante.nameR} \n Phone:${restaurante.typeR}", Toast.LENGTH_LONG)
            .show()
        Log.i("USER_",restaurante.nameR)
        Log.i("USER_", idRestauranteSeleccionado)
        abrirActividadConParametro(ARestauranteDetalle::class.java, idRestauranteSeleccionado)
    }

    fun abrirActividadConParametro(clase: Class<*>, fabricanteSeleccionado: String) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }

}