package com.example.hotelapp.presentation.screens.booking

import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.domain.model.Booking
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingDataDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingPriceDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.CustomerInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.HotelInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristCollapsedDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristExpandedDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristNewDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.CustomerInfoItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingDataItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingPriceItem
import com.example.hotelapp.presentation.screens.booking.model.toHotelInfoItem
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class BookingViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    private val bookingData: Flow<Booking> = flow { emit(hotelRepository.getBooking()) }
    private val tourists: MutableStateFlow<List<TouristItem>> = MutableStateFlow(listOf(TouristItem(1, "Турист"))) // FIXME init
    private val customerInfo: MutableStateFlow<CustomerInfoItem> = MutableStateFlow(CustomerInfoItem())

    val uiState: Flow<BookingUiState> = combine(bookingData, tourists, customerInfo) { bookingData, touristItems, customerInfo ->
        BookingUiState(
            buildList {
                add(HotelInfoDelegateItem(bookingData.toHotelInfoItem()))
                add(BookingDataDelegateItem(bookingData.toBookingDataItem()))
                add(CustomerInfoDelegateItem(customerInfo))
                touristItems.forEach {
                    add(
                        if (it.isExpanded) TouristExpandedDelegateItem(it)
                        else TouristCollapsedDelegateItem(it)
                    )
                }
                add(TouristNewDelegateItem())
                add(BookingPriceDelegateItem(bookingData.toBookingPriceItem()))
            }
        )
    }

    fun onCollapseClick(ordinal: Int) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(isExpanded = !tourist.isExpanded)
                else tourist
            }
        }
    }

    fun onNameTextChanged(ordinal: Int, text: String) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(name = text)
                else tourist
            }
        }
    }

    fun onSecondNameTextChanged(ordinal: Int, text: String) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(secondName = text)
                else tourist
            }
        }
    }

    fun onBirthdayDateTextChanged(ordinal: Int, text: String) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(birthdayDate = text)
                else tourist
            }
        }
    }

    fun onCitizenshipTextChanged(ordinal: Int, text: String) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(citizenship = text)
                else tourist
            }
        }
    }

    fun onPassportNumberTextChanged(ordinal: Int, text: String) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(passportNumber = text)
                else tourist
            }
        }
    }

    fun onPassportExpirationTextChanged(ordinal: Int, text: String) {
        tourists.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(passportExpirationDate = text)
                else tourist
            }
        }
    }

    fun onAddTouristClick() {
        tourists.update { it.addNewTourist() }
    }

    private fun List<TouristItem>.addNewTourist(): List<TouristItem> = buildList {
        val newTourist = TouristItem(ordinal = this@addNewTourist.size + 1, ordinalName = "Турист")
        addAll(this@addNewTourist + newTourist)
    }

    fun onBackClick() {
        router.exit()
    }

    fun onAddressClick() = Unit
}