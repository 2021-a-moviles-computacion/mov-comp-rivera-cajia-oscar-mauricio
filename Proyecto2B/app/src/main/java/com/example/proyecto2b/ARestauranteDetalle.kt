package com.example.proyecto2b

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.proyecto2b.dto.FirestoreRestaurantesDto
import com.example.proyecto2b.dto.FirestoreTiendasDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ARestauranteDetalle: AppCompatActivity() {


    var nombreRestaurante: String? = null
    var ubicacionRestaurante: String? = null
    var horarioREstaurante: String? = null
    var tipoRestaurante: String? = null
    var telefonoRestaurante: Int? = null
    var descripcionRestaurante: String? = null
    var restauranteCargado: FirestoreRestaurantesDto? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arestaurantedetalle)

        val botonDatosPrueba = findViewById<Button>(R.id.btn_anadirFavorito)
        botonDatosPrueba.setOnClickListener{
            anadirFavorito()
        }

        val botonDatos = findViewById<Button>(R.id.btn_volver)
        botonDatos.setOnClickListener{
            abrirActividad(GRecyclerViewRestaurants::class.java)
        }

        val botonDatosPrueba2 = findViewById<Button>(R.id.btn_quitarFavorito)
        botonDatosPrueba2.setOnClickListener{
            quitarFavorito()
        }

        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        /**CARGAR DATOS DEL RESTAURANTE SELECCIONADO**/

        val db = Firebase.firestore
        val referencia = db.collection("restaurantes").document(idDocumentRestaurante.toString())
        referencia
            .get()
            .addOnSuccessListener {
                restauranteCargado =
                    it.toObject(FirestoreRestaurantesDto::class.java)

                nombreRestaurante = restauranteCargado?.nameR
                val tvNombreRestaurante = findViewById<TextView>(R.id.tv_nameR)
                tvNombreRestaurante.text = nombreRestaurante

                ubicacionRestaurante = restauranteCargado?.locationR
                val tvUbicacionRestaurante = findViewById<TextView>(R.id.tv_locationR)
                tvUbicacionRestaurante.text = "Ubicación: "+ubicacionRestaurante

                horarioREstaurante = restauranteCargado?.hoursR
                val tvHorarioRestaurante = findViewById<TextView>(R.id.tv_hours)
                tvHorarioRestaurante.text = "Horario: "+horarioREstaurante

                tipoRestaurante = restauranteCargado?.typeR
                val tvTipoRestaurante = findViewById<TextView>(R.id.tv_typeR)
                tvTipoRestaurante.text = "Tipo de restaurante: "+tipoRestaurante

                telefonoRestaurante = restauranteCargado?.phoneR
                val tvTelefonoRestaurante = findViewById<TextView>(R.id.tv_phoneR)
                tvTelefonoRestaurante.text = "Teléfono: "+telefonoRestaurante.toString()

                descripcionRestaurante = restauranteCargado?.descriptionR
                val tvDescripcionRestaurante = findViewById<TextView>(R.id.tv_descriptionR)
                tvDescripcionRestaurante.text = "Descripción: "+descripcionRestaurante

                if (restauranteCargado!!.favorites.contains(BAuthUsuario.usuario!!.email)){
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
                var nombreImg = referenci.reference.child("restaurantes/"+restauranteCargado!!.nameR.toString()+".jpg")
                Log.i("Firebase-Imagen", "restaurantes/"+restauranteCargado!!.nameR.toString()+".jpg" )
                nombreImg.getBytes(10024*10024)
                    .addOnSuccessListener {
                        val bit: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                        //Log.i("Firebase-Imagen", "Imagen recuperada->  ${dataDir}" )
                        Log.i("Firebase-Imagen", "Imagen recuperada->  ${bit}" )
                        var imagen =findViewById<ImageView>(R.id.img_restauranteDetalle).setImageBitmap(bit)
                        //holder.logoRestaurante.setImageBitmap(bit)
                    }

            }



    }


    fun crearDatosRestaurantes(){

        val db = Firebase.firestore
        val refTiendas = db.collection("restaurantes")
        val favorites: ArrayList<String> = arrayListOf("empty")
        val restaurante1 = hashMapOf(
            "nameR" to "Atteza Duty Free",
            "locationR" to "USA",
            "typeR" to "Comida rápida",
            "hoursR" to "24H",
            "phoneR" to 2818407,
            "descriptionR" to "Las mejores hamburguesas internacionales."
            ,"favorites" to favorites
        )
        //refTiendas.document("SF").set(restaurante1)
        refTiendas
            .add(restaurante1)

        val restaurante2 = hashMapOf(
            "nameR" to "Los Cañaverales",
            "locationR" to "USA",
            "typeR" to "Gastronomía autoctona",
            "hoursR" to "24H",
            "phoneR" to 8753437,
            "descriptionR" to "La mejor gastronomía con calidad precio totalmente equilibrado que podrá encontrar."
            ,"favorites" to favorites
        )
        refTiendas
            .add(restaurante2)

        val restaurante3 = hashMapOf(
            "nameR" to "Los Cántaros",
            "locationR" to "USA",
            "typeR" to "Mariscos",
            "hoursR" to "24H",
            "phoneR" to 875085,
            "descriptionR" to "Los mejores mariscos al mejor precio para tener un vuelo agradable."
            ,"favorites" to favorites
        )
        refTiendas
            .add(restaurante3)

        val restaurante4 = hashMapOf(
            "nameR" to "JOHNNY ROCKET'S",
            "locationR" to "USA",
            "typeR" to "Mariscos",
            "hoursR" to "24H",
            "phoneR" to 875085,
            "descriptionR" to "Restaurante americano que transporta a los clientes, mediante una decoración retro, a la época de los años cincuenta."
            ,"favorites" to favorites
        )
        refTiendas
            .add(restaurante4)

        val restaurante5 = hashMapOf(
            "nameR" to "Guacamole",
            "locationR" to "USA",
            "typeR" to "Comida rápida",
            "hoursR" to "24H",
            "phoneR" to 986578,
            "descriptionR" to "Rincón donde la experiencia gastronómica Tex-Mex se fusiona con platos típicos de Ecuador."
            ,"favorites" to favorites
        )
        refTiendas
            .add(restaurante5)

        val restaurante6 = hashMapOf(
            "nameR" to "Petit Gourmet",
            "locationR" to "USA",
            "typeR" to "Repostería",
            "hoursR" to "24H",
            "phoneR" to 986578,
            "descriptionR" to "Los mejores postres inspirados en la alta repostería francesa."
            ,"favorites" to favorites
        )
        refTiendas
            .add(restaurante6)

        val restaurante7 = hashMapOf(
            "nameR" to "Fridays",
            "locationR" to "USA",
            "typeR" to "Carnes",
            "hoursR" to "24H",
            "phoneR" to 876578,
            "descriptionR" to "Restaurante de estilo americano especializado en carnes preparadas con un sello distintivo y delicioso."
            ,"favorites" to favorites
        )
        refTiendas
            .add(restaurante7)


    }

    fun anadirFavorito(){

        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        var arrayList: ArrayList<String> = arrayListOf<String>(restauranteCargado!!.favorites.add(BAuthUsuario.usuario!!.email).toString())

        Log.i("firestore-firestore", restauranteCargado!!.favorites.toString())
        Log.i("firestore-firestore", "Wo + $arrayList")
        Log.i("firestore-firestore", "Wo2" + restauranteCargado!!.favorites)


        val db = Firebase.firestore
        val referenciaTienda = db.collection("restaurantes").document(idDocumentRestaurante.toString())
        referenciaTienda.update("favorites", restauranteCargado!!.favorites)

        val btnAnadirFavroito = findViewById<Button>(R.id.btn_anadirFavorito)
        btnAnadirFavroito.visibility = View.INVISIBLE

        val btnQuitarFavroito = findViewById<Button>(R.id.btn_quitarFavorito)
        btnQuitarFavroito.visibility = View.VISIBLE


    }

    fun quitarFavorito(){

        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        var arrayList: ArrayList<String> = arrayListOf<String>(restauranteCargado!!.favorites.remove(BAuthUsuario.usuario!!.email).toString())

        Log.i("firestore-firestore", restauranteCargado!!.favorites.toString())
        Log.i("firestore-firestore", "Wo + $arrayList")
        Log.i("firestore-firestore", "Wo2" + restauranteCargado!!.favorites)


        val db = Firebase.firestore
        val referenciaTienda = db.collection("restaurantes").document(idDocumentRestaurante.toString())
        referenciaTienda.update("favorites", restauranteCargado!!.favorites)

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