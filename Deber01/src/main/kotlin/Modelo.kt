import java.io.File
import java.io.PrintWriter

class Modelo(var id: Int,
             var nombreModelo:String,
             var precioMod:Double,
             var nPuertasMod: Int,
             var puntuacionExperta: Double,
             var serie: Char,
             var fabricante:Int)
{

    override fun toString(): String {

        return "${id}, ${nombreModelo}, ${precioMod}, ${nPuertasMod}, ${puntuacionExperta}, ${serie}"

    }
    fun crearModelo(filename: String){
        File(filename).appendText(
            "\n${id}, ${nombreModelo}, ${precioMod}, ${nPuertasMod}, ${puntuacionExperta}, ${serie}, ${fabricante}")
    }

    fun  actualizarModelo(filename: String){
        var text: MutableList<String> = File(filename).readLines().toMutableList()
        var i: Int = 0
        while (i < text.size){
            var auxiliar= separ(text[i])
            if (auxiliar[0].equals(id.toString())){text[i] = "${id}, ${nombreModelo}, ${precioMod}, ${nPuertasMod}, ${puntuacionExperta}, ${serie}, ${fabricante}"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i =0
        while (i < text.size){
            if (i != 0) File(filename).appendText("\n${text[i]}")
            else File(filename).appendText("${text[i]}")
            i++

        }
    }

    fun eliminarModelo(filename: String){
        var text: MutableList<String> = File (filename).readLines().toMutableList()
        var i: Int = 0
        while (i <text.size){
            var auxiliar = separ(text[i])
            if (auxiliar[0].equals(this.id.toString())){text[i] = "vacio"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i = 0
        while (i< text.size){
            if (!text[i].equals("vacio")){
                if (i !=0) File(filename).appendText("\n${text[i]}")
                else File(filename).appendText("${text[i]}")
            }
            i++

        }
        println(text)

    }



}