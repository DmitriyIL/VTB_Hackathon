package xyz.tusion.vtb_hackathon.repositories

import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.tusion.vtb_hackathon.api.BackApiFactory
import xyz.tusion.vtb_hackathon.model.back.RoomToGet
import xyz.tusion.vtb_hackathon.model.back.RoomToSend

object JsonRepository {
    fun sendRoom(roomToSend: RoomToSend): Observable<Long> = BackApiFactory
        .jsonService
        .sendRoomDataNew(roomToSend.count_users, roomToSend.count_price, roomToSend.name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getRoom(): Observable<RoomToGet> = BackApiFactory
        .jsonService
        .getRoomData()

    fun createRoonJsonObject(roomToSend: RoomToSend): JsonObject {
        var param = JsonObject()
        param.addProperty("count_users", roomToSend.count_users)
        param.addProperty("count_price", roomToSend.count_price)
        param.addProperty("name", roomToSend.name)
        return param
    }


}