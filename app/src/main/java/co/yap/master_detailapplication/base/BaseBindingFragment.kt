package co.yap.master_detailapplication.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.databinding.library.baseAdapters.BR


abstract class BaseBindingFragment<V : IBase.ViewModel<*>> :  Fragment(), IBase.View<V>/*,
    OnBackPressedListener*/ {
    private var progress: Dialog? = null
    lateinit var viewDataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
//        context?.let { progress = Utils.createProgressDialog(it) }
    }

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
        //viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
        registerStateListeners()

    }

    abstract fun getBindingVariable(): Int



    @LayoutRes
    abstract fun getLayoutId(): Int



    override fun onDestroyView() {
        unregisterStateListeners()
        progress?.dismiss()
        super.onDestroyView()
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is IFragmentHolder) {
//            context.onFragmentAttached()
//        } else {
//            throw IllegalStateException("Could not find reference to IFragmentHolder. Make sure parent activity implements IFragmentHolder interface")
//        }
//
//        if (context !is IBase.View<*>) {
//            throw IllegalStateException("Could not find reference to IBase.View. Make sure parent activity implements IBase.View interface")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
//        if (getFragmentHolder() != null) {
//            getFragmentHolder()?.onFragmentDetached("")
//        }
    }


    override fun showLoader(isVisible: Boolean) {
        if (isVisible) {
            if (isResumed && userVisibleHint) {
                if (progress == null) {
//                    context?.let { progress = Utils.createProgressDialog(it) }
                    progress?.show()
                } else {
                    progress?.show()
                }
            }
        } else {
            progress?.dismiss()
        }
//        Utils.hideKeyboard(this.view)
        //getBaseView()?.showLoader(isVisible)
    }

    override fun showToast(msg: String) {
        getBaseView()?.showToast(msg)
    }

//    private fun getFragmentHolder(): IFragmentHolder? {
//        if (context is IFragmentHolder) {
//            return context as IFragmentHolder
//        }
//
//        return null
//    }

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



     fun onBackPressed(): Boolean = false

    private val stateObserver = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            if (propertyId == BR.toast && viewModel.state.toast.isNotBlank()) {
                showToast(viewModel.state.toast)
            }
            if (propertyId == BR.loading) {
                showLoader(viewModel.state.loading)
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
}
