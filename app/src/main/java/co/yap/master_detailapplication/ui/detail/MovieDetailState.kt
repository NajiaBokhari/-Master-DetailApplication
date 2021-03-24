package co.yap.master_detailapplication.ui.detail

import androidx.databinding.ObservableField
import co.yap.master_detailapplication.base.BaseState
import co.yap.master_detailapplication.networking.models.Movie
import co.yap.master_detailapplication.networking.models.MoviesList

class MovieDetailState : BaseState(), MovieDetailInterface.State {
    override var movies: ObservableField<MoviesList> = ObservableField()
    override var movie: ObservableField<Movie> = ObservableField()

}