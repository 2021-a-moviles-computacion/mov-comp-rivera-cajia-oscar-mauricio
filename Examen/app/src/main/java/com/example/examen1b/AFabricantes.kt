package com.example.examen1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AFabricantes : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    var posicionItemSeleccionado = 0
    var arrayAdapter: ArrayAdapter<*>? = null
    var arrayList: ArrayList<String>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afabricantes)


        EBaseDeDatos.Entidades = ESqliteHelperDatabaseFMZeta(this)
        val helper = ESqliteHelperDatabaseFMZeta(this)
        //val arrayList: ArrayList<String> = helper.consultarFabricantes() as ArrayList<String>
        arrayList = helper.consultarFabricantes() as ArrayList<String>
        arrayAdapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, arrayList as List<Any?>)
        val listView: ListView = findViewById(R.id.ltv_modelos)
        //if(EBaseDeDatos.Entidades != null) {


            listView.adapter = arrayAdapter
            registerForContextMenu(listView)


        val botonCrearFabricante = findViewById<Button>(R.id.btn_abrir_crear_fabricante)
        botonCrearFabricante.setOnClickListener { abrirActividad(ACrearFabricante::class.java) }

        /*val id = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.id

        val nomFabricante = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.nomFabricante
        val tipoFabricante = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.tipoFabricante
        val sedeFabricante = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.sedeFabricante
        val fechaFabricante = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.fechaFabricante
        val fundadorFabricante = intent.getParcelableExtra<EFabricanteBDD>("idFabricante")?.fundadorFabricante

        val botonAddNumber = findViewById<Button>(R.id.btn_actualizar_ltv_fabricantes)
        botonAddNumber.setOnClickListener {
            addItemsToViewList(
                //EFabricanteBDD("$nomFabricante", "$tipoFabricante", "$sedeFabricante", "$fechaFabricante", "$fundadorFabricante"),
                EFabricanteBDD(null, "$nomFabricante", "$tipoFabricante", "$sedeFabricante", "$fechaFabricante", "$fundadorFabricante"),
                arrayList,
                arrayAdapter as ArrayAdapter<EFabricanteBDD>
            )
        }*/

    }

    fun addItemsToViewList(valor: EFabricanteBDD, arreglo: ArrayList<String>, adaptador: ArrayAdapter<EFabricanteBDD>){
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

    fun abrirActividadConParametro(clase: Class<*>, fabricanteId: EFabricanteBDD) {
        val intentExplicito = Intent(this, clase)

        //intentExplicito.putExtra(
            //"nombre",
            //"Oscar"
        //)//ese putextra solo permite trabjar ocn variables primitivas mas no con clases

        intentExplicito.putExtra(
            "idFabricante",
            fabricanteId
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
        return when(item?.itemId){
            // Editar
            R.id.mi_editar -> {
                abrirActividadConParametro(AEditarFabricante::class.java, id as EFabricanteBDD)
                return true
            }
            // Eliminar
            R.id.mi_eliminar -> {
                eliminar(posicionItemSeleccionado)
                Log.i("list-view", "List view ${posicionItemSeleccionado}")
                arrayList!!.removeAt(posicionItemSeleccionado)
                arrayAdapter!!.notifyDataSetChanged()
                return true
            }

            R.id.mis_modelos -> {
                //abrirActividad(AModelos::class.java)
                abrirActividadConParametro(AModelos::class.java, id as EFabricanteBDD)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun eliminar(id: Int) {

        val userDeleted = EBaseDeDatos.Entidades
            ?.eliminarFabricante(id)
        if(userDeleted != null){
            if (userDeleted){
                Log.i("database","Usuario eliminado")
            }else{
                Log.i("database","F")
            }
        }
    }



}