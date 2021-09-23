package com.example.miexamen2b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.miexamen2b.dto.FirestoreModelosDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AEditarModelo : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var fabricante: FirestoreModelosDto? = null

    var nombreModelo: String? = null
    var precioModelo: Double? = null
    var nPModelo: Int? = null
    var puntExpModelo: Double? = null
    var serieModelo: String? = null

    var latitudModelo: Double? = null
    var longitudModelo: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_modelo)
        //EBaseDeDatos.Entidades = ESqliteHelperDatabaseFMZeta(this)


        val intent = intent
        val idDocument = intent.getStringExtra("idModelo")
        Log.i("intent", "intent idModelo ${idDocument}")
        val idDocumentF = intent.getStringExtra("idFabricante")
        Log.i("intent", "intent idFabricante ${idDocumentF}")

        val db = Firebase.firestore
        val referenciaModeloSeleccionado = db.collection("modelos").document(idDocument.toString())

        referenciaModeloSeleccionado
            .get()
            .addOnSuccessListener {
                val modeloCargado: FirestoreModelosDto? =
                    it.toObject(FirestoreModelosDto::class.java)

                nombreModelo = modeloCargado?.nomM
                val txtNNombreModelo = findViewById<EditText>(R.id.txt_Nnombre_modelo)
                txtNNombreModelo.setText(nombreModelo)

                precioModelo = modeloCargado?.preM
                val txtNPrecioModelo = findViewById<EditText>(R.id.txt_Nprecio_modelo)
                val stringdouble = precioModelo?.let { it.toString() }
                txtNPrecioModelo.setText(stringdouble)

                nPModelo = modeloCargado?.nPM
                val txtNNPuertasModelo = findViewById<EditText>(R.id.txt_NnPuertas_Modelo)
                val stringInt = nPModelo?.let { it.toString() }
                txtNNPuertasModelo.setText(stringInt)

                puntExpModelo = modeloCargado?.puntosM
                val txtNPuntExpModelo = findViewById<EditText>(R.id.txt_NpuntExpModelo)
                val stringdouble1 = puntExpModelo?.let { it.toString() }
                txtNPuntExpModelo.setText(stringdouble1)

                serieModelo = modeloCargado?.serieMF
                val txtNSerieModelo = findViewById<EditText>(R.id.txt_Nserie_Modelo)
                txtNSerieModelo.setText(serieModelo)

                latitudModelo = modeloCargado?.latM
                val txtNLatitudModelo = findViewById<EditText>(R.id.txt_NLatitud)
                val stringdouble2 = latitudModelo?.let { it.toString() }
                txtNLatitudModelo.setText(stringdouble2)

                longitudModelo = modeloCargado?.longM
                val txtNLongitudModelo = findViewById<EditText>(R.id.txt_NLongitud)
                val stringdouble3 = longitudModelo?.let { it.toString() }
                txtNLongitudModelo.setText(stringdouble3)


                val botonActualizarModelo = findViewById<Button>(R.id.btn_actualizar_modelo)
                botonActualizarModelo.setOnClickListener {

                    val nuevoNombre = txtNNombreModelo.text.toString()
                    referenciaModeloSeleccionado.update("nomM", nuevoNombre)

                    val nuevoPrecio = txtNPrecioModelo.text.toString().toDouble()
                    referenciaModeloSeleccionado.update("preM", nuevoPrecio)

                    val nuevoNPuertas = txtNNPuertasModelo.getText().toString().toInt()
                    referenciaModeloSeleccionado.update("nPM", nuevoNPuertas)

                    val nuevaPunt = txtNPuntExpModelo.getText().toString().toDouble()
                    referenciaModeloSeleccionado.update("puntosM", nuevaPunt)

                    val nuevaSerie = txtNSerieModelo.getText().toString()
                    referenciaModeloSeleccionado.update("serieMF", nuevaSerie)

                    val nuevaLat = txtNLatitudModelo.getText().toString().toDouble()
                    referenciaModeloSeleccionado.update("latM", nuevaLat)

                    val nuevaLong = txtNLongitudModelo.getText().toString().toDouble()
                    referenciaModeloSeleccionado.update("longM", nuevaLong)

                    //abrirActividad(AModelos::class.java)
                    if (idDocumentF != null) {
                        abrirActividadConParametro(AModelos::class.java, idDocumentF)
                    }
                }
            }


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