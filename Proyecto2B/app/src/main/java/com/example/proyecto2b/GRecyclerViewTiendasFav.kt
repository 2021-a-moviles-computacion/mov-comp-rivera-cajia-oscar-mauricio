package com.example.proyecto2b

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.FirebaseStorage

import com.google.firebase.storage.StorageReference




class GRecyclerViewTiendasFav : AppCompatActivity(), OnItemClickListenerTiendasFav {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var tienda: FirestoreTiendasDto? = null
    var listaTiendasFav = arrayListOf<FirestoreTiendasDto>()
    var listIDTiendaFirestore: ArrayList<String>? = ArrayList<String>()
    var bit: Bitmap? = null
    var d: Drawable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view_tiendas_fav)

        //abrirActividad(AFavoritos::class.java)

        var botonDatosPrueb = findViewById<Button>(R.id.btn_mostrarTiendas)
        botonDatosPrueb.setOnClickListener {
            abrirActividad(GRecyclerViewTiendas::class.java)
        }


        val db = Firebase.firestore
        val refTiendas = db.collection("tiendas")
        refTiendas
            .whereArrayContainsAny("favorites", arrayListOf(BAuthUsuario.usuario!!.email))
            .get()
            .addOnSuccessListener {
                Log.i("consultas", "${it.documents}")
                for (document in it){
                    tienda = document.toObject(FirestoreTiendasDto::class.java)
                    val nombreT: String = tienda!!.nameT

                    listIDTiendaFirestore!!.add(document.id)

                    Log.i("firebase-firestore", "${nombreT}")


                    listaTiendasFav.add(
                        FirestoreTiendasDto(
                            nombreT,
                            tienda!!.locationT,
                            tienda!!.typeT,
                            tienda!!.hoursT,
                            tienda!!.phoneT,
                            tienda!!.descriptionT,
                            tienda!!.favorites
                        )
                    )
                }



                val recyclerViewFeedHome = findViewById<RecyclerView>(R.id.rv_tiendas)

                iniciarRecyclerView(
                    listaTiendasFav,
                    this,
                    recyclerViewFeedHome
                )


            }

    }


    fun iniciarRecyclerView(
        lista: ArrayList<FirestoreTiendasDto>,
        actividad: GRecyclerViewTiendasFav,
        recyclerView: RecyclerView
    ) {

        val adaptador = FRecyclerViewAdaptadorTiendasFav(
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

    override fun onItemClicked(tienda: FirestoreTiendasDto, position: Int) {

        val posicionSeleccionada = position
        val idRestauranteSeleccionado: String = listIDTiendaFirestore!![position]
        Toast.makeText(this,"User name ${tienda.nameT} \n Phone:${tienda.typeT}", Toast.LENGTH_LONG)
            .show()
        Log.i("USER_",tienda.nameT)
        Log.i("USER_", idRestauranteSeleccionado)
        abrirActividadConParametro(ATiendaDetalle::class.java, idRestauranteSeleccionado)

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
