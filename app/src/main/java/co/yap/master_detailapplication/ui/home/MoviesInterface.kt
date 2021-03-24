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

interface MoviesInterface {

    interface View : IBase.View<ViewModel> {
        fun setObservers()
    }

    interface ViewModel : IBase.ViewModel<State> {
        var searchQuery: MutableLiveData<String>
        var moviesAdapter: MoviesAdapter
        var moviesList: MoviesList
        val MOVIE_ITEM_CLICK: Int
        val clickEvent: SingleClickEvent
        var liveDataLogin: LiveData<Movies>?
        fun getMoviesRequest()
        fun getLoginDetails(context: Context, username: String): LiveData<Movies>?
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
        )

    }

    interface State : IBase.State {
        var movies: ObservableField<MoviesList>

    }
}