package co.yap.master_detailapplication.ui.detail

import android.app.Application
import co.yap.master_detailapplication.base.BaseViewModel
import co.yap.master_detailapplication.networking.interfaces.IRepositoryHolder
import co.yap.master_detailapplication.networking.repository.AuthRepository
import co.yap.master_detailapplication.ui.adapters.AdapterItemType
import co.yap.master_detailapplication.ui.detail.adapters.MovieDetailsAdapter

class MovieDetailViewModel(application: Application) :
    BaseViewModel<MovieDetailInterface.State>(application),
    MovieDetailInterface.ViewModel, IRepositoryHolder<AuthRepository> {

    override val repository: AuthRepository = AuthRepository

    override val state: MovieDetailInterface.State = MovieDetailState()

    override var castAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(),   AdapterItemType.ITEM_CAST_TYPE)

    override var genreAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(),  AdapterItemType.ITEM_GENRE_TYPE)

    override var flickerAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(),  AdapterItemType.ITEM_IMAGE_TYPE)


    override fun onCreate() {
        super.onCreate()

      state.movie.get()?.genre?.let {genreAdapter.setList(it) }
      state.movie.get()?.cast?.let {castAdapter.setList(it) }
    }


    override fun getMoviesRequest() {
    }
}