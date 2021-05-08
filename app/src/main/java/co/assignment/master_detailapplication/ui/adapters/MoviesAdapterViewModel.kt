package co.assignment.master_detailapplication.ui.adapters

import android.view.View
import co.assignment.master_detailapplication.base.OnItemClickListener
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.networking.models.Movie

class MoviesAdapterViewModel(
    val movie: Movies,
    val position: Int,
    private val onItemClickListener: OnItemClickListener?) {
    fun onViewClicked(view: View) {
        onItemClickListener?.onItemClick(view, movie, position)
    }
}