package xyz.tusion.vtb_hackathon.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentIntegrator.forSupportFragment
import xyz.tusion.vtb_hackathon.R

class ScanQrFragment : Fragment() {
    companion object {
        const val SCAN_QR_CONTENT_CODE = "SCAN_QR_CONTENT_CODE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.act_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forSupportFragment(this as Fragment)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setPrompt("Scan QR code")
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            findNavController().navigate(
                R.id.action_scanQrFragment_to_checkDetailsFragment,
                Bundle().apply {
                    putString(SCAN_QR_CONTENT_CODE, result.contents)
                }
            )
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
