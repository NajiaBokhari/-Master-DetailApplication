package co.yap.master_detailapplication.ui.detail

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseBindingFragment
import co.yap.master_detailapplication.data.Movies
import co.yap.master_detailapplication.networking.models.Movie

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