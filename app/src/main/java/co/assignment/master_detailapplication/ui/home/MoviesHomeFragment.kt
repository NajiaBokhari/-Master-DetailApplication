package co.assignment.master_detailapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import co.assignment.master_detailapplication.R
import co.assignment.master_detailapplication.base.BaseBindingFragment
import co.assignment.master_detailapplication.base.OnItemClickListener
import co.assignment.master_detailapplication.data.Movies
import co.assignment.master_detailapplication.utils.SharedPreferencesHelper
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesHomeFragment : BaseBindingFragment<MoviesInterface.ViewModel>() {

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_movies

    override val viewModel: MoviesInterface.ViewModel
        get() = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.moviesSearchHeaderAdapter.setItemListener(movieItemClickListener)
        viewModel.moviesSearchHeaderAdapter.allowFullItemClickListener = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
     }

    private val movieItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            val action =
                    MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMovieDetailFragment(
                            data as Movies
                    )
            findNavController().navigate(action)
        }
    }


    private fun setSearchView() {
        svMovies.isFocusableInTouchMode
        svMovies.setIconifiedByDefault(false)
        svMovies.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchQuery.value = query
                viewModel.moviesSearchHeaderAdapter.filter.filter(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchQuery.value = newText
                viewModel.moviesSearchHeaderAdapter.filter.filter(newText)
                return true
            }
        })
        hideKeyboard(svMovies)
        svMovies.clearFocus()
    }

    override fun onStart() {
        super.onStart()
        loadMoviesList()

    }

    override fun onResume() {
        super.onResume()
        hideKeyboard(svMovies)
        svMovies.clearFocus()
    }

    private fun loadMoviesList() {
         progressBar.setVisibility(View.VISIBLE)
        if (!SharedPreferencesHelper.firstRun) {
            SharedPreferencesHelper.firstRun = true

            var hashSetObject = HashSet<Int>()
            activity?.let { viewModel.getAllSortedMoviesFromDB() }!!.observe(this, Observer {
                var moviesByYear: List<Movies>? = it.sortedBy {
                    it.year
                }
                it.indices.forEach {
                    moviesByYear?.get(it)?.let { it1 -> hashSetObject.add(it1.year) }
                }
                hashSetObject.sorted().forEachIndexed { index, movieAtIndex ->
                    viewModel.sortedMovieListLiveData?.add(
                            Movies(
                                    "",
                                    movieAtIndex,
                                    emptyList(),
                                    emptyList(),
                                    "",
                                    0F
                            )
                    )

                    activity?.let { viewModel.getAllMoviesFromDB(movieAtIndex) }!!
                            .observe(this, Observer { movies ->
                                viewModel.sortedMovieListLiveData?.addAll(movies)
                                viewModel.sortedMovieListLiveData?.sortBy { sortedMovie ->
                                    sortedMovie.year
                                }

                                SharedPreferencesHelper.putList(viewModel.sortedMovieListLiveData)

                                if (index == hashSetObject.size - 1) {

                                    viewModel.moviesSearchHeaderAdapter.setList(
                                            SharedPreferencesHelper.getList().distinct()
                                    )
                                    progressBar.setVisibility(View.GONE)

                                }
                            })
                }
            })

        } else {
            viewModel.moviesSearchHeaderAdapter.setList(
                    SharedPreferencesHelper.getList().distinct()
            )
            progressBar.setVisibility(View.GONE)
        }
    }


    override fun onDestroy() {
        viewModel.clickEvent.removeObservers(this)
        super.onDestroy()
    }
}