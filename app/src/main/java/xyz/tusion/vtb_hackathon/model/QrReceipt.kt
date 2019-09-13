package xyz.tusion.vtb_hackathon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat

@Parcelize
data class QrReceipt(
    val paymentIndication: Int,
    val codeQR: String,
    val date: String,
    val sum: Int,
    val fiscalAttribute: String,
    val fiscalReceiptNumber: Long,
    val fiscalStorageNumber: String,
    val manual: String? = "AUTO"
) : Parcelable

class ReceiptInvalidException(message: String) : Exception(message)

const val DEFAULT_QR_DATE_PATTERN = "yyyyMMdd'T'HHmm"

fun parceQrReceipt(qrCode: String): QrReceipt {
    // t=<Дата и время оформления кассового чека, в формате ГГГГММДДТЧЧММ>
    // &
    // s=<Сумма расчета, в рублях и копейках, разделенных точкой>
    // &
    // fn=<Номер фискального накопителя>
    // &
    // i=<Номер фискального документа, нулями не дополняется>
    // &
    // fp=<Фискальный признак, нулями не дополняется>
    // &
    // n=<Признак расчета>
    val parts = qrCode.split("""&""")
    if (parts.size < 6) throw ReceiptInvalidException("Receipt doesn't have all demanded fields")
    try {
        /**
         * Date validation before request
         * if date is invalid throw exception and show error
         */
        val t = SimpleDateFormat(DEFAULT_QR_DATE_PATTERN).parse(parts[0].substring(2))
        val s = parts[1].substring(2).toFloatOrNull()
            ?: throw ReceiptInvalidException("Receipt has error in cost")
        val fn = parts[2].substring(3)
        val i = parts[3].substring(2).toLongOrNull()
            ?: throw ReceiptInvalidException("Receipt has error in fiscalReceiptNumber")
        val fp = parts[4].substring(3)
        val n = parts[5].substring(2).toIntOrNull()
            ?: throw ReceiptInvalidException("Receipt has error in paymentIndication")
        return QrReceipt(
            paymentIndication = n,
            codeQR = qrCode,
            date = parts[0].substring(2),
            sum = (s * 100).toInt(),
            fiscalAttribute = fp,
            fiscalReceiptNumber = i,
            fiscalStorageNumber = fn
        )
    } catch (e: ParseException) {
        throw ReceiptInvalidException("Receipt has error in date")
    }
}