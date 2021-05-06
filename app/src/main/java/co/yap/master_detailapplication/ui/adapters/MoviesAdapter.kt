package co.yap.master_detailapplication.ui.adapters

import androidx.databinding.ViewDataBinding
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.databinding.ItemMovieBinding

class MoviesAdapter(
    private val list: MutableList<Movies>
) : BaseBindingRecyclerAdapter<Movies, MoviesItemViewHolder>(
    list
) {

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.item_movie

    override fun onCreateViewHolder(binding: ViewDataBinding): MoviesItemViewHolder {
        return MoviesItemViewHolder(
            binding as ItemMovieBinding
        )
    }

    override fun onBindViewHolder(holder: MoviesItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }
//
//    override fun filterItem(constraint: CharSequence?, item: Movies): Boolean {
//        val filterString = constraint.toString().toLowerCase()
//        val title = item.title?.toLowerCase()
//        if (title != null) {
//            return title.contains(filterString)
//        }
//            return false
//    }
}
