@Entity(indices = [Index(value = ["first_name", "last_name"], unique = true)])
class User {
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    @Ignore
    var picture: Bitmap? = null
}