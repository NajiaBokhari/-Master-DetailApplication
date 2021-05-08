package co.assignment.master_detailapplication.ui.base

import androidx.databinding.ObservableField
import co.assignment.master_detailapplication.base.BaseState

class HomeState : BaseState(), HomeInterface.State {

    override var title: ObservableField<String> = ObservableField()
}
