package com.example.deber02

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonAbrirRecyclerView = findViewById<Button>(
            R.id.btn_ir_recycler_view
        )
        botonAbrirRecyclerView.setOnClickListener { abrirActividadConParametro(GRecyclerView::class.java) }

        val botonAbrirDeber02 = findViewById<Button>(
            R.id.btn_deber02
        )
        botonAbrirDeber02.setOnClickListener { abrirActividadConParametro(GRecyclerViewFeed::class.java) }

    }

    fun abrirActividadConParametro(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "nombre",
            "Oscar"
        )//ese putextra solo permite trabjar ocn variables primitivas mas no con clases
        intentExplicito.putExtra("apellido", "Rivera")
        intentExplicito.putExtra("edad", "25")
        //intentExplicito.putExtra(
        //    "entrenador",
        //    BEntrenador("Ofcar", "Rifera")
        //)
        startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("intent-explicito", "Sí se han actualizado los datos")
                    if (data != null) {
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificado", 0)
                        val entrenador =
                            data.getParcelableExtra<BEntrenador>("entrenadorModificado")

                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")


                    }
                }
            }

            CODIGO_RESPUESTA_INTENT_IMPLICITO -> {
                if (resultCode == RESULT_OK) {
                    Log.i("intent-explicito","SI actualizo los datos")
                    if (data != null) {//si el resultado esta bien, sabemos que se selecciona un contacto. Si el intent es diferente de nulo hacemos el trabajillo
                        val uri: Uri = data.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()//nos vamos al primer registro y sacaremos el numero de telefono. Para obtener datos con el URI
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER//obtenemos el indice de contacto
                        )
                        val telefono = cursor?.getString(//obtenemos el numero de contac
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("resultado", "Telefono ${telefono}")//podemos iimprimir
                    }
                }
            }
        }
    }
}

