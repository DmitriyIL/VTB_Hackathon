package xyz.tusion.vtb_hackathon.model.fts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ReceiptDetailsResponse(
    var document: Document? = null
) : Parcelable

@Parcelize
class Document(
    var receipt: Receipt? = null
) : Parcelable

@Parcelize
class Receipt(
    var operationType: Int? = null,
    var fiscalSign: Long? = null,
    var dateTime: String? = null,
    var rawData: String? = null,
    var totalSum: Int? = null,
    var nds10: Int? = null,
    var userInn: String? = null,
    var taxationType: Int? = null,
    var operator: String? = null,
    var fiscalDocumentNumber: Int? = null,
    var receiptCode: Int? = null,
    var requestNumber: Int? = null,
    var user: String? = null,
    var kktRegId: String? = null,
    var fiscalDriveNumber: String? = null,
    var items: List<Commodity>? = null,
    var ecashTotalSum: Int? = null,
    var retailPlaceAddress: String? = null,
    var cashTotalSum: Int? = null,
    var shiftNumber: Int? = null
) : Parcelable

@Parcelize
class Commodity(
    var sum: Int? = null,
    var quantity: Double? = null,
    var price: Int? = null,
    var name: String? = null,
    var occupiedQuantity: Int = 0
) : Parcelable



