package xyz.tusion.vtb_hackathon

import android.app.Application
import android.content.Context
import android.widget.Toast

import io.reactivex.disposables.CompositeDisposable

class VtbApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        //        FirebaseApp.initializeApp(this);
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }

    companion object {

        var appContext: Context? = null
            private set

        var appBinds = CompositeDisposable()

        fun showMessage(message: String) {
            Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}
