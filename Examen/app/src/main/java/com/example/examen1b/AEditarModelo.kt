package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AEditarModelo : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_modelo)
        EBaseDeDatos.Entidades = ESqliteHelperDatabaseFMZeta(this)

        val id = intent.getParcelableExtra<EModelosBDD>("idModelo")?.id
        Log.i("list-view", "List view ${id}")
        val todo = intent.getParcelableExtra<EModelosBDD>("idModelo")
        val todof = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")

        val nombreModelo = intent.getParcelableExtra<EModelosBDD>("idModelo")?.nombreModelo
        val precioModelo = intent.getParcelableExtra<EModelosBDD>("idModelo")?.precioModelo
        val nPModelo = intent.getParcelableExtra<EModelosBDD>("idModelo")?.nPuertasModelo
        val puntExpModelo = intent.getParcelableExtra<EModelosBDD>("idModelo")?.puntExpModelo
        val serieModelo = intent.getParcelableExtra<EModelosBDD>("idModelo")?.serieModelo

        val txtNNombreModelo = findViewById<EditText>(R.id.txt_Nnombre_modelo)
        txtNNombreModelo.setText(nombreModelo)

        val txtNPrecioModelo = findViewById<EditText>(R.id.txt_Nprecio_modelo)
        val stringdouble = precioModelo?.let { it.toString() }
        txtNPrecioModelo.setText(stringdouble)

        val txtNNPuertasModelo = findViewById<EditText>(R.id.txt_NnPuertas_Modelo)
        val stringInt = nPModelo?.let { it.toString() }
        txtNNPuertasModelo.setText(stringInt)

        val txtNPuntExpModelo = findViewById<EditText>(R.id.txt_NpuntExpModelo)
        val stringdouble1 = puntExpModelo?.let { it.toString() }
        txtNPuntExpModelo.setText(stringdouble1)

        val txtNSerieModelo = findViewById<EditText>(R.id.txt_Nserie_Modelo)
        txtNSerieModelo.setText(serieModelo)

        val botonActualizarModelo = findViewById<Button>(R.id.btn_actualizar_modelo)
        botonActualizarModelo.setOnClickListener{
            actualizarModelo(
                txtNNombreModelo.text.toString(),
                txtNPrecioModelo.text.toString().toDouble(),
                txtNNPuertasModelo.text.toString().toInt(),
                txtNPuntExpModelo.text.toString().toDouble(),
                txtNSerieModelo.text.toString(),
                id.toString().toInt())
            abrirActividadConParametro(AModelos::class.java, todo as EModelosBDD, todof as EFabricanteBDD)
            //abrirActividad(AModelos::class.java)
        }

    }

    fun actualizarModelo(nombreModelo: String,
                         precioModelo: Double,
                         nPuertasModelo: Int,
                         puntExpModelo: Double,
                         serieModelo: String,
                         id: Int) {

        val fabActualizado = EBaseDeDatos.Entidades
            ?.actualizarModelo(nombreModelo, precioModelo, nPuertasModelo, puntExpModelo, serieModelo, id)
        if(fabActualizado != null){
            if (fabActualizado){
                Log.i("database","Modelo actualizado")
            }else{
                Log.i("database","F")
            }
        }
    }

    fun abrirActividadConParametro(clase: Class<*>, modeloId: EModelosBDD, fabricanteId: EFabricanteBDD) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idModelo",
            modeloId
        )
        intentExplicito.putExtra(
            "idFabricante",
            fabricanteId
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