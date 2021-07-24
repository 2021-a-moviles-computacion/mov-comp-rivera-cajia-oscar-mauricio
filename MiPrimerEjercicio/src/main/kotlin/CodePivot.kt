import android.arch.persistence.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CodePivot {



    //abstract class User(var name: String)
    abstract class User(var name: String) {
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }

    //mi entidad
    @Entity
    data class Palo(
        @PrimaryKey(autoGenerate = true)
        //val id: Int,
        val firstName: String?,
        @Embedded val address: sun.jvm.hotspot.debugger.Address?
    )

    @Entity
    data class Fabricante(
        //@PrimaryKey(autoGenerate = true)
        @PrimaryKey val fabricanteId: Long,
        val nameFab: String,
        val anioFab: Int
    )

    @Entity
    data class Modelo(
        @PrimaryKey val modeloId: Long,
        val fabricanteCreatorId: Long,
        val playlistName: String
    )

    data class FabricanteConModelos(
        @Embedded val Fabricante: Fabricante,
        @Relation(
            parentColumn = "fabricanteId",
            entityColumn = "fabricanteCreatorId"
        )
        val Modelos: List<Modelo>
    )

    @Dao
    interface MyDao {

        //@Transaction
        @Query("SELECT * FROM Modelo")
        fun getFabricanteConModelos(): List<FabricanteConModelos>

    }

    //@Transaction
    @Query("SELECT * FROM Modelo")
    fun getFabricanteConModelos(): List<FabricanteConModelos>



    @Query("SELECT * FROM Fabricante WHERE id=:fabricanteId")
    fun getPlayer(playerId: Long) : Player {
    }


    //The entity transformation is done via the @Entity annotation, nothing more

    @Entity(tableName="player")
    class Player (name: String, var position: String) : User(name) {
        @Ignore//indicates that the field ‘avatar’ will not be persisted
        lateinit var avatar: String
    }

    //class Coach(name: String, var experience: Int) : User(name)
    @Entity
    class Coach(name: String,
                @ColumnInfo(name = "xp")
                var experience: Int
    ) : User(name)



    /*
    class Team(var name: String, var coachId: Long,
               var address: Address, var players: List<Long>)*/
    @Entity(foreignKeys = arrayOf(
        ForeignKey(entity = Coach::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("coachId"),
            onDelete = ForeignKey.NO_ACTION)
    )
    )
    class Team(var name: String, var coachId: Long,
               @Embedded
               var address: sun.jvm.hotspot.debugger.Address
    ) {

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }




    class Address(var address: String, var postal: String, var city: String)



    //class Match (var date: Date, var firstTeamId: Long, var secondTeamId: Long)
    @Entity(foreignKeys = arrayOf(
        ForeignKey(entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("firstTeamId"),
            onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("secondTeamId"),
            onDelete = ForeignKey.NO_ACTION)
    )
    )
    class Match (var date: Date, var firstTeamId: Long,
                 var secondTeamId: Long) {

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }

    /*class Score(var label: String, var scoreTeam1: Int,
                var scoreTeam2: Int, var matchId: Long)*/
    @Entity(foreignKeys = arrayOf(
        ForeignKey(entity = Match::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("matchId"),
            onDelete = ForeignKey.CASCADE)
    )
    )
    class Score(var label: String, var scoreTeam1: Int, var scoreTeam2: Int,
                var matchId: Long) {

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }

    class TeamAllPlayers {

        @Embedded
        var team: Team? = null

        @Relation(parentColumn = "id", entityColumn = "teamId")
        var players: List<Player>? = null
    }

    class PlayersConverter {

        @TypeConverter
        fun stringToPlayers(value: String): List<Long> {
            val listPlayers = object : TypeToken<Long>() {}.type
            return Gson().fromJson(value, listPlayers)
        }

        @TypeConverter
        fun playersToString(list: List<Long>): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }


    class Converter {

        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }
    }

    @Dao
    interface UserDao {

        /* PLAYER */

        @Insert
        fun insertPlayer(player: Player) : Long

        @Insert
        fun insertPlayers(players: List<Player>) : List<Long>

        @Insert
        fun insertPlayers(vararg players: Player)


        @Query("SELECT * FROM Player")
        fun getAllPlayer(): List<Player>

        @Query("SELECT * FROM Player WHERE id=:playerId")
        fun getPlayer(playerId: Long) : Player

        @Insert
        fun insertPlayer(player: Coach) : Long
    }

    @Query("SELECT sum(scoreTeam1) AS scoreT1, sum(scoreTeam2) AS scoreT2
            FROM Score WHERE Score.matchId=:matchId
            GROUP BY Score.matchId")
        fun getEndOfMatchScore(matchId: Long) : Cursor



}









}
}