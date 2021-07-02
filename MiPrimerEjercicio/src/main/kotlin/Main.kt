//import jdk.nashorn.internal.objects.NativeArray.reduce
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

    val respuestaAny: Boolean = arrDinamico.any { valorActual:Int -> return@any (valorActual > 5) }
    println(respuestaAny)
    val respuestaAll: Boolean = arrDinamico.all { valorActual:Int -> return@all (valorActual > 5) }
    println(respuestaAll)


    //operador REDUCE
    //1. devuelve lo que acumula
    //en que valor empieza -> 0
    //el ope

    val respuestaReduce: Int =
        arrDinamico.reduce { acumulado:Int, valorActual:Int -> return@reduce (acumulado + valorActual) }
    println(respuestaReduce)

    val arregloDanio = arrayListOf<Int>(12,15, 8, 10)
    val respuestaReduceFold: Int =
        arregloDanio.fold(100, {acumulado, valorActualIteracion -> return@fold acumulado - valorActualIteracion})
    println(respuestaReduceFold)

    val vidaActual: Double = arrDinamico
        .map { it * 2.3 }
        .filter { it > 20 }
        .fold(100.00, {acc, i -> acc - i})
        .also { println(it) }
    println("Valor vida actual ${vidaActual}")

    val ejemploUno = Suma(1, 2)
    // val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null, 2)
    // val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1, null)
    // val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma(null, null)
    // val ejemploCuatro = Suma(null,null)
    println(ejemploUno.sumar())
    println(ejemploDos.sumar())
    println(ejemploTres.sumar())
    println(ejemploCuatro.sumar())
    //    Suma.historialSumas
    //    Suma.agregarHistorial(1)


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



//CLASES, BABE.

abstract class NumerosJava {
    protected val numeroUno: Int // Propiedad clase
    private val numeroDos: Int // Propiedad clase

    constructor(
        uno: Int,   // Parametros requeridos
        dos: Int,   // Parametros requeridos
    ) {
//        this.numeroUno = uno
//        this.numeroDos = dos
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }

}

abstract class Numeros(
    // Constructor Primario
    //modificadores de acceso
    protected var numeroUno: Int, // Propiedad clase
    protected var numeroDos: Int,  // Propiedad clase
) {
    init { // Bloque inicio del constructor primario
        println("Inicializar")
    }
}
// instancia.numeroUno
// instancia.numeroDos

class Suma(
    // Constructor primario
    uno: Int, // Parametro requerido
    dos: Int, // Parametro requerido
) : Numeros( // Constructor "papa" (super)
    uno,
    dos
) {
    init {
        this.numeroUno
        this.numeroDos
        // X -> this.uno -> NO EXISTEN
        // X -> this.dos -> NO EXISTEN
    }

    constructor( //  Segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ) : this( // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    )

    constructor( //  Tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this(
        // llamada constructor primario
        uno,
        if (dos == null) 0 else dos,
    )

    constructor( //  Cuarto constructor
        uno: Int?, // parametros
        dos: Int? // parametros
    ) : this(
        // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    )

    //FUNCION DENTRO DE UNA CLASE.PARAMETROS REQUERIDOS, POR DEFECTOS O NULOS
    //TODAS LAS FUNCIONES POR DEFECTO SON PUBLICOS
    // public fun sumar(): Int {
    fun sumar(): Int {
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    // SINGLETON
    companion object {
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            // this.historialSumas.add(valorNuevaSuma)
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }

}








