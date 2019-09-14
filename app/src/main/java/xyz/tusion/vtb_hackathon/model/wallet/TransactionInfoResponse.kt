package xyz.tusion.vtb_hackathon.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TransactionInfoResponse(
    var timestamp: String,
    var message: String,
    var data: TransactionInfoData
): Parcelable

@Parcelize
class TransactionInfoData(
    var number: String,
    var currencyCode: Int,
    var amount: Double,
    var description: String,
    var recipient: String,
    var payer: String,
    var state: Int,
    var created: Long,
    var updated: Long,
    var owner: String,
    var errorCode: Int
): Parcelable