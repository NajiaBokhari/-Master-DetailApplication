package co.assignment.master_detailapplication.ui.home

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.assignment.master_detailapplication.base.IBase
import co.assignment.master_detailapplication.base.SingleClickEvent
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.ui.adapters.MoviesSearchHeaderAdapter

interface MoviesInterface {

    interface ViewModel : IBase.ViewModel<State> {
        var searchQuery: MutableLiveData<String>
        var moviesSearchHeaderAdapter: MoviesSearchHeaderAdapter
        var moviesList: MoviesList
        val MOVIE_ITEM_CLICK: Int
        val clickEvent: SingleClickEvent
        var movieLiveData: LiveData<Movies>?
        var sortedMovieListLiveData: ArrayList<Movies>
        var movieListLiveData: LiveData<List<Movies>>?
        fun getMoviesRequest()
        fun getAllSortedMoviesFromDB(): LiveData<List<Movies>>?
        fun getAllMoviesFromDB(year: Int): LiveData<List<Movies>>?
        fun getMovieDetails(any: String): LiveData<Movies>?

        fun insertData(
                context: Context,
                title: String,
                year: Int,
                cast: ArrayList<String>,
                genre: ArrayList<String>,
                poster: String,
                rating: Float
        )
    }

    interface State : IBase.State {
        var movies: ObservableField<MoviesList>

    }
}