package com.example.moviles_computacion_2021_b

class BBaseDatosMemoria {
    var papel:String = "Papelito"
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        fun arreglarAlgoDelPapel(
            bddMemoria:BBaseDatosMemoria,
            nuevoPapel: String
        ){
            bddMemoria.papel = nuevoPapel
        }
        init {
            arregloBEntrenador
                .add(BEntrenador("Adrian", "a@a.com", null))
            arregloBEntrenador
                .add(BEntrenador("Vicente", "v@v.com", null))
            arregloBEntrenador
                .add(BEntrenador("Pepe", "p@p.com", null))
        }
    }
}