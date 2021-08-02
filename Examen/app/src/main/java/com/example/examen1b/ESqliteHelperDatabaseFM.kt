/*package com.example.examen1b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class ESqliteHelperDatabaseFM(context: Context?) :
    SQLiteOpenHelper(      context,
                    "databaseFM",
                    null,
                    1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        //script para crear tabla user
        val scriptCrearEntidadFabricante =
            """
                 CREATE TABLE FABRICANTES (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nomFabricante VARCHAR(50),
                tipoFabricante VARCHAR(50),
                sedeFabricante VARCHAR(50),
                fechaFabricante DATE,
                fundadorFabricante VARCHAR(50)
            );
            """.trimIndent()
        Log.i("bbd", "Creando la tabla de fabricantes")
        db?.execSQL(scriptCrearEntidadFabricante)

        /*
        val scriptCrearEntidadModelos =
            """
                 CREATE TABLE MODELOS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_fk INTEGER,
                nombreModelo VARCHAR(50),
                precioModelo DECIMAL,
                nPuertasModelo INTEGER,
                puntExpModelo DECIMAL,
                serieModelo VARCHAR(50),
                FOREIGN KEY (id_fk) REFERENCES FABRICANTES(id));
            )
            """.trimIndent()
        Log.i("bbd", "Creando la tabla de modelos")
        db?.execSQL(scriptCrearEntidadModelos)*/

        val scriptCrearEntidadModelazos =
            """
                 CREATE TABLE MODELAZOS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                puntExpModelo DECIMAL,
                id_fk INTEGER,
                nombreModelo VARCHAR(50),
                serieModelo VARCHAR(50),
                precioModelo DECIMAL,
                nPuertasModelo INTEGER,
                FOREIGN KEY(id_fk) REFERENCES FABRICANTES(id)
            );
            """.trimIndent()
        Log.i("bbd", "Creando la tabla de models")
        db?.execSQL(scriptCrearEntidadModelazos)

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

                                        valoresAGuardar.put("nomFabricante", nomFabricante)
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

    fun consultarFabricantePorId(id: Int): EFabricanteBDD {

        val scriptConsultarUsuario = "SELECT * FROM FABRICANTES WHERE ID = ${id}"
//        val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase

        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )

        //vemos si existe el user
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        // val arregloUsuario = arrayListOf<EUsuarioBDD>()//para obtener varios registros
        val usuarioEncontrado = EFabricanteBDD(0, "", "","","", "")

        //leemos las cosas del user
        if (existeUsuario) {
            do {
                val id = resultaConsultaLectura.getInt(0) // Columna indice 0 -> ID
                val nomFabricante = resultaConsultaLectura.getString(1) // Columna indice 1 -> TIPO FAB
                val tipoFabricante = resultaConsultaLectura.getString(2) // Columna indice 1 -> TIPO FAB
                val sedeFabricante = resultaConsultaLectura.getString(3) // Columna indice 2 -> SEDE FAB
                val fechaFabricante = resultaConsultaLectura.getString(4) // Columna indice 3 -> FECHA FAB
                val fundadorFabricante = resultaConsultaLectura.getString(5) // Columna indice 4 -> FUND FAB
                if(id!=null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nomFabricante = nomFabricante
                    usuarioEncontrado.tipoFabricante = tipoFabricante
                    usuarioEncontrado.sedeFabricante = sedeFabricante
                    usuarioEncontrado.fechaFabricante = sedeFabricante
                    usuarioEncontrado.fundadorFabricante = sedeFabricante
                    // arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun consultarFabricantes(): ArrayList<EFabricanteBDD> {

        val scriptConsultarUsuario = "SELECT * FROM FABRICANTES"
//        val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase

        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )

        //vemos si existe el user
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloUsuario = arrayListOf<EFabricanteBDD>()//para obtener varios registros


        //leemos las cosas del user
        if (existeUsuario) {
            do {
                val id = resultaConsultaLectura.getInt(0) // Columna indice 0 -> ID
                val usuarioEncontrado = EFabricanteBDD(id, resultaConsultaLectura.getString(1), resultaConsultaLectura.getString(2), resultaConsultaLectura.getString(3), resultaConsultaLectura.getString(4), resultaConsultaLectura.getString(5))
                if(id!=null){
                    arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloUsuario
        }




    fun eliminarFabricante(id: Any?): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FABRICANTES",
                "Id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarFabricante(
        nomFabricante: String,
        tipoFabricante: String,
        sedeFabricante: String,
        fechaFabricante: String,
        fundadorFabricante: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()

        valoresAActualizar.put("nomFabricante", nomFabricante)
        valoresAActualizar.put("tipoFabricante", tipoFabricante)
        valoresAActualizar.put("sedeFabricante", sedeFabricante)
        valoresAActualizar.put("fechaFabricante", fechaFabricante)
        valoresAActualizar.put("fundadorFabricante", fundadorFabricante)

        val resultadoActualizacion = conexionEscritura
            .update(
                "FABRICANTES",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun crearModelo(
        nombreModelo: String,
        precioModelo: Double,
        nPuertasModelo: Int,
        puntExpModelo: Double,
        serieModelo: String,
        id_fk: Int,
    ): Boolean {
        val conexionExcritura = writableDatabase//conexion de escritura
        val valoresAGuardar = ContentValues()


        valoresAGuardar.put("id_fk", id_fk)
        valoresAGuardar.put("nombreModelo", nombreModelo)
        valoresAGuardar.put("precioModelo", precioModelo)
        valoresAGuardar.put("nPuertasModelo", nPuertasModelo)
        valoresAGuardar.put("puntExpModelo", puntExpModelo)
        valoresAGuardar.put("serieModelo", serieModelo)


        val resultadoEscritura: Long = conexionExcritura
            .insert(
                "MODELAZOS",
                null,
                valoresAGuardar
            )
        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true// ==1 no se creo
    }

    fun consultarModelos(id_fk: Int?): ArrayList<EModelosBDD> {

        //val scriptConsultarModelos = "SELECT * FROM MODELAZOS WHERE id_fk = ${id_fk}"
        val scriptConsultarModelos = "SELECT * FROM MODELAZOS"
//        val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase

        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarModelos,
            null
        )

        //vemos si existe el user
        val existeModelo = resultaConsultaLectura.moveToFirst()
        val arregloModelo = arrayListOf<EModelosBDD>()//para obtener varios registros


        //leemos las cosas del user
        if (existeModelo) {
            do {
                val id = resultaConsultaLectura.getInt(0) // Columna indice 0 -> ID
                val usuarioEncontrado = EModelosBDD(id, id_fk, resultaConsultaLectura.getString(1), resultaConsultaLectura.getDouble(2), resultaConsultaLectura.getInt(3), resultaConsultaLectura.getDouble(4), resultaConsultaLectura.getString(5))
                if(id!=null){
                    arregloModelo.add(usuarioEncontrado)
                }
            } while (resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return arregloModelo
    }

    fun actualizarModelo(
        nombreModelo: String,
        precioModelo: Double,
        nPuertasModelo: Int,
        puntExpModelo: Double,
        serieModelo: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()

        valoresAActualizar.put("nombreModelo", nombreModelo)
        valoresAActualizar.put("precioModelo", precioModelo)
        valoresAActualizar.put("nPuertasModelo", nPuertasModelo)
        valoresAActualizar.put("puntExpModelo", puntExpModelo)
        valoresAActualizar.put("serieModelo", serieModelo)

        val resultadoActualizacion = conexionEscritura
            .update(
                "MODELOS",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun eliminarModelo(id: Any?): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "MODELOS",
                "Id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }




    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }



}*/