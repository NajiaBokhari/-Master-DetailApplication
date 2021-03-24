package co.yap.master_detailapplication.networking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

object NetworkConnectionManager {
    interface OnNetworkStateChangeListener {
        fun onNetworkStateChanged(isConnected: Boolean)
    }

    private var listeners: ArrayList<OnNetworkStateChangeListener> = arrayListOf()
    private var isRegistered: Boolean = false

    fun init(context: Context) {
        if (!isRegistered) {
            isRegistered = true
            context.applicationContext.registerReceiver(
                receiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }

    fun destroy(context: Context) {
        unsubscribeAll()
        if (isRegistered) {
            context.applicationContext.unregisterReceiver(receiver)
            isRegistered = false
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            handleConnectivityChange(context)
        }
    }

    private fun handleConnectivityChange(context: Context) {
        val isConnected = isNetworkAvailable(context)
        for (listener in listeners) {
            listener.onNetworkStateChanged(isConnected)
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            if (connectivityManager.activeNetworkInfo != null) {
                return connectivityManager.activeNetworkInfo.isConnected
            }
        }
        return false
    }

    fun subscribe(listener: OnNetworkStateChangeListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    private fun unsubscribeAll() {
        listeners.clear()
    }

    fun unsubscribe(listener: OnNetworkStateChangeListener) {
        listeners.remove(listener)
    }
}