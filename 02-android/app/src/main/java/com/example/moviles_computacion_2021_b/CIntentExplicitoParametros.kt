package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

//aqui se estan enviando los parametros

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)


        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")


        Log.i("intent-explicito", "${nombre}")
        Log.i("intent-explicito", "${apellido}")
        Log.i("intent-explicito", "${edad}")
        Log.i("intent-explicito", "${entrenador}")


        val botonDevolverRespuesta = findViewById<Button>(R.id.btn_devolver_respuesta)

        botonDevolverRespuesta
            .setOnClickListener{
                val intentDeolverParametros = Intent()
                intentDeolverParametros.putExtra("nombreModificado", "Rivvvera")
                intentDeolverParametros.putExtra("edadModificado", 24)
                intentDeolverParametros.putExtra(
                    "entrenadorModificado",
                    BEntrenador("OScar", "Rivera", null)
                )
                setResult(
                    RESULT_OK,
                    intentDeolverParametros
                )
                finish()
            }

    }

}