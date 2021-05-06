package co.yap.master_detailapplication.ui.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import co.yap.master_detailapplication.base.BaseState

class HomeState : BaseState(), HomeInterface.State {

    override var title: ObservableField<String> = ObservableField()
    override var toolbarBackIcon: ObservableBoolean = ObservableBoolean(true)
    override var toolbarRateIcon: ObservableBoolean = ObservableBoolean(true)
    override var toolbarIcon: ObservableBoolean = ObservableBoolean(true)
}
