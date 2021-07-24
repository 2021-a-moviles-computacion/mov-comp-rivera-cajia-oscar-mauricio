package com.example.moviles_computacion_2021_b

import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {

    var posicionItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = BBaseDatosMemoria.arregloBEntrenador
        //NNNN


        /*
        val arregloNumeros = arrayListOf<BEntrenador>(
            //ejecuto la clase
            BEntrenador("Adrian", "a@a.com"),
            BEntrenador("Vicente", "b@b.com"),
            BEntrenador("Andrea", "c@c.com"),
            BEntrenador("Carolina", "d@d.com"),
        )*/

        //creamos el adaptador
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloNumeros
        )
        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val botonAddNumber = findViewById<Button>(R.id.btn_add_num)
        botonAddNumber.setOnClickListener {
            addItemsToViewList(
                BEntrenador("Prueba", "d@d.com", null),
                arregloNumeros,
                adaptador
            )
        }

        //aber los elementos del listview
        listViewEjemplo
            .setOnItemLongClickListener { adapterView, view, posicion, id ->
                Log.i("list-view", "Has dado click en ${posicion}")

                val builder = AlertDialog.Builder(this) //constructor de alerta

                builder.setTitle("Titulo")
                //builder.setMessage("Mensaje")

                val seleccionUsuario = booleanArrayOf(
                    true,
                    false,
                    false
                )

                val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)

                builder.setMultiChoiceItems(
                    opciones,
                    seleccionUsuario,
                    { dialog, which, isChecked ->
                        Log.i("list-view", "${which} ${isChecked}")
                    }
                )

                builder.setPositiveButton(
                    "Si",
                    DialogInterface.OnClickListener { dialog, which ->
                        Log.i("list-view", "Si")
                    }
                )

                builder.setNegativeButton(
                    "No",
                    null
                )

                val dialogo = builder.create()//creamos el dialogo y lo mostramos
                dialogo.show()

                return@setOnItemLongClickListener true
            }
        //registerForContextMenu(listViewEjemplo)
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
        Log.i("list-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")
    }








    fun addItemsToViewList(valor: BEntrenador, arreglo: ArrayList<BEntrenador>, adaptador: ArrayAdapter<BEntrenador>){
        //actualiza la interfaz, sino devolvera errroes
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualizalo
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            // Editar
            R.id.mi_editar -> {
                Log.i("list-view","Editar ${BBaseDatosMemoria.arregloBEntrenador[
                        posicionItemSeleccionado
                ]}")
                return true
            }
            // Eliminar
            R.id.mi_eliminar -> {
                Log.i("list-view","Eliminar ${BBaseDatosMemoria.arregloBEntrenador[
                        posicionItemSeleccionado
                ]}")
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

}