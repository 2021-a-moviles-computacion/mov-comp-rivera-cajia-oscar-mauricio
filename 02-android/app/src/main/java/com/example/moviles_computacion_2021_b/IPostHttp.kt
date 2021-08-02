package com.example.moviles_computacion_2021_b

class IPostHttp(val id: Int,
                var userId: Any,//el backend a veces nos responde un string y otras veces un entero
                val title: String,
                var body: String) {
    init {//cuando vayamos a inicializar la variable userID
        if(userId is String){
            userId = (userId as String).toInt()//lo casteamos a entero
        }
        if (userId is Int){
            userId = userId//entero pues entero
        }
    }
    //vamos a transformar lo que obtenemos



}