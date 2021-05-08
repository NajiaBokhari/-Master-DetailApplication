package co.assignment.master_detailapplication.ui.home

import androidx.databinding.ObservableField
import co.assignment.master_detailapplication.base.BaseState
import co.assignment.master_detailapplication.networking.models.MoviesList
import co.assignment.master_detailapplication.ui.home.MoviesInterface

class MoviesState : BaseState(), MoviesInterface.State {
    override var movies:ObservableField<MoviesList>  = ObservableField()



}