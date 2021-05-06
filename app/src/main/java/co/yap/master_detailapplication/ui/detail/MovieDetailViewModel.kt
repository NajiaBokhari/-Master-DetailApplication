package co.yap.master_detailapplication.ui.detail

import android.app.Application
import androidx.databinding.ObservableField
import co.yap.master_detailapplication.base.BaseViewModel
import co.yap.master_detailapplication.networking.interfaces.IRepositoryHolder
import co.yap.master_detailapplication.networking.models.Movie
import co.yap.master_detailapplication.networking.repository.AuthRepository
import co.yap.master_detailapplication.ui.detail.adapters.MovieDetailsAdapter

class MovieDetailViewModel(application: Application) :
    BaseViewModel<MovieDetailInterface.State>(application),
    MovieDetailInterface.ViewModel, IRepositoryHolder<AuthRepository> {

    override val repository: AuthRepository = AuthRepository

    override val state: MovieDetailInterface.State = MovieDetailState()

    override var castAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), true)

    override var genreAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), false)

    override fun onCreate() {
        super.onCreate()

      state.movie.get()?.genre?.let {genreAdapter.setList(it) }
      state.movie.get()?.cast?.let {castAdapter.setList(it) }
    }


    override fun getMoviesRequest() {
    }
}