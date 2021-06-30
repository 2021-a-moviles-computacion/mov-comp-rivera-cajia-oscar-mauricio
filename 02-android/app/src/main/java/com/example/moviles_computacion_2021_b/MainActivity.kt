package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIrACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonIrACicloVida.setOnClickListener{abrirActividad(ACicloVida::class.java)}

        val botonIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonIrListView.setOnClickListener{abrirActividad(BListView::class.java)}

        val botonIrIntent = findViewById<Button>(
            R.id.btn_ir_intent
        )
        botonIrIntent.setOnClickListener{abrirActividadConParametro(CIntentExplicitoParametros::class.java)}
    }

    fun abrirActividad(clase: Class<*>){
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }

    fun abrirActividadConParametro(clase: Class<*>)
    {
        val intentExplicito = Intent(this,clase)

        intentExplicito.putExtra("nombre", "Oscar")//ese putextra solo permite trabjar ocn variables primitivas mas no con clases
        intentExplicito.putExtra("apellido", "Rivera")
        intentExplicito.putExtra("edad", 25)
        startActivity(intentExplicito)
    }






}