package com.example.hotelapp.presentation.screens.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.domain.model.Booking
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingDataDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingPriceDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.HotelInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingDataItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingPriceItem
import com.example.hotelapp.presentation.screens.booking.model.toHotelInfoItem
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = BookingUiState(hotelRepository.getBooking().toDelegateList())
        }
    }

    private fun Booking.toDelegateList() = listOf(
        HotelInfoDelegateItem(this.toHotelInfoItem()),
        BookingDataDelegateItem(this.toBookingDataItem()),
        BookingPriceDelegateItem(this.toBookingPriceItem()),
    )

    fun onBackClick() {
        router.exit()
    }
}