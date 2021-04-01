package co.yap.master_detailapplication

import android.app.Application
import co.yap.master_detailapplication.networking.NetworkConnectionManager
import co.yap.master_detailapplication.networking.RetroNetwork
import co.yap.master_detailapplication.networking.interfaces.NetworkConstraintsListener

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetroNetwork.initWith(this, BuildConfig.BASE_URL)
        NetworkConnectionManager.init(this)

        RetroNetwork.listenNetworkConstraints(object : NetworkConstraintsListener {
            override fun onInternetUnavailable() {
            }

            override fun onCacheUnavailable() {
            }

            override fun onSessionInvalid() {

            }
        })
    }
}