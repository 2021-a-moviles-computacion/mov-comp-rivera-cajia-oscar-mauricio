package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hhttp)
        metodoGet()
        metodoPost()
    }

    fun metodoGet(){
        //"https://jsonplaceholder.typicode.com/posts". MUCHOS
        "https://jsonplaceholder.typicode.com/posts/1". // 1
        httpGet()
            .responseString{ req, res, result ->
                when (result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val getString = result.get()
                        Log.i("http-klaxon", "${getString}")

                        //cogemos al string que llega y lo casteamos a una clase post
                        val post = Klaxon().parse<IPostHttp>(getString)
                        //val post = Klaxon().parseArray<IPostHttp>(getString) MUCHOS
                        Log.i("http-klaxon", "${post?.body}")

                        //hemos casteado una respuesta desde un servidor en json a una clase. Desde un servidor a una app movil
                    }
                }

            }
    }

    fun metodoPost(){
        //creamos una lista con lo que queremos enviar
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "Titulo moviles",
            "body" to "Descripcion moviles",
            "userId" to 1
        )

        "http://jsonplaceholder.typicode.com/posts"
            .httpPost(parametros)
            .responseString{ request, response, result ->
                when (result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "${postString}")

                        //CASTEO CON EL PARSE, MANDAMOS LA CLASE LO TRANSFORMA Y
                        val post = Klaxon()
                            .parse<IPostHttp>(postString)
                        Log.i("http-klaxon", "${post?.title}")
                    }
                }
            }
    }


}