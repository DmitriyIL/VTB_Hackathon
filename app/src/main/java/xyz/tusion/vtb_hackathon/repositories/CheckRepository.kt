package xyz.tusion.vtb_hackathon.repositories

import android.util.Base64
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.tusion.vtb_hackathon.api.FtsApiFactory
import xyz.tusion.vtb_hackathon.model.QrReceipt
import xyz.tusion.vtb_hackathon.model.fts.Commodity
import java.io.UnsupportedEncodingException
import java.util.concurrent.TimeUnit

object CheckRepository {
    private fun requestCommodities(
        qrReceipt: QrReceipt
    ): Single<List<Commodity>> = FtsApiFactory.jsonService
        .getReceiptDetails(
            qrReceipt.fiscalStorageNumber,
            qrReceipt.fiscalReceiptNumber,
            qrReceipt.fiscalAttribute,
            auth = getAuthHeader()
        )
        .map { it.document?.receipt?.items }

    private fun isReceiptExists(
        qrReceipt: QrReceipt
    ): Completable = FtsApiFactory.jsonService
        .checkReceipt(
            qrReceipt.fiscalStorageNumber,
            qrReceipt.paymentIndication,
            qrReceipt.fiscalReceiptNumber,
            qrReceipt.fiscalAttribute,
            qrReceipt.date,
            qrReceipt.sum,
            auth = getAuthHeader()
        )

    private fun getAuthHeader(): String {
        var auth = String.format("%s:%s", "+79604786259", "792949")
        var data: ByteArray? = null
        try {
            data = auth.toByteArray(charset("UTF-8"))
        } catch (e1: UnsupportedEncodingException) {
            e1.printStackTrace()
        }

        auth = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "")

        return "Basic $auth"
    }

    private var checkDetailsRequestAmt: Int = 0

    fun getCommodities(qrReceipt: QrReceipt): Single<List<Commodity>> =
        Single.create { subscriber ->
            fun makeOneMoreCommoditiesRequest(qrReceipt: QrReceipt) {
                requestCommodities(qrReceipt)
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (++checkDetailsRequestAmt < 5)
                            if (it.isNullOrEmpty())
                                makeOneMoreCommoditiesRequest(qrReceipt)
                            else
                                subscriber.onSuccess(it)
                        else {
                            checkDetailsRequestAmt = 0
                            subscriber.onError(Exception())
                        }
                    }, {
                        if (++checkDetailsRequestAmt < 5)
                            makeOneMoreCommoditiesRequest(qrReceipt)
                        else {
                            checkDetailsRequestAmt = 0
                            subscriber.onError(Exception())
                        }
                        Log.v("CHECK", it.message)
                    })
            }

            isReceiptExists(qrReceipt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    makeOneMoreCommoditiesRequest(qrReceipt)
                }, {
                    Log.v("CHECK_NOT_EXISTS", it.message)
                    subscriber.onError(it)
                })
        }


}