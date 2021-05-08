package co.assignment.master_detailapplication.ui.detail

import androidx.databinding.ObservableField
import co.assignment.master_detailapplication.base.BaseState
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.networking.models.MoviesList

class MovieDetailState : BaseState(), MovieDetailInterface.State {
    override var movies: ObservableField<MoviesList> = ObservableField()
    override var movie: ObservableField<Movies> = ObservableField()

}