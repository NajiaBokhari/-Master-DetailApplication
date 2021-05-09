package co.assignment.master_detailapplication.ui.detail

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import co.assignment.master_detailapplication.base.IBase
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.ui.detail.adapters.MovieDetailsAdapter

interface MovieDetailInterface {

    interface View : IBase.View<ViewModel>

    interface ViewModel : IBase.ViewModel<State> {
        var castAdapter: MovieDetailsAdapter
        var genreAdapter: MovieDetailsAdapter
        var flickerAdapter: MovieDetailsAdapter
    }

    interface State : IBase.State {
        var movies: ObservableField<MoviesList>
        var movie: ObservableField<Movies>
        var flickerPhotos: ObservableArrayList<String>
    }
}