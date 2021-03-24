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
import co.yap.master_detailapplication.networking.models.Movie
import com.android.tools.build.jetifier.core.utils.Log
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
        activity?.let { viewModel.getLoginDetails(it, "Username 1") }!!.observe(this, Observer {
            Log.i("MoviesHomeFragment", it.Password)
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
    }

    private val movieItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            val action =
                MoviesHomeFragmentDirections.actionMoviesHomeFragmentToMovieDetailFragment(
                    data as Movie
                )
            findNavController().navigate(action)
        }

    }


    override fun setObservers() {
        viewModel.searchQuery.observe(this, Observer {
            viewModel.moviesAdapter.filter.filter(it)
        })
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


    override fun onDestroy() {
        viewModel.clickEvent.removeObservers(this)
        super.onDestroy()
    }

}