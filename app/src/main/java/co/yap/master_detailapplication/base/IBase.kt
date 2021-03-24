package co.yap.master_detailapplication.base

import android.content.Context
import co.yap.master_detailapplication.networking.NetworkConnectionManager

interface IBase {

    interface View<V : ViewModel<*>> : NetworkConnectionManager.OnNetworkStateChangeListener {
        val viewModel: V
        fun showLoader(isVisible: Boolean)
        fun showToast(msg: String)
        fun showInternetSnack(isVisible: Boolean)
    }

    interface ViewModel<S : State> : ILifecycle {
        val state: S
        val context: Context
    }

    interface State {
        var toast: String
        var loading: Boolean
        var toolbarTitle: String
        var error: String
        fun reset()
        fun destroy()
        fun init()
        fun resume()
        fun pause()
    }

}