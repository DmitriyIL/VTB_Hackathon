package xyz.tusion.vtb_hackathon.api

import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.tusion.vtb_hackathon.model.back.RoomToGet
import xyz.tusion.vtb_hackathon.model.back.RoomToSend

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
}
