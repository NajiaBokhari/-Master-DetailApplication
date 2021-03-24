package co.yap.master_detailapplication.ui.adapters

import androidx.databinding.ViewDataBinding
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.yap.master_detailapplication.databinding.ItemMovieBinding
import co.yap.master_detailapplication.networking.models.Movie

class MoviesAdapter(
    private val list: MutableList<Movie>
) : BaseBindingRecyclerAdapter<Movie, MoviesItemViewHolder>(
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

    override fun filterItem(constraint: CharSequence?, item: Movie): Boolean {
        val filterString = constraint.toString().toLowerCase()
        val currencyName = item.title?.toLowerCase()
        if (currencyName != null) {
            return currencyName.contains(filterString)
        }
            return false
    }
}
