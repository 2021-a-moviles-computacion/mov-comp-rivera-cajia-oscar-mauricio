package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class ACrearFabricante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_fabricante)

        EBaseDeDatos.Entidades = ESqliteHelperDatabaseFMZeta(this)

        val txtNombreFabricante = findViewById<EditText>(R.id.txt_nombre_modelo)
        val txtTipoFabricante = findViewById<EditText>(R.id.txt_precio_modelo)
        val txtSedeFabricante = findViewById<EditText>(R.id.txt_nPuertas_Modelo)
        val txtFechaFabricante = findViewById<EditText>(R.id.txt_puntExpModelo)
        val txtFundadorFabricante = findViewById<EditText>(R.id.txt_serie_Modelo)

        val botonCrearFabricante = findViewById<Button>(R.id.btn_crear_modelo)
        botonCrearFabricante.setOnClickListener {
            crearFabricante(txtNombreFabricante.getText().toString(), txtTipoFabricante.getText().toString(), txtSedeFabricante.getText().toString(), txtFechaFabricante.getText().toString(), txtFundadorFabricante.getText().toString())
            abrirActividad(AFabricantes::class.java)
        }
    }

    fun crearFabricante(nomFabricante: String, tipoFabricante: String, sedeFabricante: String, fechaFabricante: String, fundadorFabricante: String) {

        val fabCreado = EBaseDeDatos.Entidades
            ?.crearFabricante(nomFabricante,tipoFabricante, sedeFabricante, fechaFabricante, fundadorFabricante)
        if(fabCreado != null){
            if(fabCreado){
                Log.i("database","Fabricante creado")
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
}