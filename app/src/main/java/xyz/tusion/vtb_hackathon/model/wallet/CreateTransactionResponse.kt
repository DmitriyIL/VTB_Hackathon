package xyz.tusion.vtb_hackathon.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CreateTransactionResponse(
    var timestamp: String,
    var message: String,
    var data: CreateTransactionResponseData
) : Parcelable


@Parcelize
class CreateTransactionResponseData(
    var txId: String,
    var result: String
) : Parcelable
