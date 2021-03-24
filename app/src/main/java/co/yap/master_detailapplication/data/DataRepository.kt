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

        fun initializeDB(context: Context): MoviesDatabase {
            return MoviesDatabase.getDataseClient(context)
        }

        fun insertData(
            context: Context,
            username: String,
            password: String,
            title: String,
            year: String,
            cast: String,
            genre: String,
            poster: String,
            rating: Double
        ) {

            moviesDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails =
                    Movies(username, password, title, year, cast, genre, poster, rating)
                moviesDatabase?.loginDao()?.InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String): LiveData<Movies>? {
            Movies = moviesDatabase?.loginDao()?.getMovieDetails(username)
            return Movies
        }
    }
}