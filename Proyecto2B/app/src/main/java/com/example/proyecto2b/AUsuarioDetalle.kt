package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.proyecto2b.dto.FirestoreUsuarioDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AUsuarioDetalle : AppCompatActivity() {

    var nombreUsuario: String? = null
    var correoUsuario: String? = null
    var ciudadUsuario: String? = null
    var telefonoUsuario: Int? = null
    var generoUsuario: String? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ausuario_detalle)

        val messagesIcon = findViewById<Button>(R.id.btn_editarUsuario)
        messagesIcon.setOnClickListener {
            abrirActividad(AEditarUsuario::class.java)
        }

        val messagesIco = findViewById<ImageView>(R.id.icon_home)
        messagesIco.setOnClickListener {
            abrirActividadConParametro2(MainActivity::class.java, true)
        }

        val botonVuelos = findViewById<ImageView>(R.id.icon_planes)
        botonVuelos.setOnClickListener {
            abrirActividad(GRecyclerViewVuelos::class.java)
        }

        var usuario: FirestoreUsuarioDto? = null

        //var id = BAuthUsuario.usuario!!.email

        val db = Firebase.firestore
        val referenciaUsuario = db.collection("usuario").document(BAuthUsuario.usuario!!.email)

        referenciaUsuario
            .get()
            .addOnSuccessListener {
                val usuarioCargado: FirestoreUsuarioDto? =
                    it.toObject(FirestoreUsuarioDto::class.java)
                //Log.i("intent", "intent idFabricante ${id}")

                //nombreUsuario = usuarioCargado!!.name
                nombreUsuario = usuarioCargado?.name
                val txtNNombreModelo = findViewById<TextView>(R.id.tv_nombreU)
                txtNNombreModelo.text = nombreUsuario

                correoUsuario = usuarioCargado?.email
                val tvCorreoU = findViewById<TextView>(R.id.tv_correoU)
                tvCorreoU.setText(correoUsuario)

                ciudadUsuario = usuarioCargado?.city
                val tvCiudadU = findViewById<TextView>(R.id.tv_ciudadU)
                tvCiudadU.setText(ciudadUsuario)

                telefonoUsuario = usuarioCargado?.phone
                val tvTelefonoU = findViewById<TextView>(R.id.tv_telefonoU)
                tvTelefonoU.setText(telefonoUsuario.toString())

                generoUsuario = usuarioCargado?.gender
                val tvGeneroU = findViewById<TextView>(R.id.tv_generoU)
                tvGeneroU.setText(generoUsuario)

            }

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