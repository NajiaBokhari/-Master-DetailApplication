package co.yap.master_detailapplication.ui.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import co.yap.master_detailapplication.base.IBase

class HomeInterface {
    interface View : IBase.View<ViewModel>

    interface ViewModel : IBase.ViewModel<State>

    interface State : IBase.State {
        var title: ObservableField<String>
        var toolbarBackIcon: ObservableBoolean
        var toolbarRateIcon: ObservableBoolean
        var toolbarAddWalletIcon: ObservableBoolean
        var isPrimaryWallet: MutableLiveData<Boolean>
    }
}