package xyz.tusion.vtb_hackathon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import xyz.tusion.vtb_hackathon.R
import xyz.tusion.vtb_hackathon.SharedPreferencesUtils
import xyz.tusion.vtb_hackathon.api.BackApiFactory
import xyz.tusion.vtb_hackathon.model.QRCodeGenerator
import xyz.tusion.vtb_hackathon.model.getNormalProfileQrCodeSize

class MyQrFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_my_qr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BackApiFactory.jsonService
            .getWalletByPhone("89604786260")
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                SharedPreferencesUtils.saveWallet(it)
            }, {})

        content_main_btn_get_state.setOnClickListener {
            QRCodeGenerator.generateFromString(
                SharedPreferencesUtils.getWallet()!!,
                getNormalProfileQrCodeSize(requireContext())
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    content_main_iv_qr.setImageBitmap(it)
                }, {})
        }
    }
}
