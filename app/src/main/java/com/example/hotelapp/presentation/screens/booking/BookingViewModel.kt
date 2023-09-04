package com.example.hotelapp.presentation.screens.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.domain.model.Booking
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingDataDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingPriceDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.CustomerInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.HotelInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristNewDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.CustomerInfoItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingDataItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingPriceItem
import com.example.hotelapp.presentation.screens.booking.model.toHotelInfoItem
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    fun onExpandClick() {

    }

    fun onCollapseClick() {

    }

    fun onAddTouristClick() {
        _uiState.update { state ->
            state.copy(data = buildList {
                state.data.forEach {
                    if (it is TouristNewDelegateItem)
                        add(TouristDelegateItem(TouristItem("Турист 2")))
                    add(it)
                }
            })
        }
    }

    private fun Booking.toDelegateList() = listOf(
        HotelInfoDelegateItem(this.toHotelInfoItem()),
        BookingDataDelegateItem(this.toBookingDataItem()),
        CustomerInfoDelegateItem(CustomerInfoItem()),
        TouristDelegateItem(TouristItem("Турист")),
        TouristNewDelegateItem(),
        BookingPriceDelegateItem(this.toBookingPriceItem()),
    )

    fun onBackClick() {
        router.exit()
    }

    fun onAddressClick() = Unit
}