package com.example.proyecto2b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto2b.dto.FirestoreUsuarioDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AEditarUsuario : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401


    var nombreUsuario: String? = null
    var telefonoUsuario: Int? = null
    var generoUsuario: String? = null
    var ciudadUsuario: String? = null

    var generoSeleccionado: String = "perro"
    var posicionSeleccionado: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edite)

        val list = ArrayList<String>()
        list.add("Selecciones su género")
        list.add("Hombre")
        list.add("Mujer")

        val spinnerRestaurante = findViewById<Spinner>(R.id.spinner)
        val spinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRestaurante.adapter = spinnerArrayAdapter
        spinnerRestaurante.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                generoSeleccionado = list!![position]
                posicionSeleccionado = position
                Log.i("firebase-firestore", "Género" + generoSeleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                // sometimes you need nothing here
            }
        }
        val compareValue = "some value"


        val db = Firebase.firestore
        val referenciaUsuario = db.collection("usuario").document(BAuthUsuario.usuario!!.email)
        referenciaUsuario
            .get()
            .addOnSuccessListener {
                val usuarioCargado: FirestoreUsuarioDto? =
                    it.toObject(FirestoreUsuarioDto::class.java)

                nombreUsuario = usuarioCargado?.name
                val txtNNombreModelo = findViewById<EditText>(R.id.edit_text_name)
                txtNNombreModelo.setText(nombreUsuario)

                telefonoUsuario = usuarioCargado?.phone
                val txtNNombreMode = findViewById<EditText>(R.id.edit_text_phone)
                txtNNombreMode.setText(telefonoUsuario.toString())
                Log.i("firebase-firestore", "telefono" + telefonoUsuario)

                val spinnerPosition: Int = spinnerArrayAdapter.getPosition(usuarioCargado?.gender)
                spinnerRestaurante.setSelection(spinnerPosition)

                ciudadUsuario = usuarioCargado?.city
                val txtNNombreModel = findViewById<EditText>(R.id.edit_text_city)
                txtNNombreModel.setText(ciudadUsuario)


                val botonActualizarModelo = findViewById<Button>(R.id.button_editar)
                botonActualizarModelo.setOnClickListener {

                    val nuevoNombre = txtNNombreModelo.text.toString()
                    referenciaUsuario.update("name", nuevoNombre)

                    val nuevoTelefono = txtNNombreMode.getText().toString().toInt()
                    referenciaUsuario.update("phone", nuevoTelefono)

                    val nuevoGenero = generoSeleccionado
                    referenciaUsuario.update("gender", nuevoGenero)


                    val nuevaPunt = txtNNombreModel.text.toString()
                    referenciaUsuario.update("city", nuevaPunt)


                    //abrirActividad(AModelos::class.java)

                    abrirActividad(AUsuarioDetalle::class.java)

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