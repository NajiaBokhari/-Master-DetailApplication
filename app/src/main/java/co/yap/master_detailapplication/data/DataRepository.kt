package co.yap.master_detailapplication.data

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DataRepository {

    companion object {

        var moviesDatabase: MoviesDatabase? = null

        var Movies: LiveData<Movies>? = null

        var MoviesList: LiveData<List<Movies>>? = null

        fun initializeDB(context: Context): MoviesDatabase {
            return MoviesDatabase.getDataseClient(context)
        }

        fun insertData(
            context: Context,
            title: String,
            year: Int,
            cast: List<String>,
            genre: List<String>,
            poster: String,
            rating: Float
        ) {

            moviesDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val movieDetails =
                    Movies(title, year, cast, genre, poster, rating)
                moviesDatabase?.daOmovies()?.InsertData(movieDetails)
            }
        }

        fun getMovieDetails(any: String): LiveData<Movies>? {
            Movies = moviesDatabase?.daOmovies()?.getMovieDetails(any)
            return Movies
        }

        fun getAllMoviesList(year: Int): LiveData<List<Movies>>? {
            MoviesList = moviesDatabase?.daOmovies()?.getMoviesList(year)
            return MoviesList
        }

        fun getAllSortedMoviesList(): LiveData<List<Movies>>? {
            MoviesList = moviesDatabase?.daOmovies()?.getSortedMoviesList()
            return MoviesList
        }
    }
}