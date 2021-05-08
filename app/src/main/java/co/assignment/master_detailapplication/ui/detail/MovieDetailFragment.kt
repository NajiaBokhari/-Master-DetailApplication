package co.assignment.master_detailapplication.ui.detail

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import co.assignment.master_detailapplication.R
import co.assignment.master_detailapplication.base.BaseBindingFragment
import co.assignment.master_detailapplication.data.Movies

class MovieDetailFragment : BaseBindingFragment<MovieDetailInterface.ViewModel>(),
        MovieDetailInterface.View {

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.fragment_movie_detail

    override val viewModel: MovieDetailInterface.ViewModel
        get() = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.movie.set(arguments?.let { MovieDetailFragmentArgs.fromBundle(it).movies as Movies })
    }
}