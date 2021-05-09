package co.assignment.master_detailapplication.ui.detail

import android.app.Application
import co.assignment.master_detailapplication.base.BaseViewModel
import co.assignment.master_detailapplication.networking.interfaces.IRepositoryHolder
import co.assignment.master_detailapplication.networking.models.PhotosSearchResponse
import co.assignment.master_detailapplication.networking.models.RetroApiResponse
import co.assignment.master_detailapplication.networking.repository.AuthRepository
import co.assignment.master_detailapplication.ui.adapters.AdapterItemType
import co.assignment.master_detailapplication.ui.detail.adapters.MovieDetailsAdapter

class MovieDetailViewModel(application: Application) :
        BaseViewModel<MovieDetailInterface.State>(application),
        MovieDetailInterface.ViewModel, IRepositoryHolder<AuthRepository> {

    override val repository: AuthRepository = AuthRepository

    override val state: MovieDetailInterface.State = MovieDetailState()

    override var castAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), AdapterItemType.ITEM_CAST_TYPE)

    override var genreAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), AdapterItemType.ITEM_GENRE_TYPE)

    override var flickerAdapter: MovieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), AdapterItemType.ITEM_IMAGE_TYPE)


    override fun onCreate() {
        super.onCreate()
        state.showLoader.set(true)
        state.movie.get()?.genre?.let { genreAdapter.setList(it) }
        state.movie.get()?.cast?.let { castAdapter.setList(it) }
        state.movie.get()?.title?.let {
            fetchImages(it)
        }
    }

    fun fetchImages(movieTitle: String) {
        var flickerPhotos: ArrayList<String> = arrayListOf()

        var searchResponse: PhotosSearchResponse
        launch {
            when (val response = repository.getMovies(movieTitle)) {
                is RetroApiResponse.Success -> {
                    searchResponse = response.data
                    searchResponse.photos.photo.map { photo ->
                        flickerPhotos.add(
                                "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
                        )
                    }
                    state.flickerPhotos.addAll(flickerPhotos)
                    state.flickerPhotos?.let {
                        flickerAdapter.setList(it)
                        state.showLoader.set(false)

                    }
                }

                is RetroApiResponse.Error -> {
                    state.error = response.error.message
                }
            }
        }
    }
}