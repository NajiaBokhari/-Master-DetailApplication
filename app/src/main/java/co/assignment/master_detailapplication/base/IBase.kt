package co.assignment.master_detailapplication.base

import android.content.Context
import co.assignment.master_detailapplication.networking.NetworkConnectionManager

interface IBase {

    interface View<V : ViewModel<*>> : NetworkConnectionManager.OnNetworkStateChangeListener {
        val viewModel: V
        fun showToast(msg: String)
        fun showInternetSnack(isVisible: Boolean)
    }

    interface ViewModel<S : State> : ILifecycle {
        val state: S
        val context: Context
    }

    interface State {
        var toast: String
        var error: String
        fun destroy()
        fun init()
        fun resume()
        fun pause()
    }

}