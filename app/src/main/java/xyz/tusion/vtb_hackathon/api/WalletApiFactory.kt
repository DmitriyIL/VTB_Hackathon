package xyz.tusion.vtb_hackathon.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WalletApiFactory {

    private var sClient: OkHttpClient? = null

    @Volatile
    private var sService: WalletApiRestService? = null

    val jsonService: WalletApiRestService
        get() {
            var service = sService
            if (service == null) {
                synchronized(WalletApiRestService::class.java) {
                    service = sService
                    if (service == null) {
                        sService = buildRetrofit().create(WalletApiRestService::class.java)
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
                synchronized(FtsApiFactory::class.java) {
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
        sService = buildRetrofit().create(WalletApiRestService::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("http://89.208.84.235:31080/api/v1/")
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