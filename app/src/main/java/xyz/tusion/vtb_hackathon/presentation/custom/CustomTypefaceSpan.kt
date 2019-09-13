package xyz.tusion.vtb_hackathon.presentation.custom

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan


/**
 * This class was created to handle different styles in one TextView (often used with sums of money)
 * If the default typeface is used for the purpose described above, the app will crash with the NoSuchMethod error when setting
 * typeface to a span or a textView
 * */
class CustomTypefaceSpan(private val typeface: Typeface): MetricAffectingSpan() {
    override fun updateMeasureState(textPaint: TextPaint) {
        applyCustomTypeFace(
            textPaint,
            typeface
        )
    }

    override fun updateDrawState(tp: TextPaint) {
        applyCustomTypeFace(
            tp,
            typeface
        )
    }

    companion object {
        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            paint.setTypeface(tf)
        }
    }

}