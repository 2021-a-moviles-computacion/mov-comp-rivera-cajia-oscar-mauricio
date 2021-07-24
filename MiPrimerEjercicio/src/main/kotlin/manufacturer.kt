import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class manufacturer {
    //@PrimaryKey
    @PrimaryKey(autoGenerate = true)
    val manufacturerId = 0

    //The attribute is set with a different name, the field corresponds to the column of the table, using @ColumnInfonameAttributes.
    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null
}