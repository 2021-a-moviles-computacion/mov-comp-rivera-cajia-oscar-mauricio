package com.example.miexamen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ACrearModelo : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_modelo)

        val intent = intent
        val idDocument = intent.getStringExtra("idFabricante")

        val txtNombreModelo = findViewById<EditText>(R.id.txt_nombre_modelo)
        val txtPrecioModelo = findViewById<EditText>(R.id.txt_precio_modelo)
        val txtNPuertasModelo = findViewById<EditText>(R.id.txt_nPuertas_Modelo)
        val txtPuntExpModelo = findViewById<EditText>(R.id.txt_puntExpModelo)
        val txtSerieModelo = findViewById<EditText>(R.id.txt_serie_Modelo)

        val txtLatitudModelo = findViewById<EditText>(R.id.txt_latitud)
        val txtLongitudModelo = findViewById<EditText>(R.id.txt_longitud)

        val botonCrearFabricante = findViewById<Button>(R.id.btn_crear_modelo)
        botonCrearFabricante.setOnClickListener {
            crearModelo(
                txtNombreModelo.text.toString(),
                txtPrecioModelo.text.toString().toDouble(),
                txtNPuertasModelo.text.toString().toInt(),
                txtPuntExpModelo.text.toString().toDouble(),
                txtSerieModelo.text.toString(),
                txtLatitudModelo.text.toString().toDouble(),
                txtLongitudModelo.text.toString().toDouble(),
                idDocument.toString()
            )
            if (idDocument != null) {
                abrirActividadConParametro(AModelos::class.java, idDocument)
            }
            //abrirActividad(AFabricantes::class.java)
        }

    }

    fun crearModelo(
        nombreModelo: String,
        precioModelo: Double,
        nPuertasModelo: Int,
        puntExpModelo: Double,
        serieModelo: String,
        latModelo: Double,
        longModelo: Double,
        id_fk: String,
    ) {

        val nuevoProducto = hashMapOf<String, Any>(
            "idF" to id_fk,
            "nomM" to nombreModelo,
            "preM" to precioModelo,
            "nPM" to nPuertasModelo,
            "puntosM" to puntExpModelo,
            "serieMF" to serieModelo,
            "latM" to latModelo,
            "longM" to longModelo
        )
        val db = Firebase.firestore
        val referencia = db.collection("modelos")

        referencia
            .add(nuevoProducto)
            .addOnFailureListener {}
    }

    fun abrirActividadConParametro(clase: Class<*>, fabricanteSeleccionado: String) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}