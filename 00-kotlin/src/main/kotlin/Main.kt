import java.util.*

fun main(){
    println("HOla mundo")
    //tipo de nombre = valor
    //int edad = 12
    var  edadProfesor = 32;
    //edadProfesor = 6.7
    //var edadProfesor: Int

    //Duck typing
    //Si nadas como un pato, andas como un pato.
    //Depende de las propiedades de una variable pues se le asigna su tipo

    var sueldoProfesor = 1.23

    //variables mutuables, cambian, e inmutables.
    //variables mutables, se pueden reasignar
    var edadCachorro = 0
    edadCachorro = 3
    edadCachorro = 6
    edadCachorro = 3

    val numeroCedula = 453463
    //numeroCedula = 32525

    //tipos de variables(JAVA)
    val nombreProfesor:String = "Oscar Rivera"
    val sueldo: Double = 1.5
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()


    //Condicionales
    if (true){

    }else{

    }

    //Switch
    val estadoCivilWhen:String = "S"
    when (estadoCivilWhen){
        "S" -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    //if de una linea
    val coqueteo = if (estadoCivilWhen=="S") "SI" else "NO"

    imprimirNombre("Adrian")

    calcularSueldo(100.00)
    calcularSueldo(100.00, 14.00)

    //parametros nombrados
    calcularSueldo(
        bonoEspecial = 13.00,
        sueldo = 150.00
    )
    calcularSueldo(
        tasa = 35.00,
        bonoEspecial = 34.00,
        sueldo = 35.00
    )

    //tipos de arreglos
    val arrEstatico: Array<Int> = arrayOf(1, 2, 4, 5)

    val arrDinamico: ArrayList<Int> = arrayListOf(1, 2, 4, 5,7,10)
    println(arrDinamico)
    arrDinamico.add(66)
    arrDinamico.add(55)
    println(arrDinamico)


    //operadoeres
    //for each-> iterar un arreglo
    val respuestaForEach: Unit = arrDinamico
        .forEach{ valorActual: Int ->
        println("Valor actual: ${valorActual}")
        }
    println(respuestaForEach)

    //indices del arreglo
    arrDinamico
        .forEach{
            println("Valor actual: ${it}")
        }
    println(respuestaForEach)

    arrDinamico
        .forEachIndexed{indice:Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }

    //otro operador to wapo
    //MAP -> mutamos un arreglo en otro diferente
    //1. Enviamos los que hay dentro
    //2. nos devuelve un nuevo arreglo con vlaores modificacdos

    val respuestaMap: List<Double> = arrDinamico
        .map { valorActual:Int ->

            return@map valorActual.toDouble() + 100.00
        }

    //so easy, in comparison with a for
    arrDinamico.map { it +15 }

    //FILTRAR DATOS CON CONDICIONES/ EXPRESION

    val respuestaGilter: List<Int> = arrDinamico
        .filter{valorActual:Int ->
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaGilterDos = arrDinamico.filter { it <= 5 }
    println(respuestaGilter)
    println(respuestaGilterDos)
}

//FUNCIONES
fun imprimirNombre(nombre: String):Unit {
    println("Nombre: ${nombre}")
}
//nota that double just have one valor defined
fun calcularSueldo(sueldo: Double, tasa: Double = 12.00, bonoEspecial: Double? = null):Double{
    if(bonoEspecial == null) {
        return sueldo * (100 / tasa)
    }else{
        return sueldo * (100 / tasa) + bonoEspecial
    }
}







