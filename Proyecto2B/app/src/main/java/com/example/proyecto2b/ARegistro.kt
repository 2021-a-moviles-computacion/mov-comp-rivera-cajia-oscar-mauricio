package com.example.proyecto2b

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto2b.dto.FirestoreUsuarioDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.widget.TextView




class ARegistro : AppCompatActivity() {

    private var editTextName: EditText? = null
    private var editTextEmail:EditText? = null
    private  var editTextPassword:EditText? = null
    private  var editTextPhone:EditText? = null
    private  var editTextCity:EditText? = null
    private lateinit var progressBar: ProgressBar
    var spinnerGenero: Spinner? = null

    var generoSeleccionado: String = "perro"
    var posicionSeleccionado: Int = 0
    //private val mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)


        editTextName = findViewById(R.id.edit_text_name)
        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPassword = findViewById(R.id.edit_text_password)
        editTextPhone = findViewById(R.id.edit_text_phone)
        editTextCity = findViewById(R.id.edit_text_city)

        spinnerGenero = findViewById(R.id.spinner)

        progressBar = findViewById(R.id.progressbar)
        progressBar.setVisibility(View.GONE)

        //findViewById<Button>(R.id.button_register).setOnClickListener(this)

        val list = ArrayList<String>()
        list.add("Selecciones su género")
        list.add("Hombre")
        list.add("Mujer")

        val spinnerRestaurante = findViewById<Spinner>(R.id.spinner)
        val spinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerRestaurante.adapter = spinnerArrayAdapter
        spinnerRestaurante.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                generoSeleccionado = list!![position]
                posicionSeleccionado = position
                Log.i("firebase-firestore", "Género" + generoSeleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                // sometimes you need nothing here
            }
        }


        val button_register = findViewById<Button>(R.id.button_register)
        button_register.setOnClickListener{
            registerUser()
            Log.i("firestore-firestore", "Se creó")
        }

    }



    fun registerUser() {
        val name = editTextName!!.text.toString().trim { it <= ' ' }
        val email = editTextEmail!!.text.toString().trim { it <= ' ' }
        val password = editTextPassword!!.text.toString().trim { it <= ' ' }
        val phone = editTextPhone!!.text.toString().trim { it <= ' ' }
        val city = editTextCity!!.text.toString().trim { it <= ' ' }

        if (posicionSeleccionado==0) {
            val errorText: TextView = spinnerGenero!!.selectedView as TextView
            errorText.error = ""
            errorText.setTextColor(Color.RED) //just to highlight that this is an error
            errorText.text = "Seleccione un género" //changes the selected item text to this
            return
        }

        if (name.isEmpty()) {
            editTextName!!.error = getString(R.string.input_error_name)
            editTextName!!.requestFocus()
            return
        }

        if (email.isEmpty()) {
            editTextEmail!!.error = getString(R.string.input_error_email)
            editTextEmail!!.requestFocus()
            return
        }

        if (city.isEmpty()) {
            editTextCity!!.error = getString(R.string.input_error_city)
            editTextCity!!.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail!!.error = getString(R.string.input_error_email_invalid)
            editTextEmail!!.requestFocus()
            return
        }

        if (password.isEmpty()) {
            editTextPassword!!.error = getString(R.string.input_error_password)
            editTextPassword!!.requestFocus()
            return
        }

        if (password.length < 6) {
            editTextPassword!!.error = getString(R.string.input_error_password_length)
            editTextPassword!!.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            editTextPhone!!.error = getString(R.string.input_error_phone)
            editTextPhone!!.requestFocus()
            return
        }

        if (phone.length != 10) {
            editTextPhone!!.error = getString(R.string.input_error_phone_invalid)
            editTextPhone!!.requestFocus()
            return
        }
        progressBar!!.visibility = View.VISIBLE
        val instantAuth = FirebaseAuth.getInstance()

        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .currentUser

        if (instantAuth != null) {
            instantAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val user = FirestoreUsuarioDto(
                        name,
                        email,
                        phone
                    )

                    val db = Firebase.firestore
                    //val referencia = db.collection("usuario")

                    val nuevoUsuario = hashMapOf<String, Any>(
                        //"roles" to rolesUsuario,
                        "uid" to usuarioLogeado!!.uid,
                        "name" to name,
                        "email" to email,
                        "phone" to phone.toInt(),
                        "gender" to generoSeleccionado,
                        "city" to city
                    )
                    progressBar!!.visibility = View.GONE
                    db.collection("usuario")
                        //.add(nuevoUsuario)
                        .document(email.toString())
                        .set(nuevoUsuario)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                getString(R.string.registration_success),
                                Toast.LENGTH_LONG
                            ).show()
                            Log.i("firestore-firestore", "Se creó")
                            //setearUsuarioFirebase()
                            abrirActividad(MainActivity::class.java)
                        }


                    //FirebaseDatabase.getInstance().getReference("usuarios")
                    //    .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                }
        }


    }

    fun abrirActividad(clase: Class<*>) {
        val intentEpxlicito = Intent(
            this,
            clase
        )
        startActivity(intentEpxlicito)
    }
}




