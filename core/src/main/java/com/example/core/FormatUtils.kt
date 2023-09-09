package com.example.core

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Int.toFormattedPrice(): String {
    val formatSymbols = DecimalFormatSymbols(Locale.ENGLISH)
    formatSymbols.groupingSeparator = ' '
    return DecimalFormat("#,###", formatSymbols).format(this)
}