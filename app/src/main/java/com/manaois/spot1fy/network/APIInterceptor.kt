package com.manaois.spot1fy.network

import com.manaois.spot1fy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class APIInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().toString()
        val newUrl = url.replace("_API_KEY_", BuildConfig.API_KEY)

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}