package co.assignment.master_detailapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOmovies {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(moviesTableModel: Movies)

    @Query("SELECT * FROM movies WHERE year =:anyYear")
    fun getMoviesList(anyYear: Int): LiveData<List<Movies>>

    @Query("SELECT * FROM movies WHERE year =:anyYear")
    fun getMovieDetails(anyYear: String?): LiveData<Movies>

    @Query("SELECT * FROM movies ORDER BY year ASC")
    fun getSortedMoviesList(): LiveData<List<Movies>>
}