package xyz.tusion.vtb_hackathon.api

import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*
import xyz.tusion.vtb_hackathon.model.back.RoomToGet

interface JsonService {
    @POST("createroom")
    fun sendRoomData(@Body param: JsonObject): Completable


    @POST("createroom")
    fun sendRoomDataNew(
        @Query("count_users") usersCount: Int,
        @Query("count_price") countPrice: Int,
        @Query("name") name: String?
    ): Observable<Long>

    @GET("createroom")
    fun getRoomData(): Observable<RoomToGet>

    @GET("room/gethash")
    fun getWalletByPhone(
        @Query("phone") phone: String,
        @Header("Content-Type") ct: String = "UTF-8"
    ): Single<String>
}
