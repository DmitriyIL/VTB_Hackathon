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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        content_main_btn_qr_scan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_scanQrFragment)
        }
    }
}
