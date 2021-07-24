package com.example.examen1b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import android.util.Log
import java.time.LocalDate
import java.util.*

class ESqliteHelperDatabaseFM(context: Context?) :
    SQLiteOpenHelper(      context,
                    "databaseFM",
                    null,
                    1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        //script para crear tabla user
        val scriptCrearTablaUsuario =
            """
                 CREATE TABLE FABRICANTES (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nomFabricante VARCHAR(50),
                tipoFabricante VARCHAR(50),
                sedeFabricante VARCHAR(50),
                fechaFabricante DATE,
                fundadorFabricante VARCHAR(50)
            )
            """.trimIndent()
        Log.i("bbd", "Creando la tabla de usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    var date: String = sdf.format(Date())

    fun crearFabricante(
        nomFabricante: String,
        tipoFabricante: String,
        sedeFabricante: String,
        fechaFabricante: String,
        fundadorFabricante: String
                                    ): Boolean {
                                        val conexionExcritura = writableDatabase//conexion de escritura
                                        val valoresAGuardar = ContentValues()

                                        valoresAGuardar.put("nombre", nomFabricante)
                                        valoresAGuardar.put("tipoFabricante", tipoFabricante)
                                        valoresAGuardar.put("sedeFabricante", sedeFabricante)
                                        valoresAGuardar.put("fechaFabricante", fechaFabricante)
                                        valoresAGuardar.put("fundadorFabricante", fundadorFabricante)

                                        val resultadoEscritura: Long = conexionExcritura
                                            .insert(
                                                "FABRICANTES",
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

    fun consultarFabricantes(): EUsuarioBDD {

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