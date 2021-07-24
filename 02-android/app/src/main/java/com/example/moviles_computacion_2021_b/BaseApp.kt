package com.example.moviles_computacion_2021_b

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BaseApp : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)

        val txtNombre = findViewById<EditText>(R.id.txt_nombre)
        val txtDescripcion = findViewById<EditText>(R.id.txt_descripcion)
        val txtId = findViewById<EditText>(R.id.txt_id)

        val botonCrearUser = findViewById<Button>(R.id.btn_crearUser)
        botonCrearUser.setOnClickListener { crear(txtNombre.getText().toString(), txtDescripcion.getText().toString()) }

        val botonConsultarUser = findViewById<Button>(R.id.btn_consultarUser)
        botonConsultarUser.setOnClickListener { consultar(txtId.text.toString().toInt()) }

        val botonActualizarUser = findViewById<Button>(R.id.btn_actualizaUser)
        botonActualizarUser.setOnClickListener { actualizar(txtNombre.getText().toString(), txtDescripcion.getText().toString(), txtId.text.toString().toInt()) }

        val botonEliminarUser = findViewById<Button>(R.id.btn_eliminarUser)
        botonEliminarUser.setOnClickListener { eliminar(txtId.text.toString().toInt()) }
    }

        fun consultar(id: Int) {
            if (EBaseDeDatos.TablaUsuario != null) {

                val userQuem = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(id)
                Log.i(
                    "database", "Id:${userQuem?.id} Nombre:${userQuem?.nombre} " +
                            "Descripcion:${userQuem?.descripcion}"
                )
            }
        }

        fun crear(nombre: String, descripcion: String) {

            val userCreated = EBaseDeDatos.TablaUsuario
                ?.crearUsuarioFormulario(nombre,descripcion)
            if(userCreated != null){
                if(userCreated){
                    Log.i("database","Usuario creado")
                }else{
                    Log.i("database","F")
                }
            }
        }

        fun actualizar(nombreActualizado: String, descripcionActualizada: String, id: Int) {

            val userUpdated = EBaseDeDatos.TablaUsuario
                ?.actualizarUsuario(nombreActualizado, descripcionActualizada, id)
            if(userUpdated != null){
                if (userUpdated){
                    Log.i("database","Usuario actualizado")
                }else{
                    Log.i("database","F")
                }
            }
        }

        fun eliminar(id: Int) {

            val userDeleted = EBaseDeDatos.TablaUsuario
                ?.eliminarUsuarioFormulario(id)
            if(userDeleted != null){
                if (userDeleted){
                    Log.i("database","Usuario eliminado")
                }else{
                    Log.i("database","F")
                }
            }
        }



}