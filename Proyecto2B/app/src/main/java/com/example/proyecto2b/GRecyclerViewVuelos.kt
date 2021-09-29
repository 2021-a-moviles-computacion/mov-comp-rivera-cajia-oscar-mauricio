package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.example.proyecto2b.dto.FirestoreVuelosDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GRecyclerViewVuelos : AppCompatActivity(), OnItemClickListenerVuelos {

    var vuelo: FirestoreVuelosDto? = null
    var listaVuelos = arrayListOf<FirestoreVuelosDto>()
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var listIDVueloFirestore: ArrayList<String>? = ArrayList<String>()
    var listUrlVueloFirestore: ArrayList<String>? = ArrayList<String>()
    var listTelefonoVueloFirestore: ArrayList<Int>? = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view_vuelos)

        val messagesIcon = findViewById<ImageView>(R.id.icon_home)
        messagesIcon.setOnClickListener {
            abrirActividadConParametro2(MainActivity::class.java, true)
        }

        val homeIcon = findViewById<ImageView>(R.id.icon_profile)
        homeIcon.setOnClickListener {
            abrirActividad(AUsuarioDetalle::class.java)
        }



        /**CARGAR VUELOS EN RECYCLER**/

        val db = Firebase.firestore
        val refTiendas = db.collection("vuelos")
        refTiendas
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    vuelo = document.toObject(FirestoreVuelosDto::class.java)
                    val nombreV: String = vuelo!!.aerolineaV

                    listIDVueloFirestore!!.add(document.id)
                    listUrlVueloFirestore!!.add(vuelo!!.urlV)
                    listTelefonoVueloFirestore!!.add(vuelo!!.telefonoV)

                    Log.i("firebase-firestore", "${nombreV}")

                    listaVuelos.add(
                        FirestoreVuelosDto(
                            nombreV,
                            vuelo!!.fechaSalidaV,
                            vuelo!!.fechaLlegadaV,
                            vuelo!!.origenV,
                            vuelo!!.destinoV,
                            vuelo!!.precioV,
                            vuelo!!.horaSalidaV,
                            vuelo!!.horaLlegadaV,
                            vuelo!!.duracionV,
                            vuelo!!.urlV,
                            vuelo!!.telefonoV
                        )
                    )


                }

                val recyclerViewFeedHome = findViewById<RecyclerView>(R.id.rv_vuelos)

                iniciarRecyclerView(
                    listaVuelos,
                    this,
                    recyclerViewFeedHome
                )
            }
    }



    fun iniciarRecyclerView(
        lista: ArrayList<FirestoreVuelosDto>,
        actividad: GRecyclerViewVuelos,
        recyclerView: RecyclerView
    ) {

        val adaptador = FRecyclerViewAdaptadorVuelos(
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

    override fun onItemClicked(vuelo: FirestoreVuelosDto, position: Int) {

        val posicionSeleccionada = position
        val idVueloSeleccionado: String = listIDVueloFirestore!![position]
        val URLVueloSeleccionado: String = listUrlVueloFirestore!![position]
        val telefonoSeleccionado: Int = listTelefonoVueloFirestore!![position]
        Toast.makeText(this,"User name ${vuelo.aerolineaV} \n Phone:${vuelo.horaLlegadaV}", Toast.LENGTH_LONG)
            .show()
        Log.i("USER_",vuelo.aerolineaV)
        Log.i("USER_", idVueloSeleccionado)
        abrirActividadConParametro3(AVueloDetalle::class.java, idVueloSeleccionado, URLVueloSeleccionado, telefonoSeleccionado )

    }

    fun abrirActividadConParametro(clase: Class<*>, fabricanteSeleccionado: String) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActividadConParametro3(clase: Class<*>, fabricanteSeleccionado: String, urlVuelo: String, telefonoVuelo: Int) {
        val intentExplicito = Intent(this, clase)
        val intentExplicito2 = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        intentExplicito.putExtra(
            "urlVuelo", urlVuelo
        )
        intentExplicito.putExtra(
            "telefonoVuelo", telefonoVuelo
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