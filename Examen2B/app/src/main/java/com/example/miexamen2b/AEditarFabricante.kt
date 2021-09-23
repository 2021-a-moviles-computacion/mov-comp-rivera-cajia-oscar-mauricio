package com.example.miexamen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.miexamen2b.dto.FirestoreFabricantesDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.WriteResult

import com.google.firebase.firestore.DocumentReference


class AEditarFabricante : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var fabricante: FirestoreFabricantesDto? = null
    var nombreFab: String? = null
    var tipoFab: String? = null
    var sedeFab: String? = null
    var fechaFab: String? = null
    var fundFab: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_fabricante)


        val intent = intent
        val idDocument = intent.getStringExtra("idFabricante")
        //val name = intent.getStringExtra("name")
        Log.i("list-view", "List vieweee ${idDocument}")

        val db = Firebase.firestore
        val referenciaFabricantes = db.collection("fabricantes").document(idDocument.toString())

        referenciaFabricantes
            .get()
            .addOnSuccessListener {
                val fabricanteCargado: FirestoreFabricantesDto? =
                    it.toObject(FirestoreFabricantesDto::class.java)

                nombreFab = fabricanteCargado?.nomF
                val txtNNombreFabricante = findViewById<EditText>(R.id.txt_Nnombre_modelo)
                txtNNombreFabricante.setText(nombreFab)

                tipoFab = fabricanteCargado?.tipoF
                val txtNTipoFabricante = findViewById<EditText>(R.id.txt_Nprecio_modelo)
                txtNTipoFabricante.setText(tipoFab)

                sedeFab = fabricanteCargado?.sedeF
                val txtNSedeFabricante = findViewById<EditText>(R.id.txt_NnPuertas_Modelo)
                txtNSedeFabricante.setText(sedeFab)

                fechaFab = fabricanteCargado?.fechaF
                val txtNFechaFabricante = findViewById<EditText>(R.id.txt_NpuntExpModelo)
                txtNFechaFabricante.setText(fechaFab)

                fundFab = fabricanteCargado?.fundF
                val txtNFundadorFabricante = findViewById<EditText>(R.id.txt_Nserie_Modelo)
                txtNFundadorFabricante.setText(fundFab)


                val botonActualizarFabricante = findViewById<Button>(R.id.btn_actualizar_modelo)
                botonActualizarFabricante.setOnClickListener {

                    val nuevoNombre = txtNNombreFabricante.getText().toString()
                    referenciaFabricantes.update("nomF", nuevoNombre)
                    val nuevoTipo = txtNTipoFabricante.getText().toString()
                    referenciaFabricantes.update("tipoF", nuevoTipo)
                    val nuevaSede = txtNSedeFabricante.getText().toString()
                    referenciaFabricantes.update("sedeF", nuevaSede)
                    val nuevaFecha = txtNFechaFabricante.getText().toString()
                    referenciaFabricantes.update("fechaF", nuevaFecha)
                    val nuevoFundador = txtNFundadorFabricante.getText().toString()
                    referenciaFabricantes.update("fundF", nuevoFundador)

                    abrirActividad(AFabricantes::class.java)
                }
            }
    }

    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }


}