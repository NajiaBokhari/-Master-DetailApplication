package co.yap.master_detailapplication.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.databinding.ItemMovieBinding

class MoviesItemViewHolder(
    private val itemMovieBinding: ItemMovieBinding
) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun onBind(
        movie: Movies,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        itemMovieBinding.viewModel =
            MoviesAdapterViewModel(movie, position, onItemClickListener)
        itemMovieBinding.executePendingBindings()
    }
}
