package com.example.miexamen2b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.miexamen2b.dto.FirestoreModelosDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AModelos : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    var modelo: FirestoreModelosDto? = null
    var listModelosFirebase: ArrayList<String>? = ArrayList<String>()
    var listIDModFirestore: ArrayList<String>? = ArrayList<String>()
    var modeloSeleccionado: String = "perro"

    var listLatitudFirestore: ArrayList<Double>? = ArrayList<Double>()
    var listLongitudFirestore: ArrayList<Double>? = ArrayList<Double>()
    var latitudSeleccionada: Double = 0.0
    var longitudSeleccionada: Double = 0.0


    var posicionItemSeleccionado = 0
    var arrayAdapter: ArrayAdapter<*>? = null
    var arrayList: ArrayList<String>?= null
    var todof: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amodelos)

        val intent = intent
        val idDocument = intent.getStringExtra("idFabricante")
        val nombreFab = intent.getStringExtra("nombreFabricante")

        val msj = findViewById<TextView>(R.id.textNombreFab)
        msj.setText("Modelos del fab. "+nombreFab)



        /**CARGAR MODELOS**/

        val db = Firebase.firestore
        val referencia = db.collection("modelos")
        referencia
            .whereEqualTo("idF", idDocument.toString())
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    modelo = document.toObject(FirestoreModelosDto::class.java)

                    listModelosFirebase!!.add(modelo.toString())

                    listLatitudFirestore!!.add(modelo!!.latM.toString().toDouble())
                    listLongitudFirestore!!.add(modelo!!.longM.toString().toDouble())
                    listIDModFirestore!!.add(document.id)

                    Log.i("firebase-firestore", "${modelo}")
                    //Log.i("firebase-firestore", document.id + " => " + document.data)
                }


                arrayAdapter = ArrayAdapter<Any?>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listModelosFirebase as List<Any?>
                )
                val listView: ListView = findViewById(R.id.ltv_modelos)
                listView.adapter = arrayAdapter
                registerForContextMenu(listView)
                arrayAdapter!!.notifyDataSetChanged()


            }

        /**CREAR MODELOS**/


        val botonCrearModelo = findViewById<Button>(R.id.btn_abrir_crear_modelo)
        botonCrearModelo.setOnClickListener {
            if (idDocument != null) {
                abrirActividadConParametro(ACrearModelo::class.java, idDocument)
            }
        }
        val botonVolverFabricantes = findViewById<Button>(R.id.btn_volver_a_fabricantes)
        botonVolverFabricantes.setOnClickListener {abrirActividad(AFabricantes::class.java)}

    }

    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }

    fun abrirActividadConParametro(clase: Class<*>, fabricanteSeleccionado: String) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActividadConParametro2(clase: Class<*>, fabricanteSeleccionado: String, modeloSeleccionado: String) {
        val intentExplicito = Intent(this, clase)
        val intentExplicito2 = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        intentExplicito.putExtra(
            "idModelo", modeloSeleccionado
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActividadConParametro3(clase: Class<*>, latitudSeleccionada: Double, longitudSeleccionada: Double) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "latitudModelo", latitudSeleccionada
        )
        intentExplicito.putExtra(
            "longitudModelo", longitudSeleccionada
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("list-view", "List view ${posicionItemSeleccionado}")
        //Log.i("list-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var id = arrayAdapter!!.getItem(posicionItemSeleccionado)
        return when(item?.itemId){
            // Mapa
            R.id.mi_mapa -> {
                latitudSeleccionada = listLatitudFirestore!![posicionItemSeleccionado]
                longitudSeleccionada = listLongitudFirestore!![posicionItemSeleccionado]
                abrirActividadConParametro3(FMapaModelo::class.java, latitudSeleccionada, longitudSeleccionada)
                return true
            }

            // Editar
            R.id.mi_editar -> {
                val intent = intent
                val idDocument = intent.getStringExtra("idFabricante")
                modeloSeleccionado = listIDModFirestore!![posicionItemSeleccionado]
                if (idDocument != null) {
                    abrirActividadConParametro2(AEditarModelo::class.java, idDocument, modeloSeleccionado)
                }
                return true
            }
            // Eliminar
            R.id.mi_eliminar -> {
                modeloSeleccionado = listIDModFirestore!![posicionItemSeleccionado]
                val db = Firebase.firestore
                db.collection("modelos").document(modeloSeleccionado)
                    .delete()
                Log.i("list-view", "List view ${posicionItemSeleccionado}")
                Log.i("list-view", "List view ${modeloSeleccionado}")
                listModelosFirebase!!.removeAt(posicionItemSeleccionado)
                arrayAdapter!!.notifyDataSetChanged()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


}