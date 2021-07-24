class MyApp : Application() {

    companion object {
        var database: MyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        MyApp.database = Room.databaseBuilder(this, MyDatabase::class.java, "championship-db").build()
    }
}