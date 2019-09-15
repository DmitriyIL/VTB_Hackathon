package xyz.tusion.vtb_hackathon.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.tusion.vtb_hackathon.BuildConfig

object BackApiFactory {

    private var sClient: OkHttpClient? = null

    @Volatile
    private var sService: JsonService? = null

    val jsonService: JsonService
        get() {
            var service = sService
            if (service == null) {
                synchronized(BackApiFactory::class.java) {
                    service = sService
                    if (service == null) {
                        sService = buildRetrofit().create(JsonService::class.java)
                        service = sService
                    }
                }
            }
            return service!!
        }

    private val client: OkHttpClient?
        get() {
            var client = sClient
            if (client == null) {
                synchronized(BackApiFactory::class.java) {
                    client = sClient
                    if (client == null) {
                        sClient = buildClient()
                        client = sClient
                    }
                }
            }
            return client
        }

    fun recreate() {
        sClient = null
        sClient = client
        sService = buildRetrofit().create(JsonService::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(client!!)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()
    }

}