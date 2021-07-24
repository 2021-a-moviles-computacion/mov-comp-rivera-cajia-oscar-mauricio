package com.example.moviles_computacion_2021_b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
    context: Context?,
) : SQLiteOpenHelper(
    context,
    "nameMoviles",
    null,
    1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        //script para crear tabla user
        val scriptCrearTablaUsuario =
            """
                 CREATE TABLE USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion varchar(50)
            )
            """.trimIndent()
        Log.i("bbd", "Creando la tabla de usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }



    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ): Boolean {
//        val conexionExcritura = this.writableDatabase
        val conexionExcritura = writableDatabase//conexion de escritura
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoEscritura: Long = conexionExcritura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true// ==1 no se creo
    }

    fun consultarUsuarioPorId(id: Int): EUsuarioBDD {

        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"
//        val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase

        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )

        //vemos si existe el user
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        // val arregloUsuario = arrayListOf<EUsuarioBDD>()//para obtener varios registros
        val usuarioEncontrado = EUsuarioBDD(0, "", "")

        //leemos las cosas del user
        if (existeUsuario) {
            do {
                val id = resultaConsultaLectura.getInt(0) // Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
                val descripcion =
                    resultaConsultaLectura.getString(2) // Columna indice 2 -> DESCRIPCION
                if(id!=null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    // arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun eliminarUsuarioFormulario(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "USUARIO",
                "Id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarUsuario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "USUARIO",
                valoresActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }



}