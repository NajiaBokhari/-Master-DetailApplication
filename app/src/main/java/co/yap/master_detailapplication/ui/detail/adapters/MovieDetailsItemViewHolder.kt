package co.yap.master_detailapplication.ui.detail.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.databinding.ItemCastBinding

class MovieDetailsItemViewHolder(
    private val itemMovieBinding: ItemCastBinding
) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun onBind(
        String: String,
        position: Int,
        castsList: Boolean
    ) {
        itemMovieBinding.viewModel =
            MovieDetailsItemViewModel(String, position, castsList)
        itemMovieBinding.executePendingBindings()
    }
}
