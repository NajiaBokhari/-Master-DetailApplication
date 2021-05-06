package co.yap.master_detailapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingFragment
import co.yap.master_detailapplication.base.OnItemClickListener
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.utils.SharedPreferencesHelper
import com.test.androidcodechallenge.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesHomeFragment : BaseBindingFragment<MoviesInterface.ViewModel>(),
        MoviesInterface.View {

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_movies

    override val viewModel: MoviesInterface.ViewModel
        get() = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        viewModel.moviesAdapter.setItemListener(movieItemClickListener)
        viewModel.moviesAdapter.allowFullItemClickListener = true
        viewModel.moviesSearchHeaderAdapter.setItemListener(movieItemClickListener)
        viewModel.moviesSearchHeaderAdapter.allowFullItemClickListener = true
//        viewModel.moviesAdapter.setItemListener(movieItemClickListener)
//        viewModel.moviesAdapter.allowFullItemClickListener = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
    }

    override fun setObservers() {
        viewModel.searchQuery.observe(this, Observer {
//            viewModel.moviesAdapter.filter.filter(it)
            viewModel.moviesSearchHeaderAdapter.filter.filter(it)
        })
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
        svMovies.isIconified = false
        svMovies.setIconifiedByDefault(false)
        svMovies.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchQuery.value = query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchQuery.value = newText
                return true
            }
        })
    }

    override fun onStart() {
        super.onStart()
        loadMoviesList()
    }

    private fun loadMoviesList() {
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
                    viewModel.sortedMovieListLiveData?.add(Movies("", movieAtIndex, emptyList(), emptyList(), "", 0F))

                    activity?.let { viewModel.getAllMoviesFromDB(movieAtIndex) }!!.observe(this, Observer { movies ->

                        viewModel.sortedMovieListLiveData?.addAll(movies.sortedByDescending { sortedMovie -> sortedMovie.rating }.take(5))
                        viewModel.sortedMovieListLiveData?.sortBy { sortedMovie ->
                            sortedMovie.year
                        }

                        val sortedMovies = viewModel.sortedMovieListLiveData
                        sortedMovies.sortBy { sortedMovie ->
                            sortedMovie.year
                        }
                        SharedPreferencesHelper.putList(sortedMovies)

                        if (index == hashSetObject.size - 1) {

                            viewModel.moviesSearchHeaderAdapter.setList(SharedPreferencesHelper.getList().distinct())
                        }
                    })
                }
            })

        } else {
            viewModel.moviesSearchHeaderAdapter.setList(SharedPreferencesHelper.getList().distinct())
        }
    }


    override fun onDestroy() {
        viewModel.clickEvent.removeObservers(this)
        super.onDestroy()
    }

}