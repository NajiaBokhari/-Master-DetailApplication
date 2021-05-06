package co.yap.master_detailapplication.base

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR


abstract class BaseState : BaseObservable(), IBase.State {


    @get:Bindable
    override var error: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.error)
        }

    @get:Bindable
    override var toast: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.toast)
        }

    override fun destroy() {

    }

    override fun init() {

    }

    override fun resume() {

    }

    override fun pause() {

    }

}