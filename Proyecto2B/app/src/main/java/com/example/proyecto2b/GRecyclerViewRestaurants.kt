package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreRestaurantesDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.widget.Button
import android.widget.ImageView

class GRecyclerViewRestaurants : AppCompatActivity(), OnItemClickListener {

    var restaurante: FirestoreRestaurantesDto? = null
    var listaRestaurants = arrayListOf<FirestoreRestaurantesDto>()

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var listIDResFirestore: ArrayList<String>? = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view_restaurants)

        val messagesIcon = findViewById<ImageView>(R.id.icon_home)
        messagesIcon.setOnClickListener {
            abrirActividadConParametro2(MainActivity::class.java, true)
        }

        val homeIcon = findViewById<ImageView>(R.id.icon_profile)
        homeIcon.setOnClickListener {
            abrirActividad(AUsuarioDetalle::class.java)
        }

        val botonVuelos = findViewById<ImageView>(R.id.icon_planes)
        botonVuelos.setOnClickListener {
            abrirActividad(GRecyclerViewVuelos::class.java)
        }

        /**CARGAR RESTAURATES EN RECYCLER**/

        val db = Firebase.firestore
        val refRestaurantes = db.collection("restaurantes")
        refRestaurantes
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    restaurante = document.toObject(FirestoreRestaurantesDto::class.java)
                    val nombreR: String = restaurante!!.nameR

                    listIDResFirestore!!.add(document.id)

                    Log.i("firebase-firestore", "${nombreR}")

                    listaRestaurants.add(
                        FirestoreRestaurantesDto(
                            nombreR,
                            restaurante!!.locationR,
                            restaurante!!.typeR,
                            restaurante!!.hoursR,
                            restaurante!!.phoneR,
                            restaurante!!.descriptionR
                        )
                    )


                }

                val recyclerViewFeedHome = findViewById<RecyclerView>(R.id.rv_restaurants)

                iniciarRecyclerView(
                    listaRestaurants,
                    this,
                    recyclerViewFeedHome
                )
            }

        var botonDatosPrueb = findViewById<Button>(R.id.btn_restaurantesFav)
        botonDatosPrueb.setOnClickListener {
            abrirActividad(GRecyclerViewRestaurantsFav::class.java)
        }


    }

    fun iniciarRecyclerView(
        lista: ArrayList<FirestoreRestaurantesDto>,
        actividad: GRecyclerViewRestaurants,
        recyclerView: RecyclerView
    ) {

        val adaptador = FRecyclerViewAdaptadorRestaurants(
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
        val idRestauranteSeleccionado: String = listIDResFirestore!![position]
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

    fun abrirActividadConParametro2(clase: Class<*>, MuestraTiendas: Boolean) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", MuestraTiendas
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


}