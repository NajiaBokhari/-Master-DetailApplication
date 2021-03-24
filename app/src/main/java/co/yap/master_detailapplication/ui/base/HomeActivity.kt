package co.yap.master_detailapplication.ui.base

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import co.yap.master_detailapplication.R
import co.yap.master_detailapplication.base.BaseActivity

class HomeActivity : BaseActivity<HomeInterface.ViewModel>(),
    HomeInterface.View {

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_movies_home

    override val viewModel: HomeInterface.ViewModel
        get() = ViewModelProviders.of(this).get(HomeViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}