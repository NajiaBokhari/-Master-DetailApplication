package co.yap.master_detailapplication.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.databinding.ItemMovieYearHeaderBinding

class MoviesItemYearViewHolder (
    private val ItemMovieYearHeaderBinding: ItemMovieYearHeaderBinding
) :
    RecyclerView.ViewHolder(ItemMovieYearHeaderBinding.root) {
    fun onBind(
            movie: Movies,
            position: Int,
            onItemClickListener: OnItemClickListener?
    ) {
        ItemMovieYearHeaderBinding.viewModel =
            MoviesAdapterViewModel(movie, position, onItemClickListener)
        ItemMovieYearHeaderBinding.executePendingBindings()
    }

    fun getYear(year:Int){
        year.toString()
    }
}
