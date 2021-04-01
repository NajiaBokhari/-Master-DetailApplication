package co.yap.master_detailapplication.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.databinding.ItemMovieYearHeaderBinding
import co.yap.master_detailapplication.networking.models.Movie

class MoviesItemYearViewHolder (
    private val ItemMovieYearHeaderBinding: ItemMovieYearHeaderBinding
) :
    RecyclerView.ViewHolder(ItemMovieYearHeaderBinding.root) {
    fun onBind(
        movie: Movie,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        ItemMovieYearHeaderBinding.viewModel =
            MoviesAdapterViewModel(movie, position, onItemClickListener)
        ItemMovieYearHeaderBinding.executePendingBindings()
    }
}
