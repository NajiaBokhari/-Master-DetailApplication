package co.assignment.master_detailapplication.ui.detail.adapters

import androidx.recyclerview.widget.RecyclerView
import co.assignment.master_detailapplication.databinding.ItemCastBinding
import co.assignment.master_detailapplication.ui.adapters.AdapterItemType

class MovieDetailsItemViewHolder(
    private val itemMovieBinding: ItemCastBinding
) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun onBind(
        String: String,
        position: Int,
        adapterItemType: AdapterItemType
    ) {
        itemMovieBinding.viewModel =
            MovieDetailsItemViewModel(String, position, adapterItemType.name)
        itemMovieBinding.executePendingBindings()
    }
}
