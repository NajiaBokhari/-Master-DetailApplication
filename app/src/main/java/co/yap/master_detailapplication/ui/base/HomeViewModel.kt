package co.yap.master_detailapplication.ui.base

import android.app.Application
import co.yap.master_detailapplication.base.BaseViewModel

class HomeViewModel(application: Application) :
        BaseViewModel<HomeInterface.State>(application),
        HomeInterface.ViewModel {
    override val state: HomeInterface.State = HomeState()
}