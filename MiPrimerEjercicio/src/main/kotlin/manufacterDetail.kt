    @DatabaseView(
        "SELECT user.id, user.name, user.departmentId," +
                "department.name AS departmentName FROM user " +
                "INNER JOIN department ON user.departmentId = department.id"
    )
    internal class UserDetail {
        var id: Long = 0
        var name: String? = null
        var departmentId: Long = 0
        var departmentName: String? = null
    }

    annotation class DatabaseView(val value: String)
