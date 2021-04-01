package co.yap.master_detailapplication.ui.home

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.yap.master_detailapplication.base.IBase
import co.yap.master_detailapplication.base.SingleClickEvent
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.networking.models.MoviesList
import co.yap.master_detailapplication.ui.adapters.MoviesAdapter
import co.yap.master_detailapplication.ui.adapters.MoviesSearchHeaderAdapter

interface MoviesInterface {

    interface View : IBase.View<ViewModel> {
        fun setObservers()
    }

    interface ViewModel : IBase.ViewModel<State> {
        var searchQuery: MutableLiveData<String>
        var moviesAdapter: MoviesAdapter
        var moviesSearchHeaderAdapter: MoviesSearchHeaderAdapter
        var moviesList: MoviesList
        val MOVIE_ITEM_CLICK: Int
        val clickEvent: SingleClickEvent
        var movieLiveData: LiveData<Movies>?
        var movieListLiveData: LiveData<List<Movies>>?
        fun getMoviesRequest()
        fun getAllMoviesFromDB(any: String): LiveData<List<Movies>>?
        fun getMovieDetails(any: String): LiveData<Movies>?
        fun insertData(
                context: Context,
                title: String,
                year: String,
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