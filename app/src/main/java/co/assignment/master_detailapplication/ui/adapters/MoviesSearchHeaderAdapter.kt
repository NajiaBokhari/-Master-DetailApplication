package co.assignment.master_detailapplication.ui.adapters

import android.widget.Filter
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import co.assignment.master_detailapplication.R
import co.assignment.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.databinding.ItemMovieSearchedTitleBinding
import co.assignment.master_detailapplication.databinding.ItemMovieYearHeaderBinding


class MoviesSearchHeaderAdapter(
        private var moviesList: MutableList<Movies>
) : BaseBindingRecyclerAdapter<Movies, RecyclerView.ViewHolder>(moviesList) {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }
      var showError: ObservableField<Boolean> = ObservableField(false)

    var filter: ItemFilter = ItemFilter()
    private var filteredMoviesList: MutableList<Movies> = moviesList

    override fun getLayoutIdForViewType(viewType: Int): Int = if (viewType == VIEW_TYPE_ONE) R.layout.item_movie_year_header else R.layout.item_movie_searched_title

    override fun onCreateViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder {
        return if (binding is ItemMovieYearHeaderBinding) {
            MoviesItemYearViewHolder(binding)
        } else {
            MoviesItemTitleViewHolder(binding as ItemMovieSearchedTitleBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (holder is MoviesItemYearViewHolder) {
            holder.onBind(moviesList[position], position, onItemClickListener)
        } else {
            if (holder is MoviesItemTitleViewHolder)
                holder.onBind(moviesList[position], position, onItemClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (moviesList[position].title.isNullOrEmpty()) VIEW_TYPE_ONE else VIEW_TYPE_TWO
    }

    override fun getItemCount(): Int {
        if (moviesList.size == 0) showError.set(true) else showError.set(false)
        return moviesList.size
    }


    override fun getDataForPosition(position: Int): Movies {
        return moviesList[position]
    }

    inner class ItemFilter() : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val charString = constraint.toString()
            if (charString.isEmpty() || charString.isBlank()) {
                moviesList = filteredMoviesList
            } else {
                moviesList = filteredMoviesList
                val filteredList = java.util.ArrayList<Movies>()
                for (movies in moviesList) {
                    if (movies.title.toLowerCase().contains(charString) || movies.title.toLowerCase().startsWith(charString)) {
                        filteredList.add(movies)
                    }
                }
                moviesList = filteredList

            }

            val filterResults = Filter.FilterResults()
            filterResults.values = moviesList

            return filterResults
        }

        override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
            if (results?.values != null && charSequence.toString().isNotEmpty() && charSequence.toString().isNotBlank()) {
                val searchResults = segregateViews(results?.values as MutableList<Movies>)
                moviesList = searchResults
                notifyDataSetChanged()

            } else {
                moviesList = filteredMoviesList
                notifyDataSetChanged()
            }
        }

        fun segregateViews(searchedList: MutableList<Movies>): MutableList<Movies> {
            var segregatedSearchedList: MutableList<Movies> = mutableListOf()
            var hashSetObject = HashSet<Int>()
            var moviesByYear: List<Movies>? = searchedList.sortedBy {
                it.year
            }
            searchedList.indices.forEach {
                moviesByYear?.get(it)?.let { it1 -> hashSetObject.add(it1.year) }
            }
            hashSetObject.sorted().forEach { year ->
                segregatedSearchedList.add(Movies("", year, emptyList(), emptyList(), "", 0F))
                segregatedSearchedList.distinct()
                var movies: List<Movies> = (searchedList.filter { it.year == year })
                movies = (movies.sortedByDescending { sortedMovie ->
                    sortedMovie.rating
                }).take(5)
                var moviesMutableList: MutableList<Movies> = movies as MutableList<Movies>
                segregatedSearchedList.addAll(moviesMutableList.distinct())
            }
            segregatedSearchedList.distinct()
            return segregatedSearchedList
        }
    }
}
