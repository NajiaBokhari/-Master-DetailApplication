package co.yap.master_detailapplication.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

interface ILifecycle : LifecycleObserver {
    fun onCreate()
    fun onStart()
    fun onStop()
    fun onDestroy()
    fun onResume()
    fun onPause()
    fun registerLifecycleOwner(owner: LifecycleOwner?)
    fun unregisterLifecycleOwner(owner: LifecycleOwner?)
}
