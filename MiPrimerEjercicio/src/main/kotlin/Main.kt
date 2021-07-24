//import jdk.nashorn.internal.objects.NativeArray.reduce

import android.arch.persistence.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sun.jvm.hotspot.debugger.Address
import java.time.LocalDate
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

fun main(){
    println("HOla mundo")

    getAllPlayer()
}

fun createPlayer(name: String, position: String, avatar: String) : CodePivot.Player {
    var player = CodePivot.Player(name, position)
    player.avatar = avatar
    player.id = MyApp.database?.userDao()?.insertPlayer(player)!!
    return player
}










