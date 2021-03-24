package co.yap.master_detailapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertData(moviesTableModel: Movies)

    @Query("SELECT * FROM movies WHERE Username =:username")
    fun getMovieDetails(username: String?) : LiveData<Movies>

}