package com.example.proyecto2b

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class GRecyclerViewTiendas : AppCompatActivity(), OnItemClickListenerTiendas {

    var tienda: FirestoreTiendasDto? = null
    var bit: Bitmap? = null
    var d: Drawable? = null
    var listaTiendas = arrayListOf<FirestoreTiendasDto>()
    var listaTiendasFav = arrayListOf<FirestoreTiendasDto>()
    var listaVolverTiendas = arrayListOf<FirestoreTiendasDto>()

    //var listaTiendas = arrayListOf<FirestoreFavo>()

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var listIDTiendaFirestore: ArrayList<String>? = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view_tiendas)

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

        val intent = intent
        var muestraR = intent.getBooleanExtra("idFabricante", true)



            /**CARGAR RESTAURATES EN RECYCLER**/

            val db = Firebase.firestore
            val refTiendas = db.collection("tiendas")
            refTiendas
                .get()
                .addOnSuccessListener {
                    for (document in it) {
                        tienda = document.toObject(FirestoreTiendasDto::class.java)
                        val nombreT: String = tienda!!.nameT

                        listIDTiendaFirestore!!.add(document.id)

                        Log.i("firebase-firestore", "${nombreT}")

                        var referencia = Firebase.storage
                        //var nombreImg = referencia.reference.child("restaurantes/"+nombreT)
                        var nombreImg = referencia.reference.child("/restaurantes/fridays.jpg")

                        nombreImg.getBytes(10024*10024)
                            .addOnSuccessListener {
                                bit = BitmapFactory.decodeByteArray(it, 0, it.size)
                                d = BitmapDrawable(resources, bit)
                                Log.i("Firebase-Imagen", "Imagen recuperada->  ${dataDir}" )
                                //var imagen=findViewById<ImageView>(R.id.iv_caratula).setImageBitmap(bit)
                            }

                        listaTiendas.add(
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
                        listaTiendas,
                        this,
                        recyclerViewFeedHome
                    )
                }







        var botonDatosPrueb = findViewById<Button>(R.id.btn_tiendasFav)
        botonDatosPrueb.setOnClickListener {
            abrirActividad(GRecyclerViewTiendasFav::class.java)
        }

        }


    fun iniciarRecyclerView(
        lista: ArrayList<FirestoreTiendasDto>,
        actividad: GRecyclerViewTiendas,
        recyclerView: RecyclerView
    ) {

        val adaptador = FRecyclerViewAdaptadorTiendas(
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

    fun abrirActividadConParametro2(clase: Class<*>, MuestraTiendas: Boolean) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", MuestraTiendas
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

    fun cargarImagen(caratula: String){

    }
}