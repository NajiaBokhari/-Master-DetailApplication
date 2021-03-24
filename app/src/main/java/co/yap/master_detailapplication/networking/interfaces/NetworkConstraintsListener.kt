package co.yap.master_detailapplication.networking.interfaces

interface NetworkConstraintsListener {

    fun onInternetUnavailable()
    fun onCacheUnavailable()
    fun onSessionInvalid()

    companion object {
        val DEFAULT = object : NetworkConstraintsListener {
            override fun onInternetUnavailable() {

            }

            override fun onCacheUnavailable() {

            }

            override fun onSessionInvalid() {

            }
        }
    }
}