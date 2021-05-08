package co.assignment.master_detailapplication.networking

import android.app.Application
import android.content.Context
import android.os.Environment
import co.assignment.master_detailapplication.networking.intercepters.NetworkConstraintsInterceptor
import co.assignment.master_detailapplication.networking.intercepters.SessionValidator
import co.assignment.master_detailapplication.networking.interfaces.Network
import co.assignment.master_detailapplication.networking.interfaces.NetworkConstraintsListener
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetroNetwork : Network {
    private const val READ_TIMEOUT_SECONDS = 600L
    private const val CONNECTION_TIMEOUT_SECONDS = 60L
    private const val DISK_CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10 MB

    private var retro: Retrofit? = null
    private var networkConstraintsListener: NetworkConstraintsListener? = null
        get() {
            if (field == null) field = NetworkConstraintsListener.DEFAULT
            return field
        }

    override fun initWith(application: Application, baseUrl: String) {
        build(application, baseUrl)
    }

    @Throws(IllegalStateException::class)
    override fun <T> createService(serviceInterface: Class<T>): T {
        if (retro == null) throw IllegalStateException("RetroNetwork is not initialised. Make sure you have called 'initWith' before calling this function ")
        return retro?.create(serviceInterface)!!
    }

    private fun build(context: Context, baseUrl: String): Retrofit {
        if (retro == null) {
            retro = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient(context)).build()
        }
        return retro!!
    }

    private fun buildOkHttpClient(context: Context): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(getCache())
            .addInterceptor(logger)
            .addInterceptor(object : NetworkConstraintsInterceptor(context) {
                override fun onInternetUnavailable() {
                    networkConstraintsListener?.onInternetUnavailable()
                }

                override fun onCacheUnavailable() {
                    networkConstraintsListener?.onCacheUnavailable()
                }
            })
            .build()
    }

    fun listenNetworkConstraints(listener: NetworkConstraintsListener) {
        this.networkConstraintsListener = listener
    }

    private fun getCache(): Cache {
        val cacheDir = File(Environment.getDataDirectory(), "cache")
        return Cache(cacheDir, DISK_CACHE_SIZE)
    }

}