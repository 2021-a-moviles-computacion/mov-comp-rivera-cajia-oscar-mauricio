import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main(){
    val fileNameFabricantes: String =
        "src/main/resources/Fabricantes.txt"
    val fileNameModelos: String =
        "src/main/resources/Modelos.txt"

    do{
        //println("\n")
        println("\n\n /*---------------------------------------------------------------------------------------**/\n" +
                "/**     F      A      B        R       I       C       A       N       T       E       S   **/\n" +
                "/**----------------------------------------------------------------------------------------**/\n")
        println(
                    "(1).   Leer fabricantes\n" +
                    "(2).   Crear un fabricante\n" +
                    "(3).   Actualizar un fabricante\n" +
                    "(4).   Eliminar un fabricante"
        )
        println("\n /*-------------------------------------------------------------**/\n" +
                "/**     M       O      D       E       L       O       S        **/\n" +
                "/**-------------------------------------------------------------**/\n")
        println(
                    "(5).   Leer modelos\n" +
                    "(6).   Crear un modelo\n" +
                    "(7).   Actualizar un modelo\n" +
                    "(8).   Eliminar un modelo\n\n" +
                    "(0).   Salir del sistema\n"
        )

        val scan = Scanner(System.`in`)
        var n = scan.nextLine().trim().toInt()

        when(n){
            1 ->{
                desplegarFabricantes(fileNameFabricantes)
            }
            2 ->{
                println(
                    "Ingrese los datos del nuevo fabricante:\n" +
                            "'Nombre del fabricante', 'Tipo de fabricante', 'Sede del fabricante', 'Fecha de fundación (AAAA-MM-DD)', 'Fundador'\n" +
                            "Cancelar = 0"
                )
                var scan = Scanner(System.`in`)
                val n1 = scan.nextLine()
                if (!n1.equals("0")){
                    crearFabricante(n1,fileNameFabricantes)
                    println("Fabricante ingresado correctamente\n")
                }
            }
            3 ->{
                println("Digite el id del fabricante que desea actualizar")
                desplegarFabricantes(fileNameFabricantes)
                //  println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n = scan.nextLine()
                if (!n.equals("0")){
                    val fabricante: String = busquedaFabricantesById(fileNameFabricantes,n)
                    val idFabricante = separ(fabricante)
                    if (!fabricante.equals("hole")){
                        print("${fabricante}\n")
                        println(
                            "Ingrese los datos actualizados del  fabricante:\n" +
                                    "'Nombre del fabricante', 'Tipo de fabricante', 'Sede del fabricante', 'Año de fundación (AAAA-MM-DD)', 'Fundador'\n" +
                                    "ó 0 para cancelar"
                        )
                        val scan1= Scanner(System.`in`)
                        val n1 = scan1.nextLine()
                        if (!n1.equals(0)){
                            actualizarFabricante(n1,fileNameFabricantes,idFabricante[0])
                            println("Fabricante actualizado correctamente\n")
                        }


                    }else println("No existe un fabricante que corresponda con ese id introducido.\n")
                }
            }
            4 ->{
                println("\nDigite el id del fabricante a eliminar")
                desplegarFabricantes(fileNameFabricantes)
                val scan = Scanner(System.`in`)
                val n = scan.nextLine()
                val fabricante: String = busquedaFabricantesById(fileNameFabricantes,n)
                if (!fabricante.equals("hole")){
                    print("${fabricante}\n")
                    println(
                        "Esta seguro que desea eliminar este fabricante?\n" +
                                "Presione S para confirmar o N para cancelar"

                    )
                    val scan1 = Scanner(System.`in`)
                    val n1 = scan1.nextLine()
                    if (n1.toLowerCase().equals("s")){
                        eliminarFabricante(fabricante,fileNameFabricantes)
                        println("Fabricante eliminado correctamente\n")
                    }else{
                        println("Operacion Cancelada")
                    }
                }else println("No existe un fabricante que corresponda a este id introducido.\n")
            }
            5 ->{
                desplegarModelos(fileNameModelos,fileNameFabricantes)
            }
            6 ->{
                println("\n Digite el id del Fabricante al que pertenece el nuevo modelo")
                desplegarFabricantes(fileNameFabricantes)
                //  println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n  = scan.nextLine()
                if (!n.equals("0")){
                    println(
                        "Ingrese los datos del nuevo Modelo como se indica\n" +
                                "'Nombre del modelo', 'Precio del modelo', 'Número de puertas', 'PuntuacionExperta', 'Serie'\n" +
                                "ó 0 para cancelar"
                    )
                    val n1 = scan.nextLine()
                    if (!n1.equals("0")){
                        crearModelo(n1, fileNameModelos, n)
                        println("Modelo ingresado correctamente\n")
                    }
                }
            }
            7 ->{
                println("Digite el id del modelo que desea actualizar")
                desplegarModelos(fileNameModelos, fileNameFabricantes)
                println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n  = scan.nextLine()
                if (!n.equals("0")){
                    println("El modelo seleccionado es: ")
                    desplegarModeloById(fileNameModelos,fileNameFabricantes,n)
                    println(
                        "Ingrese los datos actualizados del Modelo como se indica\n" +
                                "'Nombre del modelo', 'Precio del modelo', 'Número de puertas', 'PuntuacionExperta', 'Serie'\n" +
                                "ó 0 para cancelar"
                    )
                    val n1 = scan.nextLine()
                    if (!n1.equals("0")){
                        var id: Int = consultarIdFabricante2(fileNameFabricantes)
                            actualizarModelo(n1,fileNameModelos,n,id)
                            println("Modelo actualizado correctamente\n")
                    }
                }

            }
            8 ->{
                println("\nDigite el id del modelo que desea eliminar")
                desplegarModelos(fileNameModelos, fileNameFabricantes)
                val scan = Scanner(System.`in`)
                val n  = scan.nextLine()
                if (!n.equals("0")){
                    println("El modelo seleccionado es: ")
                    var modelo = busquedaModeloById(fileNameModelos,n)
                    desplegarModeloById(fileNameModelos,fileNameFabricantes,n)
                    println(
                        "Are u sure?\n" +
                                "S/N?"

                    )
                    val scan1 = Scanner(System.`in`)
                    val n1 = scan1.nextLine()
                    if(n1.toLowerCase().equals("s")){
                        eliminarModelo(modelo,fileNameModelos)
                        println("Modelo eliminadao correctamente\n")

                    }
                }
                else {
                    println("Operacion Cancelada\n")
                }
            }
            else ->{
                n = 0
            }

        }

    } while ( n !=0)
}


