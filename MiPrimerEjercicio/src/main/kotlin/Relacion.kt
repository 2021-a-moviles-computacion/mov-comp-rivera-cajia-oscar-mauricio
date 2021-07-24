import CodePivot.User
import android.arch.persistence.room.*


class Relacion {
    data class FabricanteConModelos(
        @Embedded val Fabricante: Fabricante,
        @Relation(
            parentColumn = "fabricanteId",
            entityColumn = "fabricanteCreatorId"
        )
        val Modelos: List<Modelo>
    )

    @Entity(foreignKeys = ForeignKey(   entity = User::class,
                                        parentColumns = "id",
                                        childColumns = "model_id"))
    class Model {
        @PrimaryKey
        var modelId = 0
        var nameModel: String? = null

        @ColumnInfo(name = "model_id")
        var manufacturerId = 0
    }

}