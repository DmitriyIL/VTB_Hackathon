package xyz.tusion.vtb_hackathon.model.back;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomToGet {

    @SerializedName("room_id")
    @Expose
    private Integer roomId;
    @SerializedName("count_users")
    @Expose
    private Integer countUsers;
    @SerializedName("count_price")
    @Expose
    private Integer countPrice;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("usersInRoomList")
    @Expose
    private Object usersInRoomList;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(Integer countUsers) {
        this.countUsers = countUsers;
    }

    public Integer getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(Integer countPrice) {
        this.countPrice = countPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getUsersInRoomList() {
        return usersInRoomList;
    }

    public void setUsersInRoomList(Object usersInRoomList) {
        this.usersInRoomList = usersInRoomList;
    }

}