/**-------------------------------------------------------------**/
/**     F   A   B   R   I   C   A   N   T   E   S               **/
/**-------------------------------------------------------------**/

fun desplegarFabricantes(filename: String)
{
    println("FabricanteId, NombreFabricante, TipoFabricante, SedeFab, Fecha de fundacion, Fundador")
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    while (i < lines.size){
        var fabricante = separ(lines[i])
        var manufacturer = Fabricante(
            fabricante[0].toInt(), fabricante[1], fabricante[2], fabricante[3], LocalDate.parse(fabricante[4], DateTimeFormatter.ISO_DATE), fabricante[5]
        ).toString()
        println(manufacturer)
        i++
    }
}
fun busquedaFabricantesById(
    filename: String,
    id: String): String
{
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    while (i< lines.size){
        var fabricante = separ(lines[i])
        if (fabricante[0].equals(id)){
            return  lines.get(i)
        }
        i++
    }
    return "hole"
}

fun separ(
    input:String):List<String>
{
    val result = input.split(", ")
    return result
}
//CRUD

fun crearFabricante(
    input: String,
    filename: String)
{
    var fabricante = separ(input)
    var id: Int = consultarIdFabricante(filename)
    var nuevofabricante = Fabricante(
        id, fabricante[0], fabricante[1], fabricante[2], LocalDate.parse(fabricante[3], DateTimeFormatter.ISO_DATE), fabricante[4]
    ).crearFabricante(filename)
}
fun actualizarFabricante(
    input: String,
    filename: String,
    id: String)
{
    var fabricante = separ(input)
    var fabricanteActualizado = Fabricante(id.toInt(), fabricante[0], fabricante[1], fabricante[2], LocalDate.parse(fabricante[3], DateTimeFormatter.ISO_DATE), fabricante[4]).actualizarFabricante(filename)
}

