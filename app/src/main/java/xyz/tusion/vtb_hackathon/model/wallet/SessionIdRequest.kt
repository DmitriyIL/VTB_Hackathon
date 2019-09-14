package xyz.tusion.vtb_hackathon.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SessionIdRequest(
    var addresses: IntArray? = IntArray(0),
    var deviceId: String? = "test_device_id",
    var deviceType: Int? = 1
): Parcelable