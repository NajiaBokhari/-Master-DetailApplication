package co.yap.master_detailapplication.ui.adapters

import android.view.View
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.networking.models.Movie

class MoviesAdapterViewModel(
    val movie: Movie,
    val position: Int,
    private val onItemClickListener: OnItemClickListener?
) {
    fun onViewClicked(view: View) {
        onItemClickListener?.onItemClick(view, movie, position)
    }
}