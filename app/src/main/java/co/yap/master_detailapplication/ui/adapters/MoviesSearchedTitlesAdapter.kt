package co.yap.master_detailapplication.ui.adapters

import androidx.databinding.ViewDataBinding
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.databinding.ItemMovieSearchedTitleBinding

class MoviesSearchedTitlesAdapter (
    private val list: MutableList<Movies>
) : BaseBindingRecyclerAdapter<Movies, MoviesItemTitleViewHolder>(
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

//    override fun filterItem(constraint: CharSequence?, item: Movies): Boolean {
//
//        return false
//    }
}
