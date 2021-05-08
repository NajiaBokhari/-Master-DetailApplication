package co.assignment.master_detailapplication.ui.base

import androidx.databinding.ObservableField
import co.assignment.master_detailapplication.base.IBase

class HomeInterface {
    interface View : IBase.View<ViewModel>

    interface ViewModel : IBase.ViewModel<State>

    interface State : IBase.State {
        var title: ObservableField<String>
    }
}