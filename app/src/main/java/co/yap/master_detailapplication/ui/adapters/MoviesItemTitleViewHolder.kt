package co.yap.master_detailapplication.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.databinding.ItemMovieSearchedTitleBinding

class MoviesItemTitleViewHolder  (
    private val ItemMovieSearchedTitleBinding: ItemMovieSearchedTitleBinding
) :
    RecyclerView.ViewHolder(ItemMovieSearchedTitleBinding.root) {
    fun onBind(
            movie: Movies,
            position: Int,
            onItemClickListener: OnItemClickListener?
    ) {
        ItemMovieSearchedTitleBinding.viewModel =
            MoviesAdapterViewModel(movie, position, onItemClickListener)
        ItemMovieSearchedTitleBinding.executePendingBindings()
    }
}