fun eliminarFabricante(
    input: String,
    filename: String)
{
    var fabricante = separ(input)
    var fabricanteEliminado = Fabricante(fabricante[0].toInt(), fabricante[1], fabricante[2], fabricante[3], LocalDate.parse(fabricante[4], DateTimeFormatter.ISO_DATE), fabricante[5]).eliminarFabricante(filename)
}

fun consultarIdFabricante(
    filename: String):Int
{
    val lines: List<String> =File(filename).readLines()
    var idFabricante = separ(lines[(lines.size - 1)])
    return (idFabricante[0].toInt() + 1)
}

fun consultarIdFabricante2(
    filename: String):Int
{
    val lines: List<String> =File(filename).readLines()
    var idFabricante = separ(lines[(lines.size - 1)])
    return (idFabricante[0].toInt())
}

/**-------------------------------------------------------------**/
/**     M       O      D       E       L       O       S        **/
/**-------------------------------------------------------------**/

fun desplegarModelos(
    filenameModelo: String,
    filenameFabricante: String)
{
    println("ModeloId, MombreModelo, PrecioModelo, NumPuertas, PuntuacionExperta, Serie, FabricanteId(FK)")
    val lines: List<String> = File(filenameModelo).readLines()
    var i: Int = 0
    while (i < lines.size){
        var modelo = separ(lines[i])
        var model = Modelo(modelo[0].toInt(), modelo[1], modelo[2].toDouble(), modelo[3].toInt(), modelo[4].toDouble(), modelo[5].single(), modelo[6].toInt())
        var fabricanteFK = separ(busquedaFabricantesById(filenameFabricante,modelo[6]))
        println(model.toString() +", " +"${fabricanteFK[0]} ( ${fabricanteFK[1]})")
        i++
    }
}


fun crearModelo(
    input: String,
    filename: String,
    idFabricante: String)
{
    var modelo = separ(input)
    var id: Int = consultarIdModelo(filename)
    var nuevoModelo = Modelo(id, modelo[0], modelo[1].toDouble(), modelo[2].toInt(), modelo[3].toDouble(), modelo[4].single(), idFabricante.toInt()).crearModelo(filename)
}

fun actualizarModelo(
    input: String,
    filename: String,
    id: String,
    idFabricante:Int)
{
    var modelo = separ(input)
    var modeloActualizado = Modelo(id.toInt(), modelo[0], modelo[1].toDouble(), modelo[2].toInt(), modelo[3].toDouble(), modelo[4].single(), idFabricante).actualizarModelo(filename)

}

fun eliminarModelo(
    input: String,
    filename: String)
{
    var modelo = separ(input)
    var modeloEliminado = Modelo(modelo[0].toInt(), modelo[1], modelo[2].toDouble(), modelo[3].toInt(), modelo[4].toDouble(), modelo[5].single(), modelo[6].toInt()).eliminarModelo(filename)
}

fun busquedaModeloById(
    filename: String,
    id: String): String
{
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    while (i < lines.size){
        var modelo = separ(lines[i])
        if (modelo[0].equals(id)){
            return lines.get(i)
        }
        i++
    }
    return "hole"
}
//Para elegir el modelo que quiero eliminar, pa que se muestre
fun desplegarModeloById(
    fileModelo: String,
    fileFabricante: String,
    id: String)
{
    var modelo = separ(busquedaModeloById(fileModelo, id))
    var model = Modelo(modelo[0].toInt(), modelo[1], modelo[2].toDouble(), modelo[3].toInt(), modelo[4].toDouble(), modelo[5].single(), modelo[6].toInt())
    var fabricanteFK = separ(busquedaFabricantesById(fileFabricante, modelo[6]))
    println(model.toString() + "${fabricanteFK[1]} ${fabricanteFK[2]}")

}

fun consultarIdModelo(filename: String):Int
{
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    var idModelo = separ(lines[(lines.size -1)])
    return (idModelo[0].toInt()+1)
}