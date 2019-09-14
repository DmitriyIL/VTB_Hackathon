package xyz.tusion.vtb_hackathon.extensions

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import xyz.tusion.vtb_hackathon.R
import xyz.tusion.vtb_hackathon.presentation.custom.CustomTypefaceSpan

fun String.getBoldSumRegularCurrencySpannableStringBuilder(
    currency: String,
    context: Context,
    sumColorResId: Int = 0,
    currencyColorResId: Int = 0
): SpannableStringBuilder {
    val spannableSum = SpannableString(this)
    val spannableCurrencyCode = SpannableString(currency)
    val boldFont = Typeface.create(ResourcesCompat.getFont(context, R.font.firasans_bold), Typeface.BOLD)
    val regularFont = Typeface.create(ResourcesCompat.getFont(context, R.font.firasans_regular), Typeface.NORMAL)
    spannableSum.setSpan(
        CustomTypefaceSpan(
            boldFont
        ), 0, spannableSum.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannableCurrencyCode.setSpan(
        CustomTypefaceSpan(
            regularFont
        ), 0, spannableCurrencyCode.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    if (sumColorResId != 0) {
        spannableSum.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    sumColorResId
                )
            ), 0, spannableSum.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    if (currencyColorResId != 0) {
        spannableCurrencyCode.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    currencyColorResId
                )
            ), 0, spannableCurrencyCode.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    val builder = SpannableStringBuilder()
    builder.append(spannableSum)
    builder.append(" ")
    builder.append(spannableCurrencyCode)
    return builder
}


fun String.removeStartSigns(): String {
    val rus1 = 'а'..'я'
    val rus2 = 'А'..'Я'
    val eng1 = 'a'..'z'
    val eng2 = 'A'..'Z'
    var startIndex = 0
    while (!(this[startIndex] in rus1
                || this[startIndex] in rus2
                || this[startIndex] in eng1
                || this[startIndex] in eng2)) {
        startIndex++
    }
    return this.removeRange(0, startIndex)
}
