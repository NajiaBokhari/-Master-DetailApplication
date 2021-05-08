package co.assignment.master_detailapplication.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import co.assignment.master_detailapplication.R
import co.assignment.master_detailapplication.networking.NetworkConnectionManager
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<V : IBase.ViewModel<*>> : AppCompatActivity(), IBase.View<V>,
        NetworkConnectionManager.OnNetworkStateChangeListener {
    lateinit var viewDataBinding: ViewDataBinding

    private var snackbar: Snackbar? = null

    private var DURATION_CODE = -2

    private var checkConnectivity: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        NetworkConnectionManager.init(this)
        NetworkConnectionManager.subscribe(this)
        registerStateListeners()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    fun hideKeyboard() = hideKeyboard(this.currentFocus)

    override fun showToast(msg: String) {
        if ("" != msg.trim { it <= ' ' }) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }

    override fun onNetworkStateChanged(isConnected: Boolean) {
        if (checkConnectivity && isConnected) {
            checkConnectivity = false

        } else {
            showInternetSnack(!isConnected)
            checkConnectivity = false
        }
    }

    override fun showInternetSnack(isVisible: Boolean) {
        if (isVisible) showNoInternetSnackBar() else showInternetConnectedSnackBar()
    }

    private fun showNoInternetSnackBar() {
        snackbar = setSnackBar(
                this,
                getString(R.string.common_display_text_error_no_internet),
                Snackbar.LENGTH_INDEFINITE
        )
                .setAction(
                        "Settings"
                ) { startActivity(Intent(Settings.ACTION_WIFI_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
                .setActionTextColor(applicationContext.resources.getColor(R.color.colorAccent))
        snackbar?.show()
    }

    private fun showInternetConnectedSnackBar() {
        val snackbarConnected = setSnackBar(
                this,
                "Internet connected.",
                Snackbar.LENGTH_SHORT
        )
        snackbarConnected.show()
        snackbar?.dismiss()
    }

    private fun setSnackBar(activity: Activity, message: String, duration: Int): Snackbar {
        val layout: View
        val snackbar = Snackbar
                .make(activity.findViewById(android.R.id.content), message, duration)
        layout = snackbar.view
        layout.setBackgroundColor(activity.resources.getColor(R.color.colorAccent))
        val text =
                layout.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        text.setTextColor(Color.WHITE)

        if (duration == DURATION_CODE) {
            layout.setBackgroundColor(activity.resources.getColor(R.color.colorAccent))
            val snackbarView = snackbar.view
            val textView =
                    snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
        }
        return snackbar
    }

    override fun onDestroy() {
        NetworkConnectionManager.unsubscribe(this)
        unregisterStateListeners()
        super.onDestroy()
    }

    private val stateObserver = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            if (propertyId == BR.toast && viewModel.state.toast.isNotBlank()) {
                showToast(viewModel.state.toast)
            }
        }
    }

    private fun registerStateListeners() {
        if (viewModel is BaseViewModel<*>) {
            viewModel.registerLifecycleOwner(this)
        }
        if (viewModel.state is BaseState) {
            (viewModel.state as BaseState).addOnPropertyChangedCallback(stateObserver)
        }
    }

    private fun unregisterStateListeners() {
        if (viewModel is BaseViewModel<*>) {
            viewModel.unregisterLifecycleOwner(this)
        }
        if (viewModel.state is BaseState) {
            (viewModel.state as BaseState).removeOnPropertyChangedCallback(stateObserver)
        }
    }

    fun hideKeyboard(view: View?) {
        view?.let { v ->
            val imm =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}