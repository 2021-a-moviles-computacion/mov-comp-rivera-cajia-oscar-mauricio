package com.example.proyecto2b

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.proyecto2b.dto.FirestoreRestaurantesDto
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ATiendaDetalle : AppCompatActivity() {

    var nombreTienda: String? = null
    var ubicacionTienda: String? = null
    var horarioTienda: String? = null
    var tipoTienda: String? = null
    var telefonoTienda: Int? = null
    var descripcionTienda: String? = null
    var tiendaCargada: FirestoreTiendasDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atiendadetalle)

        val botonDatos = findViewById<Button>(R.id.btn_volver)
        botonDatos.setOnClickListener{
            abrirActividad(GRecyclerViewTiendas::class.java)
        }

        val botonDatosPrueba = findViewById<Button>(R.id.btn_anadirFavorito)
        botonDatosPrueba.setOnClickListener{
            anadirFavorito()
        }

        val botonDatosPrueba2 = findViewById<Button>(R.id.btn_quitarFavorito)
        botonDatosPrueba2.setOnClickListener{
            quitarFavorito()
        }

        var referenci = Firebase.storage
        var nombreImg = referenci.reference.child("restaurantes/fridays.jpg")
        nombreImg.getBytes(10024*10024)
            .addOnSuccessListener {
                val bit: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                Log.i("Firebase-Imagen", "Imagen recuperada->  ${dataDir}" )
                Log.i("Firebase-Imagen", "Imagen recuperada->  ${bit}" )
                var imagen =findViewById<ImageView>(R.id.imageView)
                    .setImageBitmap(bit)
            }


        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        /**CARGAR DATOS DE LA TIENDA SELECCIONADA**/

        val db = Firebase.firestore
        val referencia = db.collection("tiendas").document(idDocumentRestaurante.toString())
        referencia
            .get()
            .addOnSuccessListener {
                tiendaCargada = it.toObject(FirestoreTiendasDto::class.java)

                nombreTienda = tiendaCargada?.nameT
                val tvNombreRestaurante = findViewById<TextView>(R.id.tv_nameT)
                tvNombreRestaurante.text = nombreTienda

                ubicacionTienda = tiendaCargada?.locationT
                val tvUbicacionRestaurante = findViewById<TextView>(R.id.tv_locationT)
                tvUbicacionRestaurante.text = "Ubicación: "+ubicacionTienda

                horarioTienda = tiendaCargada?.hoursT
                val tvHorarioRestaurante = findViewById<TextView>(R.id.tv_hours)
                tvHorarioRestaurante.text = "Horario: "+horarioTienda

                tipoTienda = tiendaCargada?.typeT
                val tvTipoRestaurante = findViewById<TextView>(R.id.tv_typeT)
                tvTipoRestaurante.text = "Tipo de tienda: "+tipoTienda

                telefonoTienda = tiendaCargada?.phoneT
                val tvTelefonoRestaurante = findViewById<TextView>(R.id.tv_phoneT)
                tvTelefonoRestaurante.text = "Teléfono: "+telefonoTienda.toString()

                descripcionTienda = tiendaCargada?.descriptionT
                val tvDescripcionRestaurante = findViewById<TextView>(R.id.tv_descriptionT)
                tvDescripcionRestaurante.text = "Descripción: "+descripcionTienda

                if (tiendaCargada!!.favorites.contains(BAuthUsuario.usuario!!.email)){
                    val btnAnadirFavroito = findViewById<Button>(R.id.btn_anadirFavorito)
                    btnAnadirFavroito.visibility = View.INVISIBLE
                    val btnQuitarFavroito = findViewById<Button>(R.id.btn_quitarFavorito)
                    btnQuitarFavroito.visibility = View.VISIBLE

                }else{
                    val btnAnadirFavroito = findViewById<Button>(R.id.btn_anadirFavorito)
                    btnAnadirFavroito.visibility = View.VISIBLE
                    val btnQuitarFavroito = findViewById<Button>(R.id.btn_quitarFavorito)
                    btnQuitarFavroito.visibility = View.INVISIBLE
                }


                var referenci = Firebase.storage
                var nombreImg = referenci.reference.child("tiendas/"+tiendaCargada!!.nameT.toString()+".jpg")
                Log.i("Firebase-Imagen", "tiendas/"+tiendaCargada!!.nameT.toString()+".jpg" )
                nombreImg.getBytes(10024*10024)
                    .addOnSuccessListener {
                        val bit: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                        //Log.i("Firebase-Imagen", "Imagen recuperada->  ${dataDir}" )
                        Log.i("Firebase-Imagen", "Imagen recuperada->  ${bit}" )
                        var imagen =findViewById<ImageView>(R.id.img_tiendaDetalle).setImageBitmap(bit)
                        //holder.logoRestaurante.setImageBitmap(bit)
                    }
            }

    }

    fun crearDatosTiendas(){

        val db = Firebase.firestore
        val refTiendas = db.collection("tiendas")

        val favorites: ArrayList<String> = arrayListOf("empty")

        val tienda1 = hashMapOf(
            "nameT" to "Sombrero Legendario Co.",
            "locationT" to "USA",
            "typeT" to "Ropa",
            "hoursT" to "24H",
            "phoneT" to 439867,
            "descriptionT" to "Sombreros, bolsos de cuero y más artículos creados a mano con mucha pasión en cada detalle."
        ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda1)

        val tienda2 = hashMapOf(
            "nameT" to "Plaza Ruana",
            "locationT" to "USA",
            "typeT" to "Souvenirs",
            "hoursT" to "24H",
            "phoneT" to 353463,
            "descriptionT" to "Tienda de souvenirs con el espíritu de plaza o mercado artesanal; ofrece productos como recuerdos o regalos muy nacionales."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda2)

        val tienda3 = hashMapOf(
            "nameT" to "Euphoria",
            "locationT" to "USA",
            "typeT" to "Ropa",
            "hoursT" to "24H",
            "phoneT" to 253874,
            "descriptionT" to "En Euphoria encontrarás todo lo que necesitas para verte y sentirte increíble. Productos de alta calidad, innovación y una nueva experiencia de compra."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda3)

        val tienda4 = hashMapOf(
            "nameT" to "Flor de Liz",
            "locationT" to "USA",
            "typeT" to "Flores",
            "hoursT" to "24H",
            "phoneT" to 436765,
            "descriptionT" to "Los mejores arreglos ya sea para despedir o recibir a un ser querido en el aeropuerto."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda4)

        val tienda5 = hashMapOf(
            "nameT" to "Tikuna",
            "locationT" to "USA",
            "typeT" to "Textil",
            "hoursT" to "24H",
            "phoneT" to 547833,
            "descriptionT" to "Textiles y artesanías que reflejan la riqueza cultural del Ecuador como símbolo de tradición, belleza y calidad."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda5)

        val tienda6 = hashMapOf(
            "nameT" to "Uomo Venetto",
            "locationT" to "USA",
            "typeT" to "Textil",
            "hoursT" to "24H",
            "phoneT" to 476385,
            "descriptionT" to "La mejor confección de trajes exquisitos en diseño, frescos y livianos para él."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda6)

        val tienda7 = hashMapOf(
            "nameT" to "QURI",
            "locationT" to "USA",
            "typeT" to "Souvenir",
            "hoursT" to "24H",
            "phoneT" to 466665,
            "descriptionT" to "Encuentre varios productos de línea gourmet, souvenir, snacks y de entretenimiento."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(tienda7)




    }


    fun anadirFavorito(){

        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        var arrayList: ArrayList<String> = arrayListOf<String>(tiendaCargada!!.favorites.add(BAuthUsuario.usuario!!.email).toString())

        Log.i("firestore-firestore", tiendaCargada!!.favorites.toString())
        Log.i("firestore-firestore", "Wo + $arrayList")
        Log.i("firestore-firestore", "Wo2" + tiendaCargada!!.favorites)


        val db = Firebase.firestore
        val referenciaTienda = db.collection("tiendas").document(idDocumentRestaurante.toString())
        referenciaTienda.update("favorites", tiendaCargada!!.favorites)

        val btnAnadirFavroito = findViewById<Button>(R.id.btn_anadirFavorito)
        btnAnadirFavroito.visibility = View.INVISIBLE

        val btnQuitarFavroito = findViewById<Button>(R.id.btn_quitarFavorito)
        btnQuitarFavroito.visibility = View.VISIBLE


    }

    fun quitarFavorito(){

        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        var arrayList: ArrayList<String> = arrayListOf<String>(tiendaCargada!!.favorites.remove(BAuthUsuario.usuario!!.email).toString())

        Log.i("firestore-firestore", tiendaCargada!!.favorites.toString())
        Log.i("firestore-firestore", "Wo + $arrayList")
        Log.i("firestore-firestore", "Wo2" + tiendaCargada!!.favorites)


        val db = Firebase.firestore
        val referenciaTienda = db.collection("tiendas").document(idDocumentRestaurante.toString())
        referenciaTienda.update("favorites", tiendaCargada!!.favorites)

        val btnQuitarFavroito = findViewById<Button>(R.id.btn_quitarFavorito)
        btnQuitarFavroito.visibility = View.INVISIBLE

        val btnAnadirFavroito = findViewById<Button>(R.id.btn_anadirFavorito)
        btnAnadirFavroito.visibility = View.VISIBLE

    }

    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }
}