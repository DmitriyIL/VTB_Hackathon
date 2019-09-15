package xyz.tusion.vtb_hackathon

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {
    const val WALLET: String = "my wallert"
    val sp: SharedPreferences = VtbApp.appContext!!.getSharedPreferences("xyz.tusion.vtb_hackathon", Context.MODE_PRIVATE)

    fun saveWallet(wallet: String) {
        sp.edit().putString(WALLET, wallet)
    }

    fun getWallet() = sp.getString(WALLET, "")

}