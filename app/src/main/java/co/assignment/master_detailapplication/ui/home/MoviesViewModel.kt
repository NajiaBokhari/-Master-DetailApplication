package co.assignment.master_detailapplication.ui.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.assignment.master_detailapplication.base.BaseViewModel
import co.assignment.master_detailapplication.base.SingleClickEvent
import co.assignment.master_detailapplication.data.DataRepository
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.networking.interfaces.IRepositoryHolder
import co.assignment.master_detailapplication.networking.models.Movie
import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.networking.models.RetroApiResponse
import co.assignment.master_detailapplication.networking.repository.AuthRepository
import co.assignment.master_detailapplication.ui.adapters.MoviesSearchHeaderAdapter
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class MoviesViewModel(application: Application) :
        BaseViewModel<MoviesInterface.State>(application),
        MoviesInterface.ViewModel, IRepositoryHolder<AuthRepository> {

    override val MOVIE_ITEM_CLICK: Int = 100

    override val repository: AuthRepository = AuthRepository

    override val clickEvent: SingleClickEvent = SingleClickEvent()

    override val state: MoviesInterface.State =
            MoviesState()

    override var moviesSearchHeaderAdapter: MoviesSearchHeaderAdapter =
            MoviesSearchHeaderAdapter(mutableListOf())

    override var moviesList: MoviesList = MoviesList(loadLocalMoviesList())

    override var searchQuery: MutableLiveData<String> = MutableLiveData()

    override var movieLiveData: LiveData<Movies>? = null

    override var movieListLiveData: LiveData<List<Movies>>? = null

    override var sortedMovieListLiveData: ArrayList<Movies> = arrayListOf()


    override fun insertData(
            context: Context,
            title: String,
            year: Int,
            cast: ArrayList<String>,
            genre: ArrayList<String>,
            poster: String,
            rating: Float
    ) {
        DataRepository.insertData(
                context,
                title,
                year,
                cast,
                genre,
                poster,
                rating
        )
    }

    override fun getMovieDetails(any: String): LiveData<Movies>? {
        movieLiveData = DataRepository.getMovieDetails(any)
        return movieLiveData
    }

    override fun getAllMoviesFromDB(year: Int): LiveData<List<Movies>>? {
        movieListLiveData = DataRepository.getAllMoviesList(year)
        return movieListLiveData
    }

    override fun getAllSortedMoviesFromDB(): LiveData<List<Movies>>? {
        movieListLiveData = DataRepository.getAllSortedMoviesList()
        return movieListLiveData
    }

    override fun getMoviesRequest() {

//        launch {
//            when (val response = repository.getMovies()) {
//                is RetroApiResponse.Success -> {
//                    moviesList = response.data
//                    clickEvent.setValue(MOVIE_ITEM_CLICK)
//                }
//
//                is RetroApiResponse.Error -> {
//                    state.error = response.error.message
//                }
//            }
//        }
    }


    fun loadLocalMoviesList(): ArrayList<Movie> {

        var dataList: ArrayList<Movie> = arrayListOf()
        val mainObj = JSONObject(loadTransactionFromJsonAssets(context))
        val mainDataList = mainObj.getJSONArray("movies")
        if (mainDataList != null) {
            for (i in 0 until mainDataList.length()) {
                val parentArrayList = mainDataList.getJSONObject(i)
                val movieTitle: String = parentArrayList.getString("title")
                val movieYear: Int = parentArrayList.getInt("year")
                val movieCast: ArrayList<String> = getCasts(parentArrayList, "cast")
                val movieGenres: ArrayList<String> = getCasts(parentArrayList, "genres")
                val movieRating: Float = parentArrayList.getString("rating").toFloat()

                val movie: Movie = Movie(
                        title = movieTitle,
                        year = movieYear,
                        cast = movieCast,
                        genre = movieGenres,
                        rating = movieRating,
                        poster = ""
                )

                insertData(
                        context,
                        movieTitle,
                        movieYear,
                        movieCast,
                        movieGenres,
                        "poster",
                        movieRating
                )

                dataList.add(movie)

            }
        }

        return dataList
    }


    private fun getCasts(parentArrayList: JSONObject, field: String): ArrayList<String> {
        var castList: ArrayList<String> = arrayListOf()
        if (parentArrayList.getJSONArray(field) != null) {

            for (i in 0 until parentArrayList.getJSONArray(field).length()) {

                castList.add(parentArrayList.getJSONArray(field).get(i) as String)
            }
        }
        return castList
    }

    private fun loadTransactionFromJsonAssets(context: Context): String? {
        val json: String?
        try {
            val readFromAsset = context.assets.open("movies.json")
            val size = readFromAsset.available()
            val buffer = ByteArray(size)
            readFromAsset.read(buffer)
            readFromAsset.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}