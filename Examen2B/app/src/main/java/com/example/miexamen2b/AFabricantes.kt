package com.example.miexamen2b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.miexamen2b.dto.FirestoreFabricantesDto
import com.example.miexamen2b.dto.FirestoreModelosDto
//import com.google.firebase.firestore.FieldValue.delete
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.*


class AFabricantes : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    var posicionItemSeleccionado = 0
    var arrayAdapter: ArrayAdapter<*>? = null
    var fabricante: FirestoreFabricantesDto? = null
    var listFabricantesFirebase: ArrayList<String>? = ArrayList<String>()
    var listIDFabFirestore: ArrayList<String>? = ArrayList<String>()
    var listNomFabricantesFirestore: ArrayList<String>? = ArrayList<String>()


    var fabricanteSeleccionado: String = "perro"
    var nombreFabricanteSeleccionado: String = "perro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afabricantes)

        /**CARGAR FABRICANTES**/

        val db = Firebase.firestore
        val referencia = db.collection("fabricantes")
        referencia
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    fabricante = document.toObject(FirestoreFabricantesDto::class.java)

                    listFabricantesFirebase!!.add(fabricante.toString())
                    listNomFabricantesFirestore!!.add(fabricante!!.nomF.toString())
                    listIDFabFirestore!!.add(document.id)
                    Log.i("firebase-firestore", "${fabricante}")
                    //Log.i("firebase-firestore", document.id + " => " + document.data)
                }


                arrayAdapter = ArrayAdapter<Any?>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listFabricantesFirebase as List<Any?>
                )
                val listView: ListView = findViewById(R.id.ltv_modelos)
                listView.adapter = arrayAdapter
                registerForContextMenu(listView)
                arrayAdapter!!.notifyDataSetChanged()


            }

        /**CREAR FABRICANTES**/

        val botonCrearFabricante = findViewById<Button>(R.id.btn_abrir_crear_fabricante)
        botonCrearFabricante.setOnClickListener { abrirActividad(ACrearFabricante::class.java) }

    }


    fun addItemsToViewList(
        valor: EFabricanteBDD,
        arreglo: ArrayList<String>,
        adaptador: ArrayAdapter<EFabricanteBDD>
    ) {
        //actualiza la interfaz, sino devolvera errroes
        arreglo.add(valor.toString())
        adaptador.notifyDataSetChanged()//actualizalo
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

    fun abrirActividadConParametro2(clase: Class<*>, fabricanteSeleccionado: String, nombreFabricanteSeleccionado: String) {
        val intentExplicito = Intent(this, clase)
        val intentExplicito2 = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", fabricanteSeleccionado
        )
        intentExplicito.putExtra(
            "nombreFabricante", nombreFabricanteSeleccionado
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
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("list-view", "List view ${posicionItemSeleccionado}")
        //Log.i("list-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var id = arrayAdapter!!.getItem(posicionItemSeleccionado)

        return when (item?.itemId) {
            // Editar
            R.id.mi_editar -> {
                fabricanteSeleccionado = listIDFabFirestore!![posicionItemSeleccionado]
                abrirActividadConParametro(AEditarFabricante::class.java, fabricanteSeleccionado)
                return true
            }
            // Eliminar
            R.id.mi_eliminar -> {
                /**ELIMINAR PADRES**/
                fabricanteSeleccionado = listIDFabFirestore!![posicionItemSeleccionado]
                val db = Firebase.firestore
                db.collection("fabricantes").document(fabricanteSeleccionado)
                    .delete()
                Log.i("list-view", "List view ${posicionItemSeleccionado}")
                Log.i("list-view", "List view ${fabricanteSeleccionado}")

                /**ELIMINAR HIJOS**/
                val referencia = db.collection("modelos")

                val queryDelete: Query = referencia
                    .whereEqualTo("idF", fabricanteSeleccionado)

                queryDelete.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot?> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            referencia.document(document.id).delete()
                        }
                    } else {
                        Log.d("Error getting documents: ", task.exception.toString())
                    }
                })

                listFabricantesFirebase!!.removeAt(posicionItemSeleccionado)
                arrayAdapter!!.notifyDataSetChanged()
                return true
            }

            R.id.mis_modelos -> {
                //abrirActividad(AModelos::class.java)

                nombreFabricanteSeleccionado = listNomFabricantesFirestore!![posicionItemSeleccionado]
                fabricanteSeleccionado = listIDFabFirestore!![posicionItemSeleccionado]
                abrirActividadConParametro2(AModelos::class.java, fabricanteSeleccionado, nombreFabricanteSeleccionado)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


}