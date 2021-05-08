package co.assignment.master_detailapplication.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment


abstract class BaseBindingFragment<V : IBase.ViewModel<*>> : Fragment(), IBase.View<V> {
    lateinit var viewDataBinding: ViewDataBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
        registerStateListeners()
    }

    abstract fun getBindingVariable(): Int


    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onDestroyView() {
        unregisterStateListeners()
        super.onDestroyView()
    }

    override fun showToast(msg: String) {
        getBaseView()?.showToast(msg)
    }

    private fun getBaseView(): IBase.View<*>? {
        if (context is IBase.View<*>) {
            return context as IBase.View<*>
        }

        return null
    }

    override fun showInternetSnack(isVisible: Boolean) {
        getBaseView()?.showInternetSnack(isVisible)
    }

    override fun onNetworkStateChanged(isConnected: Boolean) {
        getBaseView()?.onNetworkStateChanged(isConnected)
    }

    fun onBackPressed(): Boolean = true

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
