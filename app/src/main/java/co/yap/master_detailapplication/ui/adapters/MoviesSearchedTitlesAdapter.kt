package co.yap.master_detailapplication.ui.adapters

import androidx.databinding.ViewDataBinding
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.yap.master_detailapplication.databinding.ItemMovieSearchedTitleBinding
 import co.yap.master_detailapplication.networking.models.Movie

class MoviesSearchedTitlesAdapter (
    private val list: MutableList<Movie>
) : BaseBindingRecyclerAdapter<Movie, MoviesItemTitleViewHolder>(
    list
) {

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.item_movie_searched_title

    override fun onCreateViewHolder(binding: ViewDataBinding): MoviesItemTitleViewHolder {
        return MoviesItemTitleViewHolder(
            binding as ItemMovieSearchedTitleBinding
        )
    }

    override fun onBindViewHolder(holder: MoviesItemTitleViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun filterItem(constraint: CharSequence?, item: Movie): Boolean {

        return false
    }
}
