package xyz.tusion.vtb_hackathon.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.tusion.vtb_hackathon.model.fts.ReceiptDetailsResponse

interface FtsApiRestService {

    //Также обязательно указать хотя бы пустые заголовки device-id и device-os
    @GET("inns/*/kkts/*/fss/{fn}/tickets/{i}")
    fun getReceiptDetails(
        @Path("fn") fiscalStorageNumber: String,
        @Path("i") fiscalReceiptNumber: Long,
        @Query("fiscalSign") fiscalAttribute: String,
        @Query("sendToEmail") sendToEmail: String = "no",
        @Header("device-id") deviceId: String = "",
        @Header("device-os") deviceOs: String = "",
        @Header("Authorization") auth: String
    ): Single<ReceiptDetailsResponse>

    @GET("ofds/*/inns/*/fss/{fn}/operations/{n}/tickets/{i}")
    fun checkReceipt(
        @Path("fn") fiscalStorageNumber: String,
        @Path("n") paymentIndication: Int,
        @Path("i") fiscalReceiptNumber: Long,
        @Query("fiscalSign") fiscalAttribute: String,
        @Query("date") date: String,
        @Query("sum") sum: Int,
        @Header("device-id") deviceId: String = "",
        @Header("device-os") deviceOs: String = "",
        @Header("Authorization") auth: String
    ): Completable
}