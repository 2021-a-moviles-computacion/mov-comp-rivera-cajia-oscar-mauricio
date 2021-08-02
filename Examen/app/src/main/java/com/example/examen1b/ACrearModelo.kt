package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class ACrearModelo : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_modelo)

        EBaseDeDatos.Entidades = ESqliteHelperDatabaseFMZeta(this)

        val id_fk = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.id
        val todo = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")

        val txtNombreModelo = findViewById<EditText>(R.id.txt_nombre_modelo)
        val txtPrecioModelo = findViewById<EditText>(R.id.txt_precio_modelo)
        val txtNPuertasModelo = findViewById<EditText>(R.id.txt_nPuertas_Modelo)
        val txtPuntExpModelo = findViewById<EditText>(R.id.txt_puntExpModelo)
        val txtSerieModelo = findViewById<EditText>(R.id.txt_serie_Modelo)

        val botonCrearFabricante = findViewById<Button>(R.id.btn_crear_modelo)
        botonCrearFabricante.setOnClickListener {
            crearModelo( txtNombreModelo.text.toString(), txtPrecioModelo.text.toString().toDouble(), txtNPuertasModelo.text.toString().toInt(), txtPuntExpModelo.text.toString().toDouble(), txtSerieModelo.text.toString(), id_fk.toString().toInt())
            abrirActividadConParametro(AModelos::class.java, todo as EFabricanteBDD)}

    }

        fun crearModelo( nombreModelo: String, precioModelo: Double, nPuertasModelo: Int, puntExpModelo: Double, serieModelo: String, id_fk:Int,) {

            val modCreado = EBaseDeDatos.Entidades
                ?.crearModelo( nombreModelo, precioModelo, nPuertasModelo, puntExpModelo, serieModelo, id_fk,)
            if(modCreado != null){
                if(modCreado){
                    Log.i("database","Modelo creado")
                    Log.i("database", "nombre Modelo ${nombreModelo}")
                }else{
                    Log.i("database","F")
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

    fun abrirActividadConParametro(clase: Class<*>, fabricanteId: EFabricanteBDD) {
        val intentExplicito = Intent(this, clase)

        //intentExplicito.putExtra(
        //"nombre",
        //"Oscar"
        //)//ese putextra solo permite trabjar ocn variables primitivas mas no con clases

        intentExplicito.putExtra(
            "idFabricante",
            fabricanteId
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}