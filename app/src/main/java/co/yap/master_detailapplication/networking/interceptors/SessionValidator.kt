package co.yap.master_detailapplication.networking.intercepters

import okhttp3.Interceptor
import okhttp3.Response

internal abstract class SessionValidator :  Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)

        if (response.code == 401) {
            val builder =
                request.newBuilder()
                    .method(request.method, request.body)
            response = chain.proceed(builder.build())

        }
        return response
    }
}