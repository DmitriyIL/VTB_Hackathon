package xyz.tusion.vtb_hackathon.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SessionIdResponse(
    var timestamp: String? = null,
    var message: String? = null,
    var data: String? = null
) : Parcelable