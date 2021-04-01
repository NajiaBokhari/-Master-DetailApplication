package co.yap.master_detailapplication.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.databinding.ItemMovieSearchedTitleBinding
import co.yap.master_detailapplication.networking.models.Movie

class MoviesItemTitleViewHolder  (
    private val ItemMovieSearchedTitleBinding: ItemMovieSearchedTitleBinding
) :
    RecyclerView.ViewHolder(ItemMovieSearchedTitleBinding.root) {
    fun onBind(
        movie: Movie,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        ItemMovieSearchedTitleBinding.viewModel =
            MoviesAdapterViewModel(movie, position, onItemClickListener)
        ItemMovieSearchedTitleBinding.executePendingBindings()
    }
}