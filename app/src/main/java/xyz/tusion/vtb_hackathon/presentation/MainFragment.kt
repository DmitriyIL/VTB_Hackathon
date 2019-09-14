package xyz.tusion.vtb_hackathon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.content_main.*
import xyz.tusion.vtb_hackathon.R


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    val sessionId = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        content_main_btn_qr_scan.setOnClickListener {
            /*WalletRepository.getSessionId()
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
                }, {})*/
            findNavController().navigate(R.id.action_mainFragment_to_scanQrFragment)
        }
    }
}
