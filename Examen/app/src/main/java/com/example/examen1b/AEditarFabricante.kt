package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class AEditarFabricante : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_fabricante)

        EBaseDeDatos.Entidades = ESqliteHelperDatabaseFMZeta(this)



        //val txtNNombreFabricante = findViewById<EditText>(R.id.txt_Nnombre_fabricante)
        val id = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.id
        val nombreFab = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.nomFabricante
        val tipoFab = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.tipoFabricante
        val sedeFab = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.sedeFabricante
        val fechaFab = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.fechaFabricante
        val fundFab = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.fundadorFabricante


        val txtNNombreFabricante = findViewById<EditText>(R.id.txt_Nnombre_modelo)
        txtNNombreFabricante.setText(nombreFab)
        val txtNTipoFabricante = findViewById<EditText>(R.id.txt_Nprecio_modelo)
        txtNTipoFabricante.setText(tipoFab)
        val txtNSedeFabricante = findViewById<EditText>(R.id.txt_NnPuertas_Modelo)
        txtNSedeFabricante.setText(sedeFab)
        val txtNFechaFabricante = findViewById<EditText>(R.id.txt_NpuntExpModelo)
        txtNFechaFabricante.setText(fechaFab)
        val txtNFundadorFabricante = findViewById<EditText>(R.id.txt_Nserie_Modelo)
        txtNFundadorFabricante.setText(fundFab)

        val botonActualizarFabricante = findViewById<Button>(R.id.btn_actualizar_modelo)
        botonActualizarFabricante.setOnClickListener{
            actualizarFabricante(txtNNombreFabricante.getText().toString(), txtNTipoFabricante.getText().toString(), txtNSedeFabricante.getText().toString(), txtNFechaFabricante.getText().toString(), txtNFundadorFabricante.getText().toString(), id.toString().toInt())
            //abrirActividadConParametro(AFabricantes::class.java, id as EFabricanteBDD)
            abrirActividad(AFabricantes::class.java)
        }
    }

    fun actualizarFabricante(nomFabricante: String, tipoFabricante: String, sedeFabricante: String, fechaFabricante: String, fundadorFabricante: String, id: Int) {

        val fabActualizado = EBaseDeDatos.Entidades
            ?.actualizarFabricante(nomFabricante, tipoFabricante, sedeFabricante, fechaFabricante, fundadorFabricante, id)
        if(fabActualizado != null){
            if (fabActualizado){
                Log.i("database","Fabricante actualizado")
            }else{
                Log.i("database","F")
            }
        }
    }


    fun abrirActividadConParametro(clase: Class<*>, fabricanteId: EFabricanteBDD) {
        val intentExplicito = Intent(this, clase)

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