import java.io.File
import java.io.PrintWriter
import java.time.LocalDate

class Fabricante(
    var id: Int,
    var nombreFabricante:String,
    var tipoFabricante: String,
    var sedeFabricante: String,
    var fechaFundacion: LocalDate,
    var fundador:String)
{
    override fun toString(): String {
        return "${id}, ${nombreFabricante}, ${tipoFabricante}, ${sedeFabricante}, ${fechaFundacion}, ${fundador}"
    }

    fun crearFabricante(filename: String){
        File(filename).appendText("\n${id}, ${nombreFabricante}, ${tipoFabricante}, ${sedeFabricante}, ${fechaFundacion}, ${fundador}")
    }

    fun actualizarFabricante(filename: String){
        var text:MutableList<String> = File(filename).readLines().toMutableList()
        var i: Int=0
        while (i < text.size){
            var auxiliar = separ(text[i])
            if (auxiliar[0].equals(id.toString())){
                text[i] = "${id}, ${nombreFabricante}, ${tipoFabricante}, ${sedeFabricante}, ${fechaFundacion}, ${fundador}"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i=0
        while (i < text.size){
            if (i !=0) File(filename).appendText("\n${text[i]}")
            else File (filename).appendText("${text[i]}")
            i++
        }
    }
    fun eliminarFabricante(filename: String){
        var text: MutableList<String> = File(filename).readLines().toMutableList()
        var i: Int = 0
        while (
            i < text.size)
            {
            var auxiliar = separ(text[i])
            if (auxiliar[0].equals(id.toString())){text[i]="vacio"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i = 0
        while (i<text.size){
            if (!text[i].equals("vacio")){
                if (i!=0) File(filename).appendText("\n${text[i]}")
                else File(filename).appendText("${text[i]}")
            }
            i++
        }
        println(text)
    }
}