package xyz.tusion.vtb_hackathon.model.back

class RoomToSend(count_price: Int, count_users: Int, name: String?){


    var count_users: Int = 0
    var count_price: Int = 0

    var name: String? = null

    init {
        this.count_users = count_users
        this.count_price = count_price
        this.name = name
    }
}