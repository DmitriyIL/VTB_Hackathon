package xyz.tusion.vtb_hackathon.repositories

import io.reactivex.Single
import xyz.tusion.vtb_hackathon.api.WalletApiFactory
import xyz.tusion.vtb_hackathon.model.wallet.CreateTransactionRequest
import xyz.tusion.vtb_hackathon.model.wallet.SessionIdRequest
import xyz.tusion.vtb_hackathon.model.wallet.TransactionInfoResponse

object WalletRepository {
    fun getSessionId(
        sessionIdRequest: SessionIdRequest = SessionIdRequest()
    ): Single<String> {
        return WalletApiFactory
            .jsonService
            .createSessionId(sessionIdRequest)
            .map { it.data }
    }

    fun createTransactionAndGetId(
        createTransactionRequest: CreateTransactionRequest,
        sessionId: String
    ): Single<String> {
        return WalletApiFactory
            .jsonService
            .createTransaction(createTransactionRequest, sessionId)
            .map { it.data.txId }
    }

    fun getTransactionStatus(
        currencyCode: Int,
        number: String,
        recipient: String,
        sessionId: String
    ): Single<Int> {
        return WalletApiFactory
            .jsonService
            .getTransactionInfo(currencyCode, number, recipient, sessionId)
            .map { it.data.state }
    }
}