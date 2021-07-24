import android.arch.persistence.room.*

@Dao
interface manufacturerDAO {
    /*@Query("SELECT * FROM manufacturer")
    List<manufacturer> getAll()*/
    @Query("SELECT * FROM manufacturer")
    fun getAllPlayer(): List<manufacturer>

    /*
    fun save(name: String, email: String) {
        val id = lastId.incrementAndGet()
        users.put(id, User(name = name, email = email, id = id))
    }


    @Query("SELECT * FROM manufacturer WHERE manufacturerId IN (:userIds)")
    fun loadAllByIds(int[] userIds): List<manufacturer>*/
/*
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM Song")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>*/

}
	   