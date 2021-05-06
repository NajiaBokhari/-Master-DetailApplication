package co.yap.master_detailapplication.ui.adapters

import android.widget.Filter
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.databinding.ItemMovieSearchedTitleBinding
import co.yap.master_detailapplication.databinding.ItemMovieYearHeaderBinding


class MoviesSearchHeaderAdapter(
        list: MutableList<Movies>
) : BaseBindingRecyclerAdapter<Movies, RecyclerView.ViewHolder>(list) {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    var filterCount = MutableLiveData<Int>()

    var list: ArrayList<Movies> = list as ArrayList<Movies>
    var filter: ItemFilter = ItemFilter(list)
    private var duplicate: MutableList<Movies> = list


    override fun getLayoutIdForViewType(viewType: Int): Int =
            if (viewType == VIEW_TYPE_ONE) R.layout.item_movie_year_header else R.layout.item_movie_searched_title


    override fun onCreateViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder {

        return if (binding is ItemMovieYearHeaderBinding) {
            MoviesItemYearViewHolder(binding as ItemMovieYearHeaderBinding)
        } else {
            MoviesItemTitleViewHolder(binding as ItemMovieSearchedTitleBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (holder is MoviesItemYearViewHolder) {
            (holder as MoviesItemYearViewHolder).onBind(list[position], position, onItemClickListener)
        } else {
            if (holder is MoviesItemTitleViewHolder)
                (holder as MoviesItemTitleViewHolder).onBind(list[position], position, onItemClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].title.isNullOrEmpty()) VIEW_TYPE_ONE else VIEW_TYPE_TWO
    }


    fun filterItem(constraint: CharSequence?, item: Movies): Boolean {
        val filterString = constraint.toString().toLowerCase()
        val title = item.title?.toLowerCase()
        if (title != null) {
            return title.contains(filterString)
        }
        return false
    }

    inner class ItemFilter(private val dataList: MutableList<Movies>) : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()
            val list = mutableListOf<Movies>()
            list.addAll(duplicate)

            val count = list.size
            val nlist = ArrayList<Movies>(count)

            if (!constraint.isNullOrEmpty() && !constraint.isNullOrBlank()) {
                for (i in 0 until count) {
                    if (filterItem(constraint, list[i])) {
                        nlist.add(list[i])
                    }
                }
            } else {
                nlist.addAll(list)
            }

            results.values = nlist
            results.count = nlist.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
          if (results?.values != null)  {
              list.clear()

            filterCount.value = results?.count
            val searchResults = segregateViews(results?.values as MutableList<Movies>)
            list.addAll(searchResults.distinct())
            notifyDataSetChanged()
        }else{

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
            hashSetObject.sorted().forEachIndexed { index, year ->
                segregatedSearchedList.add(Movies("", year, emptyList(), emptyList(), "", 0F))

                var movies: List<Movies> = (searchedList.filter { it.year == year }).take(5)
                var moviesMutableList: MutableList<Movies> = movies as MutableList<Movies>
                segregatedSearchedList.addAll(moviesMutableList)
            }
            segregatedSearchedList.sortBy { sortedMovie ->
                sortedMovie.year
            }
            segregatedSearchedList.distinct()
            return segregatedSearchedList
        }

    }


}