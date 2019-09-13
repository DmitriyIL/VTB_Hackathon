package xyz.tusion.vtb_hackathon.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

const val PRICE_DEFAULT_DECIMAL_SEPARATOR_SYMBOL = '.'
const val PRICE_GROUPING_SEPARATOR_SYMBOL = ' '

fun BigDecimal.toCurrencyString(
    newScale: Int = 2,
    roundingMode: Int = BigDecimal.ROUND_HALF_UP,
    decimalSeparator: Char = PRICE_DEFAULT_DECIMAL_SEPARATOR_SYMBOL,
    groupingSeparator: Char? = PRICE_GROUPING_SEPARATOR_SYMBOL,
    showEmptyFractionalPart: Boolean = true
): String {

    val bigDecimal = this.setScale(newScale, roundingMode).stripTrailingZeros()
    val formatSymbols = DecimalFormatSymbols()
        .apply {
            this.decimalSeparator = decimalSeparator
        }

    if (groupingSeparator != null) {
        formatSymbols.groupingSeparator = groupingSeparator
    }

    val formatter = if (showEmptyFractionalPart)
        DecimalFormat("###,##0.00", formatSymbols)
    else
        DecimalFormat("###,##0.##", formatSymbols)

    formatter.isGroupingUsed = groupingSeparator != null

    return formatter.format(bigDecimal)
}