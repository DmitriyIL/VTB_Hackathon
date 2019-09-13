package xyz.tusion.vtb_hackathon.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import xyz.tusion.vtb_hackathon.R
import xyz.tusion.vtb_hackathon.model.*
import xyz.tusion.vtb_hackathon.presentation.ScanQrFragment.Companion.SCAN_QR_CONTENT_CODE
import xyz.tusion.vtb_hackathon.repositories.CheckRepository


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        content_main_btn_qr_scan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_scanQrFragment)
        }
    }
}
