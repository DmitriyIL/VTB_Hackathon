package xyz.tusion.vtb_hackathon.api


import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor : Interceptor {

    private val mLoggingInterceptor: Interceptor

    init {
        mLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return mLoggingInterceptor.intercept(chain)
    }
}
