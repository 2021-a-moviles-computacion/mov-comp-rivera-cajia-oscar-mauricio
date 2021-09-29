package com.example.proyecto2b

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto2b.dto.FirestoreUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    val CODIGO_INICIO_SESION = 102
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var nombreUsuario: String? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        var idDocumentRestaurante: Boolean
        idDocumentRestaurante = intent.getBooleanExtra("idFabricante", false)

        if(idDocumentRestaurante == true){
            setearBienvenida()
        }

        /**INGRESO Y REGISTRO**/
        val botonLogin = findViewById<Button>(R.id.btn_ingresar)
        botonLogin.setOnClickListener{
            llamarLoginUsuario()
        }

        val botonRegistrar = findViewById<Button>(R.id.btn_registrar)
        botonRegistrar.setOnClickListener{
            //abrirActividad(ARestaurantes::class.java)
            abrirActividad(ARegistro::class.java)
        }


        /** USUARIO AUTENTIFICADO **/

        val botonRestaurants = findViewById<ImageView>(R.id.img_restaurants)
        botonRestaurants.setOnClickListener{
            //abrirActividad(ARestaurantes::class.java)
            abrirActividad(GRecyclerViewRestaurants::class.java)
        }

        val botonShops = findViewById<ImageView>(R.id.img_shops)
        botonShops.setOnClickListener{
            //abrirActividad(ATiendaDetalle::class.java)
            //abrirActividad(GRecyclerViewTiendas::class.java)
            abrirActividadConParametro(GRecyclerViewTiendas::class.java, true)
        }

        val botonProfile = findViewById<ImageView>(R.id.icon_profile)
        botonProfile.setOnClickListener{
            //abrirActividad(ATiendaDetalle::class.java)
            abrirActividad(AUsuarioDetalle::class.java)
        }

        val botonVuelos = findViewById<ImageView>(R.id.icon_planes)
        botonVuelos.setOnClickListener {
            abrirActividad(GRecyclerViewVuelos::class.java)
        }

        /**CARGAR DATA**/
        val botonDatosPrueba = findViewById<Button>(R.id.btn_cargarTiendas)
        botonDatosPrueba.setOnClickListener{
            crearDatosTiendas()
        }

        val botonDatosPrueb = findViewById<Button>(R.id.btn_cargarRestaurantes)
        botonDatosPrueb.setOnClickListener{
            //crearDatosRestaurantes()
            crearDatosVuelo()
        }

        val botonLogout = findViewById<Button>(R.id.btn_logout)
        botonLogout.setOnClickListener{
            solicitarSalirDelAplicativo()
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
            "descriptionR" to "Las mejores hamburguesas internacionales.",
            "favorites" to favorites
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


    fun crearDatosTiendas(){

        val db = Firebase.firestore
        val refTiendas = db.collection("tiendas")

        val favorites = arrayListOf("empty")

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
            "urlV" to "https://www.iberia.com/ec/?market=EC&language=es&flexible=true&TRIP_TYPE=2&BEGIN_CITY_01=UIO&END_CITY_01=ATH&BEGIN_DAY_01=28&BEGIN_MONTH_01=202109&BEGIN_YEAR_01=2021&END_DAY_01=28&END_MONTH_01=202110&END_YEAR_01=2021&FARE_TYPE=R&ADT=1&CHD=0&INF=0&BNN=0&YTH=0&YCD=0"
            ,"telefonoV" to 987837278,

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


    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }

    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),
            CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK) {
                    val usuario = IdpResponse.fromResultIntent(data)
                    if(usuario != null) {
                        if (usuario.isNewUser == true) {
                            Log.i("firebase-login", "Nuevo Usuario")
                            registrarUsuarioPorPrimeraVez(usuario)
                        } else {
                            setearUsuarioFirebase()
                            Log.i("firebase-login", "Usuario Antiguo")
                        }
                    }
                }else {
                    Log.i("firebase-login", "El usuario cancelo")
                }
            }

        }
    }


    fun setearBienvenida(){

        val logoImage: ImageView = findViewById(R.id.img_logo)
        val homeImage: ImageView = findViewById(R.id.img_home)
        val restaurantsImage: ImageView = findViewById(R.id.img_restaurants)
        val shopsImage: ImageView = findViewById(R.id.img_shops)
        val iconHome: ImageView = findViewById(R.id.icon_home)
        val iconProfile: ImageView = findViewById(R.id.icon_profile)
        val iconPlanes: ImageView = findViewById(R.id.icon_planes)

        val textViewHome = findViewById<TextView>(R.id.tv_home)
        val textViewPregunta = findViewById<TextView>(R.id.tv_pregunta)
        val textViewHey = findViewById<TextView>(R.id.tv_hey)
        val textViewBar = findViewById<TextView>(R.id.tv_bar)
        val botonLogin = findViewById<Button>(R.id.btn_ingresar)
        val botonRegistrar = findViewById<Button>(R.id.btn_registrar)
        val botonLogout = findViewById<Button>(R.id.btn_logout)
        //val botonLogout = findViewById<Button>(R.id.btn_logout)
        //val botonProducto = findViewById<Button>(R.id.btn_productos)
        //val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        //val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)

        if(BAuthUsuario.usuario != null){
            //textViewHome.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
                logoImage.visibility = View.INVISIBLE
            textViewPregunta.visibility = View.INVISIBLE
            textViewHome.visibility = View.VISIBLE
            textViewHey.visibility = View.VISIBLE

            val db = Firebase.firestore
            val referenciaUsuario = db.collection("usuario").document(BAuthUsuario.usuario!!.email)

            referenciaUsuario
                .get()
                .addOnSuccessListener {
                    val usuarioCargado: FirestoreUsuarioDto? =
                        it.toObject(FirestoreUsuarioDto::class.java)
                    //Log.i("intent", "intent idFabricante ${id}")

                    //nombreUsuario = usuarioCargado!!.name
                    nombreUsuario = usuarioCargado?.name
                    //val txtNNombreModelo = findViewById<TextView>(R.id.tv_nombreU)
                    //txtNNombreModelo.text = nombreUsuario
                    textViewHey.text = "Bienvenido ${nombreUsuario}"
                }


            //textViewHey.text = "Bienvenido ${BAuthUsuario.usuario?.name}"
            textViewBar.visibility = View.VISIBLE
            iconHome.visibility = View.VISIBLE
            iconProfile.visibility = View.VISIBLE
            iconPlanes.visibility = View.VISIBLE
            homeImage.visibility = View.VISIBLE
            restaurantsImage.visibility = View.VISIBLE
            shopsImage.visibility = View.VISIBLE
            botonLogin.visibility = View.INVISIBLE
            botonRegistrar.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
            //botonLogout.visibility = View.VISIBLE
            //botonProducto.visibility = View.VISIBLE
            //botonRestaurante.visibility = View.VISIBLE
            //botonOrdenes.visibility = View.VISIBLE
        }else{
            //textViewBienvenida.text = "Ingresa al aplicativo"
            textViewPregunta.visibility = View.VISIBLE
            textViewHome.visibility = View.INVISIBLE
            textViewHey.visibility = View.INVISIBLE
            logoImage.visibility = View.VISIBLE
            botonLogin.visibility = View.VISIBLE
            homeImage.visibility = View.INVISIBLE
            restaurantsImage.visibility = View.INVISIBLE
            shopsImage.visibility = View.INVISIBLE
            textViewBar.visibility = View.INVISIBLE
            iconHome.visibility = View.INVISIBLE
            iconProfile.visibility = View.INVISIBLE
            botonRegistrar.visibility = View.VISIBLE
            iconPlanes.visibility = View.INVISIBLE
            botonLogout.visibility = View.INVISIBLE
            //botonLogout.visibility = View.INVISIBLE
            //botonProducto.visibility = View.INVISIBLE
            //botonRestaurante.visibility = View.INVISIBLE
            //botonOrdenes.visibility = View.INVISIBLE

        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        //para obtener el UID del usuario
        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .currentUser

        if(usuario.email != null && usuarioLogeado != null){
            //roles ["usuario", "admin"]
            //obtenemos el objeto de firestore
            val db = Firebase.firestore
            //val rolesUsuario = arrayListOf("usuario")

            val identificadorUsuario = usuario.email

            val nuevoUsuario = hashMapOf<String, Any>(
                //"roles" to rolesUsuario,
                "uid" to usuarioLogeado.uid,
                "name" to usuarioLogeado.displayName.toString(),
                "email" to identificadorUsuario.toString()
            )


            db.collection("usuario")
                //yo seteo el ID
                .document(identificadorUsuario.toString())
                .set(nuevoUsuario)
                //el firestore crea el ID
                //.add(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firestore-firestore", "Se creó")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener {
                    Log.i("firestore-firestore", "Falló en la creasao")
                }

        }else{
            Log.i("firebase-login", "ERROR")
        }
    }

    fun setearUsuarioFirebase(){
        val instantAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instantAuth.currentUser

        if(usuarioLocal != null){
            if(usuarioLocal.email != null){

                val db = Firebase.firestore
                val referencia = db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString())
                    //.document(usuarioLocal.)

                referencia
                    .get()
                    .addOnSuccessListener {
                        //val usuarioCargado = it.toObject(BUsuarioFirebase::class.java)
                        Log.i("firebase-firestore", "${it.data}")
                        val usuarioCargado: FirestoreUsuarioDto? = it.toObject(FirestoreUsuarioDto::class.java)

                        if(usuarioCargado != null){
                            BAuthUsuario.usuario = BUsuarioFirebase(
                                usuarioCargado.uid,
                                usuarioCargado.email,
                                usuarioCargado.name,
                                usuarioCargado.city,
                                usuarioCargado.gender,
                                usuarioCargado.phone
                            )
                            setearBienvenida()
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario}")
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario?.email}")
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario?.uid}")
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario?.name}")
                        }
                        Log.i("firebase-firestore", "Usuario cargado")
                    }


                    .addOnFailureListener{
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
            }
        }else{
            Log.i("firebase-login", "FRACASOO")
        }
    }

    fun abrirActividadConParametro(clase: Class<*>, MuestraTiendas: Boolean) {
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra(
            "idFabricante", MuestraTiendas
        )
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun solicitarSalirDelAplicativo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener{
                BAuthUsuario.usuario = null
                setearBienvenida()
            }
    }


}