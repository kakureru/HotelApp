package com.example.booking.presentation.model

import com.example.booking.domain.model.Booking
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


class BookingPriceItem(
    val tour: String,
    val fuel: String,
    val service: String,
    val total: String,
)

fun Booking.toBookingPriceItem() = BookingPriceItem(
    tour = tourPrice.toFormattedPrice(),
    fuel = fuelCharge.toFormattedPrice(),
    service = serviceCharge.toFormattedPrice(),
    total = (tourPrice + fuelCharge + serviceCharge).toFormattedPrice(),
)

private fun Int.toFormattedPrice(): String {
    val formatSymbols = DecimalFormatSymbols(Locale.ENGLISH)
    formatSymbols.groupingSeparator = ' '
    return DecimalFormat("#,###", formatSymbols).format(this)
}