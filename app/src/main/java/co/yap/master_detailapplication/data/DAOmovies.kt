package co.yap.master_detailapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOmovies {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(moviesTableModel: Movies)


    @Query("SELECT * FROM movies WHERE year =:any")
    fun getMoviesList(any: String?): LiveData<List<Movies>>

    @Query("SELECT * FROM movies WHERE year =:any")
    fun getMovieDetails(any: String?): LiveData<Movies>

//    @Query("SELECT * FROM movies ORDER BY year DESC ")

}