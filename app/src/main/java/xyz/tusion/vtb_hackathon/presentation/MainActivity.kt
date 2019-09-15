package xyz.tusion.vtb_hackathon.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import xyz.tusion.vtb_hackathon.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("test", " onViewCreated MainActivity")
    setContentView(R.layout.act_main)


  }
}
