import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

    @Entity
    data class Fabricante(
        //@PrimaryKey(autoGenerate = true)
        @PrimaryKey val fabricanteId: Long,
        val nameFab: String,
        val anioFab: Int
    )
