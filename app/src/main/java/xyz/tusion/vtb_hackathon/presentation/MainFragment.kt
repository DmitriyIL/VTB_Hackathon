package xyz.tusion.vtb_hackathon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import xyz.tusion.vtb_hackathon.R
import xyz.tusion.vtb_hackathon.model.back.RoomToSend
import xyz.tusion.vtb_hackathon.model.wallet.CreateTransactionRequest
import xyz.tusion.vtb_hackathon.presentation.standard.LoadingDialog
import xyz.tusion.vtb_hackathon.presentation.standard.LoadingView
import xyz.tusion.vtb_hackathon.repositories.JsonRepository
import xyz.tusion.vtb_hackathon.repositories.WalletRepository
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var dialog: LoadingView
        dialog = LoadingDialog.view(fragmentManager)
        content_main_btn_qr_scan.setOnClickListener {
            WalletRepository.getSessionId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    content_main_tv_qr.text = it
                    val sessionId = it
                    WalletRepository.createTransactionAndGetId(
                        CreateTransactionRequest(
                            amount = 100,
                            number = it,
                            payer = "648a81315307a32bd079533c4f47d258d309869f",
                            recipient = "46f60547e5b77d13468ef49e9fac10c08b66a644"
                        ),
                        it
                    ).delay(10, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            content_main_tv_qr.text = "${content_main_tv_qr.text}\n $it"
                            WalletRepository.getTransactionStatus(
                                810,
                                sessionId,
                                "46f60547e5b77d13468ef49e9fac10c08b66a644",
                                sessionId
                            )
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    content_main_tv_qr.text = "${content_main_tv_qr.text}\n Status: $it"
                                }, {})
                        }, {})
                }, {})
//            JsonRepository
//                .sendRoom(RoomToSend(1150, 3, "NewDefaultRoom"))
//                .doOnSubscribe {dialog.showLoadingIndicator()}
//                .doAfterTerminate {dialog.hideLoadingIndicator()}
//                .subscribe (
//                    this::onSuccess,
//                    this::onError
//                )
//            findNavController().navigate(R.id.action_mainFragment_to_scanQrFragment)
        }
    }

    private fun onError(t: Throwable) {
        println("MESSAGE: " + t.localizedMessage)

    }

    private fun onSuccess(l: Long) {
        Toast.makeText(requireContext(), "room id: $l", Toast.LENGTH_SHORT).show()
    }
}
