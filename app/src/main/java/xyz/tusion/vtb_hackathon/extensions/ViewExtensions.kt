package xyz.tusion.vtb_hackathon.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.Group
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.toVisibleOrGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.toVisibleOrInvisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun View.isInvisible(): Boolean {
    return this.visibility == View.INVISIBLE
}

fun View.showSoftKeyboard(showFlags: Int = InputMethodManager.SHOW_IMPLICIT) {
    if (this.requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, showFlags)
    }
}

fun View.hideSoftKeyboard(hideFlags: Int = 0) {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, hideFlags)
    this.clearFocus()
}

