package com.example.firebaseuno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebaseuno.dto.FirestoreUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.security.AuthProvider

class MainActivity : AppCompatActivity() {

    val CODIGO_INICIO_SESION = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin = findViewById<Button>(R.id.btn_login)
        botonLogin.setOnClickListener{
            llamarLoginUsuario()
        }

        val botonLogout = findViewById<Button>(R.id.btn_logout)
        botonLogout.setOnClickListener{
            solicitarSalirDelAplicativo()
        }

        val botonProducto = findViewById<Button>(R.id.btn_productos)
        botonProducto.setOnClickListener{
            val intent = Intent(
                this,
                CProducto::class.java
            )
            startActivity(intent)
        }

        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        botonRestaurante.setOnClickListener{
            val intent = Intent(
                this,
                DRestaurante::class.java
            )
            startActivity(intent)
        }

        val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)
        botonOrdenes.setOnClickListener{
            val intent = Intent(
                this,
                EOrdenes::class.java
            )
            startActivity(intent)
        }

        val botonIrMapa = findViewById<Button>(R.id.btn_ir_mapa)
        botonIrMapa.setOnClickListener{
            val intent = Intent(
                this,
                FMapsActivity::class.java
            )
            startActivity(intent)
        }

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
        val textViewBienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonLogout = findViewById<Button>(R.id.btn_logout)
        val botonProducto = findViewById<Button>(R.id.btn_productos)
        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)
        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonRestaurante.visibility = View.VISIBLE
            botonOrdenes.visibility = View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingresa al aplicativo"
            botonLogin.visibility = View.VISIBLE
            botonLogout.visibility = View.INVISIBLE
            botonProducto.visibility = View.INVISIBLE
            botonRestaurante.visibility = View.INVISIBLE
            botonOrdenes.visibility = View.INVISIBLE

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
            val rolesUsuario = arrayListOf("usuario")

            val identificadorUsuario = usuario.email

            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to rolesUsuario,
                "uid" to usuarioLogeado.uid,
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
                                usuarioCargado.roles
                            )
                            setearBienvenida()
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario}")
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario?.email}")
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario?.uid}")
                            Log.i("firebase-firestore", "${BAuthUsuario.usuario?.roles}")
                        }
                            Log.i("firebase-firestore", "Usuario cargado")
                                               }


                    .addOnFailureListener{
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
            }
        }
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