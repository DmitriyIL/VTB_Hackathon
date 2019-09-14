package xyz.tusion.vtb_hackathon.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CreateTransactionRequest(
    var amount: Int = 0,
    var currencyCode: Int = 810,
    var description: String = "test description",
    var number: String = "123123123123",
    var payer: String = "",
    var recipient: String = ""
) : Parcelable