package com.example.moviles_computacion_2021_b

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)

        if (EBaseDeDatos.TablaUsuario != null) {
            //EBaseDeDatos.TablaUsuario.consultarUsuarioPorId()
            //EBaseDeDatos.TablaUsuario.crearUsuarioFormulario()
            //EBaseDeDatos.TablaUsuario.eliminarUsuarioFormulario()
            //EBaseDeDatos.TablaUsuario.actualizarUsuario()
        }

        //consultar(4)
        //crear("Roberto", "Profesional")
        //actualizar(4, "NuevoNombre", "descripcionNueva")
        //eliminar(4)


        val botonIrACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonIrACicloVida.setOnClickListener { abrirActividad(ACicloVida::class.java) }


        val botonDatabase = findViewById<Button>(
            R.id.btn_ir_DataBase
        )
        botonDatabase.setOnClickListener { abrirActividad(BaseApp::class.java) }


        val botonIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonIrListView.setOnClickListener { abrirActividad(BListView::class.java) }

        val botonIrIntent = findViewById<Button>(
            R.id.btn_ir_intent
        )
        //botonIrIntent.setOnClickListener { abrirActividadConParametro(CIntentExplicitoParametros::class.java) }

        val botonAbrirBotonImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )

        botonAbrirBotonImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,//LLAMO LA ACCION
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI //PEDIMOS EL URI DE UN CONTACTO
            )
            startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
        }

        val botonAbrirRecyclerView = findViewById<Button>(
            R.id.btn_ir_recycler_view
        )
        botonAbrirRecyclerView.setOnClickListener { abrirActividadConParametro(GRecyclerView::class.java) }

        val botonAbrirHHttp = findViewById<Button>(
            R.id.btn_ir_http
        )
        botonAbrirHHttp.setOnClickListener {
            abrirActividadConParametro(HHttpActivity::class.java)
        }


    }

    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }

    fun abrirDataExer(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
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
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        /*
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    //ejecutar codigo ok
                    it.data?.getStringExtra("nombreModificado")
                    it.data?.getIntExtra("edadModificado", 0)
                    it.data?.getParcelableExtra<BEntrenador>("entrenadorModificado")
                }
            }
        }*/
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








