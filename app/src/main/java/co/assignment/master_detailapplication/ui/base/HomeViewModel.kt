package co.assignment.master_detailapplication.ui.base

import android.app.Application
import co.assignment.master_detailapplication.base.BaseViewModel

class HomeViewModel(application: Application) :
        BaseViewModel<HomeInterface.State>(application),
        HomeInterface.ViewModel {
    override val state: HomeInterface.State = HomeState()
}