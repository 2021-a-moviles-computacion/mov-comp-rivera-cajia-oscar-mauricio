package com.example.proyecto2b

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.proyecto2b.dto.FirestoreVuelosDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalTime
import android.content.Intent
import android.net.Uri


class AVueloDetalle : AppCompatActivity() {

    var aerolineaV: String? = null
    var fechaSalidaV: String? = null
    var fechaLlegadaV: String? = null
    var origenV: String? = null
    var precioV: Int? = null
    var destinoV: String? = null
    var horaSalidaV: String? = null
    var horaLlegadaV: String? = null
    var duracionV: String? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avuelo_detalle)

/*
        val botonDatosPrueba = findViewById<Button>(R.id.btn_cargarVuelos)
        botonDatosPrueba.setOnClickListener {
            crearDatosVuelo()
        }*/

        val intent3 = intent
        val urlVuelo = intent3.getStringExtra("urlVuelo")

        val intent4 = intent
        val telefonoVuelo = intent4.getIntExtra("telefonoVuelo", 0)

        val botonDatosPrueba = findViewById<Button>(R.id.btn_url)
        botonDatosPrueba.setOnClickListener {
            val uri: Uri = Uri.parse(urlVuelo) // missing 'http://' will cause crashed

            val intentUrl = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intentUrl)
        }

        val botonDatosPrueb = findViewById<Button>(R.id.btn_telefonoV)
        botonDatosPrueb.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+telefonoVuelo.toString())
            startActivity(intent)
        }


        val intent = intent
        val idDocumentRestaurante = intent.getStringExtra("idFabricante")

        /**CARGAR DATOS DEL VUELO SELECCIONADO**/

        val db = Firebase.firestore
        val referencia = db.collection("vuelos").document(idDocumentRestaurante.toString())
        referencia
            .get()
            .addOnSuccessListener {
                val vueloCargada: FirestoreVuelosDto? =
                    it.toObject(FirestoreVuelosDto::class.java)

                aerolineaV = vueloCargada?.aerolineaV
                val tvNombreRestaurante = findViewById<TextView>(R.id.tv_aerolineaV)
                tvNombreRestaurante.text = aerolineaV

                fechaSalidaV = vueloCargada?.fechaSalidaV
                val tvUbicacionRestaurante = findViewById<TextView>(R.id.tv_fechaSalidaV)
                tvUbicacionRestaurante.text = fechaSalidaV

                fechaLlegadaV = vueloCargada?.fechaLlegadaV
                val tvHorarioRestaurante = findViewById<TextView>(R.id.tv_fechaLlegadaV)
                tvHorarioRestaurante.text = fechaLlegadaV

                origenV = vueloCargada?.origenV
                val tvTipoRestaurante = findViewById<TextView>(R.id.tv_origenV)
                tvTipoRestaurante.text = origenV

                destinoV = vueloCargada?.destinoV
                val tvTelefonoRestaurante = findViewById<TextView>(R.id.tv_destinoV)
                tvTelefonoRestaurante.text = destinoV

                precioV = vueloCargada?.precioV
                val tvDescripcionRestaurante = findViewById<TextView>(R.id.tv_precioVV)
                tvDescripcionRestaurante.text = "$"+precioV.toString()

                horaSalidaV = vueloCargada?.horaSalidaV
                val tvHoraSalida = findViewById<TextView>(R.id.tv_horaSalidaV)
                tvHoraSalida.text = horaSalidaV

                horaLlegadaV = vueloCargada?.horaLlegadaV
                val tvHoraLlegada = findViewById<TextView>(R.id.tv_horaLlegadaV)
                tvHoraLlegada.text = horaLlegadaV

                duracionV = vueloCargada?.duracionV
                val tvDuracionV = findViewById<TextView>(R.id.tv_duracionV)
                tvDuracionV.text = duracionV

            }



    }

        @RequiresApi(Build.VERSION_CODES.O)
        fun crearDatosVuelo() {

            var horaSalida: LocalTime = LocalTime.of(11, 15)
            var hour = 11
            var minute = 0
            var duracionV: String = String.format("%dH %dm", hour, minute)
            val db = Firebase.firestore
            val refVuelos = db.collection("vuelos")

            val vuelo1 = hashMapOf(
                "aerolineaV" to "Iberia",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Vie 29 Sep",
                "origenV" to "UIO",
                "destinoV" to "ATH",
                "precioV" to 15000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(17).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://www.iberia.com/ec/?market=EC&language=es&flexible=true&TRIP_TYPE=2&BEGIN_CITY_01=UIO&END_CITY_01=ATH&BEGIN_DAY_01=28&BEGIN_MONTH_01=202109&BEGIN_YEAR_01=2021&END_DAY_01=28&END_MONTH_01=202110&END_YEAR_01=2021&FARE_TYPE=R&ADT=1&CHD=0&INF=0&BNN=0&YTH=0&YCD=0",
                "telefonoV" to 987837278,

            )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo1)


            horaSalida = LocalTime.of(16, 45)
            hour = 12
            duracionV = String.format("%dH %dm", hour, minute)

            val vuelo2 = hashMapOf(
                "aerolineaV" to "Avianca",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Vie 29 Sep",
                "origenV" to "UIO",
                "destinoV" to "DUB",
                "precioV" to 20000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(12).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://www.avianca.com/ec/es/descubre-y-compra/promociones/",
                "telefonoV" to 987837278,

            )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo2)

            horaSalida = LocalTime.of(21, 0)
            hour = 5
            duracionV = String.format("%dH %dm", hour, minute)
            val vuelo3 = hashMapOf(
                "aerolineaV" to "Avianca",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Vie 29 Sep",
                "origenV" to "UIO",
                "destinoV" to "AUS",
                "precioV" to 13000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(5).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://www.avianca.com/ec/es/descubre-y-compra/promociones/",
                "telefonoV" to 987837278,

            )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo3)

            horaSalida = LocalTime.of(10, 30)
            hour = 17
            duracionV = String.format("%dH %dm", hour, minute)

            val vuelo4 = hashMapOf(
                "aerolineaV" to "American Airlines",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Vie 29 Sep",
                "origenV" to "UIO",
                "destinoV" to "MKC",
                "precioV" to 8000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(17).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://www.aa.com/booking/flights/choose-flights/flight1?bookingPathStateId=1632843683041-524&redirectSearchToLegacyAACom=false",
                "telefonoV" to 987837278,

            )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo4)

            horaSalida = LocalTime.of(7, 0)
            hour = 11
            duracionV = String.format("%dH %dm", hour, minute)

            val vuelo5 = hashMapOf(
                "aerolineaV" to "Iberia",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Jue 28 Sep",
                "origenV" to "UIO",
                "destinoV" to "MAD",
                "precioV" to 11000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(11).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://www.iberia.com/ec/?market=EC&language=es&flexible=true&TRIP_TYPE=2&BEGIN_CITY_01=UIO&END_CITY_01=ATH&BEGIN_DAY_01=28&BEGIN_MONTH_01=202109&BEGIN_YEAR_01=2021&END_DAY_01=28&END_MONTH_01=202110&END_YEAR_01=2021&FARE_TYPE=R&ADT=1&CHD=0&INF=0&BNN=0&YTH=0&YCD=0",
                "telefonoV" to 987837278,

                )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo5)

            horaSalida = LocalTime.of(21, 0)
            hour = 16
            duracionV = String.format("%dH %dm", hour, minute)

            val vuelo6 = hashMapOf(
                "aerolineaV" to "Emirates",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Vie 29 Sep",
                "origenV" to "UIO",
                "destinoV" to "DXB",
                "precioV" to 19000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(16).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://fly2.emirates.com/CAB/IBE/SearchResult.aspx",
                "telefonoV" to 987837278,

            )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo6)

            horaSalida = LocalTime.of(21, 0)
            hour = 11
            duracionV = String.format("%dH %dm", hour, minute)
            val vuelo7 = hashMapOf(
                "aerolineaV" to "Avianca",
                "fechaSalidaV" to "Jue 28 Sep",
                "fechaLlegadaV" to "Vie 29 Sep",
                "origenV" to "UIO",
                "destinoV" to "GRX",
                "precioV" to 13000,
                "horaSalidaV" to horaSalida.toString(),
                "horaLlegadaV" to horaSalida.plusHours(11).toString(),
                "duracionV" to duracionV,
                "urlV" to "https://www.avianca.com/ec/es/descubre-y-compra/promociones/",
                "telefonoV" to 987837278,

            )
            //refTiendas.document("SF").set(restaurante1)
            refVuelos
                .add(vuelo7)
        }




    }