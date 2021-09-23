package com.example.firebaseuno

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebaseuno.dto.FirestoreProductoDto
import com.example.firebaseuno.dto.FirestoreRestauranteDto
import com.example.firebaseuno.dto.FirestoreUsuarioDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class EOrdenes : AppCompatActivity(){

    var arregloRestaurantes = arrayListOf<String>()

    var arrayAdapter: ArrayAdapter<*>? = null

    var productoSeleccionado: String = "perro"
    var precioProductoSeleccionado: Double = 0.0

    val productosSeleccionados = ArrayList<String>()

    var listProductosFirestore: ArrayList<String>?= null
    var listPreciosFirestore: ArrayList<Double>?=null

    var precioAcumulado: Double = 0.0

    var posicionItemSeleccionado = 0
    var producto: FirestoreProductoDto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)
        cargarRestaurantes()
        cargarProductos()

        //arrayList = FirestoreProductoDto() as ArrayList<String>

        val botonanadirProducto = findViewById<Button>(R.id.btn_anadir_lista_producto)
        botonanadirProducto.setOnClickListener{
            val cantidadProducto = findViewById<TextView>(R.id.et_cantidad_producto)
            val precioTotalProducto = findViewById<TextView>(R.id.tv_total)

            //cantidadProducto.setText("Modelos del fab. "+nombreFab)
            productosSeleccionados!!.add(productoSeleccionado + " X "+cantidadProducto.text.toString().toInt() +" unidades = $"+ precioProductoSeleccionado*cantidadProducto.text.toString().toInt())
            precioAcumulado = precioProductoSeleccionado*cantidadProducto.text.toString().toInt() + precioAcumulado
            precioTotalProducto.setText("Total $" + precioAcumulado.toString())


            arrayAdapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, productosSeleccionados as List<Any?>)
            val listView: ListView = findViewById(R.id.lv_lista_productos)
            listView.adapter = arrayAdapter
            registerForContextMenu(listView)
            arrayAdapter!!.notifyDataSetChanged()
            cantidadProducto.setText("")
        }
    }


    fun cargarRestaurantes(){
        val db = Firebase.firestore
        val referencia = db.collection("restaurante")

        referencia
            .get()
            .addOnSuccessListener {
                //Log.d("firebase-firestore", document.id + " => " + document.data)
                val list = ArrayList<String>()

                    for (document in it) {
                        var restaurante = document.toObject(FirestoreRestauranteDto::class.java)

                        list.add(restaurante.nombre)
                        Log.i("firebase-firestore", "${restaurante.nombre}")
                        //Log.i("firebase-firestore", document.id + " => " + document.data)
                    }
                val spinnerRestaurante = findViewById<Spinner>(R.id.sp_restaurantes)
                val spinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerRestaurante.adapter = spinnerArrayAdapter
                spinnerRestaurante.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        Log.i("firebase-firestore", "seleccionado")
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                        // sometimes you need nothing here
                    }
                }

            }
    }

    fun cargarProductos(){
        val db = Firebase.firestore
        val referencia = db.collection("producto")
        referencia
            .get()
            .addOnSuccessListener {
                //listaProductos
                //val list = ArrayList<String>()

                listProductosFirestore = ArrayList<String>()
                listPreciosFirestore = ArrayList<Double>()
                //listProductosSeleccionados = FirestoreProductoDto() as ArrayList<String>
                //listProductosSeleccionados = ArrayList<String>()
                //spinnerProductos


                for (document in it) {
                    producto = document.toObject(FirestoreProductoDto::class.java)

                    //producto.id = document.id
                    //listProductosFirestore!!.add(producto.nombre + " $" + producto.precio)
                    listProductosFirestore!!.add(producto.toString())
                    listPreciosFirestore!!.add(producto!!.precio)

                    Log.i("firebase-firestore", "${listProductosFirestore}")
                    //Log.i("firebase-firestore", document.id + " => " + document.data)
                }

                val spinnerProducto = findViewById<Spinner>(R.id.sp_producto)
                //adaptadorProductos
                val spinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                    listProductosFirestore!!
                )
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerProducto.adapter = spinnerArrayAdapter
                spinnerProducto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        //val id = info.position
                        //posicionItemSeleccionado = position
                        productoSeleccionado = listProductosFirestore!![position]
                        precioProductoSeleccionado = listPreciosFirestore!![position]

                        //listProductosSeleccionados = listProductosFirestore!![position]
                        //var id = arrayAdapter!!.getItem(posicionItemSeleccionado)
                        //Log.i("firebase-firestore", "seleccionado ${posicionItemSeleccionado}")
                        //Log.i("firebase-firestore", "seleccionado ${producto?.precio}")
                        Log.i("firebase-firestore", "seleccionado ${productoSeleccionado}")
                        Log.i("firebase-firestore", "seleccionado ${precioProductoSeleccionado}")
                        //listProductosSeleccionados = listProductosFirestore

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                        // sometimes you need nothing here
                    }
                }
//
            }
    }

    fun anadirProductos(){



    }




}







