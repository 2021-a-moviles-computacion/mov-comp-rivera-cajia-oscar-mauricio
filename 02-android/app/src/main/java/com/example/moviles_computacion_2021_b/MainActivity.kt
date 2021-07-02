package com.example.moviles_computacion_2021_b

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

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
        intentExplicito.putExtra(
            "entrenador",
            BEntrenador("Ofcar", "Rifera")
                                )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)

        /*registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                Activity.RESULT_OK-> {
                    //ejecutar codigo ok
                    it.data?.getStringExtra("nombreModificado")
                    it.data?.getIntExtra("edadModificado", 0)
                    it.data?.getParcelableExtra<BEntrenador>("entrenadorModificado")
                }
            }*/
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                if(resultCode == Activity.RESULT_OK){
                    Log.i("intent-explicito", "Sí se han actualizado los datos")
                    if(data!= null){
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificado", 0)
                        val entrenador = data.getParcelableExtra<BEntrenador>("entrenadorModificado")

                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")


                    }
                }
            }
        }
    }


    }


