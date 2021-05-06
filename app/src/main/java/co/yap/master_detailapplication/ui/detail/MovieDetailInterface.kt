package co.yap.master_detailapplication.ui.detail

import androidx.databinding.ObservableField
import co.yap.master_detailapplication.base.IBase
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.networking.models.Movie
import co.yap.master_detailapplication.networking.models.MoviesList
import co.yap.master_detailapplication.ui.adapters.AdapterItemType
import co.yap.master_detailapplication.ui.detail.adapters.MovieDetailsAdapter

interface MovieDetailInterface {

    interface View : IBase.View<ViewModel>

    interface ViewModel : IBase.ViewModel<State> {
        var castAdapter: MovieDetailsAdapter
        var genreAdapter: MovieDetailsAdapter
        var flickerAdapter: MovieDetailsAdapter
        fun getMoviesRequest()
    }

    interface State : IBase.State {
        var movies: ObservableField<MoviesList>
        var movie: ObservableField<Movies>
    }
}