package co.yap.master_detailapplication.ui.adapters

import androidx.databinding.ViewDataBinding
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingRecyclerAdapter
 import co.yap.master_detailapplication.databinding.ItemMovieYearHeaderBinding
import co.yap.master_detailapplication.networking.models.Movie

class MoviesSearchHeaderAdapter (
    private val list: MutableList<Movie>
) : BaseBindingRecyclerAdapter<Movie, MoviesItemYearViewHolder>(
    list
) {
      var moviesSearchedTitlesAdapter: MoviesSearchedTitlesAdapter = MoviesSearchedTitlesAdapter(mutableListOf())

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.item_movie_year_header

    override fun onCreateViewHolder(binding: ViewDataBinding): MoviesItemYearViewHolder {
//        moviesSearchedTitlesAdapter.setList(Movie.data)

        return MoviesItemYearViewHolder(
            binding as ItemMovieYearHeaderBinding
        )
    }

    override fun onBindViewHolder(holder: MoviesItemYearViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun filterItem(constraint: CharSequence?, item: Movie): Boolean {
        val filterString = constraint.toString().toLowerCase()
        val movieName = item.year?.toLowerCase()
        if (movieName != null) {
            return movieName.contains(filterString)
        }
        return false
    }
}
