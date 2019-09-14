package xyz.tusion.vtb_hackathon.api

import io.reactivex.Single
import retrofit2.http.*
import xyz.tusion.vtb_hackathon.model.wallet.*

interface WalletApiRestService {

    @POST("session")
    fun createSessionId(@Body params: SessionIdRequest): Single<SessionIdResponse>

    @POST("invoice")
    fun createTransaction(
        @Body params: CreateTransactionRequest,
        @Header("FPSID") fpsid: String
    ): Single<CreateTransactionResponse>

    @GET("invoice/{currencyCode}/{number}/{recipient}")
    fun getTransactionInfo(
        @Path("currencyCode") currencyCode: Int,
        @Path("number") number: String,
        @Path("recipient") recipient: String,
        @Header("FPSID") fpsid: String
    ): Single<TransactionInfoResponse>
}