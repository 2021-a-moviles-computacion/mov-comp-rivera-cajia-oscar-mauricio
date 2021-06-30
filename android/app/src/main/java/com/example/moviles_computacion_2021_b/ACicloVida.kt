package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloVida : AppCompatActivity() {

    var numero = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        Log.i("ciclo-vida", "onCreate")
        val textViewACicloVida = findViewById<TextView>(
            R.id.txt_ciclo_vida
        )
        textViewACicloVida.text = numero.toString()
        val buttonACicloVida = findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )
        buttonACicloVida.setOnClickListener{aumentarNumero()}
    }


    //guarda numero
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            //primitivos
            putInt("numeroGuardado", numero)
        }
        super.onSaveInstanceState(outState)
    }

    //recupera numero
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado=savedInstanceState.getInt("numeroGuardado")
        if(numeroRecuperado != null){
            numero = numeroRecuperado
            val textViewCicloVida = findViewById<TextView>(
                R.id.txt_ciclo_vida
            )
            textViewCicloVida.text = numero.toString()
        }
    }




    fun aumentarNumero(){
        numero = numero + 1
        val textViewACicloVida = findViewById<TextView>(
            R.id.txt_ciclo_vida
        )
        textViewACicloVida.text = numero.toString()
    }


    override fun onStart(){
        super.onStart()
        Log.i("ciclo-vida", "onStart")
    }

    override fun onRestart(){
        super.onRestart()
        Log.i("ciclo-vida", "onReStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida", "onDestroy")
    }
}