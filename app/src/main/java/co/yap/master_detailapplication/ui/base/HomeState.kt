package co.yap.master_detailapplication.ui.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import co.yap.master_detailapplication.base.BaseState

class HomeState : BaseState(), HomeInterface.State {

    override var title: ObservableField<String> = ObservableField()
    override var toolbarBackIcon: ObservableBoolean = ObservableBoolean(true)
    override var toolbarRateIcon: ObservableBoolean = ObservableBoolean(true)
    override var toolbarAddWalletIcon: ObservableBoolean = ObservableBoolean(true)
    override var isPrimaryWallet: MutableLiveData<Boolean> = MutableLiveData()
}
