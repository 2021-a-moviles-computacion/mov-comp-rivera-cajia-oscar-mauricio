import android.arch.persistence.room.*

@Entity(foreignKeys = [ForeignKey(entity = manufacturer::class,
    parentColumns = arrayOf("manufacturerId"),
    childColumns = arrayOf("manufacturer_id"))])
    //onDelete = ForeignKey.NO_ACTION))
    class Model {
    @PrimaryKey
    val modelId = 0
    val nameModel: String? = null

    @ColumnInfo(name = "manufacturer_id")
    val manufacter_id = 0
}




