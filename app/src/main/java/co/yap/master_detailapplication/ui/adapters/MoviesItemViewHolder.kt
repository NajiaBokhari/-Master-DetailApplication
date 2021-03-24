package co.yap.master_detailapplication.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.databinding.ItemMovieBinding
import co.yap.master_detailapplication.networking.models.Movie

class MoviesItemViewHolder(
    private val itemMovieBinding: ItemMovieBinding
) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun onBind(
        movie: Movie,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        itemMovieBinding.viewModel =
            MoviesAdapterViewModel(movie, position, onItemClickListener)
        itemMovieBinding.executePendingBindings()
    }
}
