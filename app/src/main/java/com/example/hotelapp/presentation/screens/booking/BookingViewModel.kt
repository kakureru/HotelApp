package com.example.hotelapp.presentation.screens.booking

import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingDataDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingPriceDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.CustomerInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.HotelInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristCollapsedDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristExpandedDelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristNewDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.CustomerInfoItem
import com.example.hotelapp.presentation.screens.booking.model.InputState
import com.example.hotelapp.presentation.screens.booking.model.TouristItem
import com.example.hotelapp.presentation.screens.booking.model.error
import com.example.hotelapp.presentation.screens.booking.model.normal
import com.example.hotelapp.presentation.screens.booking.model.toBookingDataItem
import com.example.hotelapp.presentation.screens.booking.model.toBookingPriceItem
import com.example.hotelapp.presentation.screens.booking.model.toHotelInfoItem
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import com.example.hotelapp.presentation.utils.RussianFirstHundredOrdinalSpelling as Ros

class BookingViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    private val initTourist = TouristItem(1, Ros.getSpelling(1).capitalize())

    private val customerInnerState = MutableStateFlow(CustomerInfoItem())
    private val touristsInnerState = MutableStateFlow(listOf(initTourist))

    private val bookingData = flow { emit(hotelRepository.getBooking()) }
    private val tourists = MutableStateFlow(touristsInnerState.value)
    private val customer = MutableStateFlow(customerInnerState.value)

    val uiState: Flow<BookingUiState> = combine(bookingData, tourists, customer) { bookingData, touristItems, customerInfo ->
        val bookingPriceItem = bookingData.toBookingPriceItem()
        BookingUiState(
            data = buildList {
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
                add(BookingPriceDelegateItem(bookingPriceItem))
            },
            totalCharge = bookingPriceItem.total
        )
    }

    fun onPhoneTextChanged(text: String) {
        customerInnerState.update {
            it.copy(phone = InputState.Normal(text))
        }
    }

    fun onMailTextChanged(text: String) {
        customerInnerState.update {
            it.copy(mail = InputState.Normal(text))
        }
    }

    fun onCollapseClick(ordinal: Int) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(isExpanded = !tourist.isExpanded)
                else tourist
            }
        }
        refreshTourists()
    }

    fun onNameTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(name = InputState.Normal(text))
                else tourist
            }
        }
    }

    fun onSecondNameTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(secondName = InputState.Normal(text))
                else tourist
            }
        }
    }

    fun onBirthdayDateTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(birthdayDate = InputState.Normal(text))
                else tourist
            }
        }
    }

    fun onCitizenshipTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(citizenship = InputState.Normal(text))
                else tourist
            }
        }
    }

    fun onPassportNumberTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(passportNumber = InputState.Normal(text))
                else tourist
            }
        }
    }

    fun onPassportExpirationTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(passportExpirationDate = InputState.Normal(text))
                else tourist
            }
        }
    }

    fun onAddTouristClick() {
        touristsInnerState.update { it.addNewTourist() }
        refreshTourists()
    }

    private fun List<TouristItem>.addNewTourist(): List<TouristItem> = buildList {
        val newOrdinal = this@addNewTourist.size + 1
        val newTourist = TouristItem(
            ordinal = newOrdinal,
            ordinalName = Ros.getSpelling(newOrdinal).capitalize()
        )
        addAll(this@addNewTourist + newTourist)
    }

    fun onBackClick() {
        router.exit()
    }

    fun onAddressClick() = Unit

    fun onPurchaseClick() {
        checkInputs()
    }

    private fun checkInputs() {
        touristsInnerState.update { tourists ->
            tourists.map {
                it.copy(
                    name = it.name.checkBlank(),
                    secondName = it.secondName.checkBlank(),
                    birthdayDate = it.birthdayDate.checkBlank(),
                    citizenship = it.citizenship.checkBlank(),
                    passportNumber = it.passportNumber.checkBlank(),
                    passportExpirationDate = it.passportExpirationDate.checkBlank(),
                )
            }
        }
        customerInnerState.update {
            it.copy(
                phone = it.phone.checkBlank(),
                mail = it.mail.checkBlank(),
            )
        }
        refreshTourists()
        refreshCustomer()
    }

    private fun String.capitalize() = this.replaceFirstChar { it.uppercase() }

    private fun refreshTourists() { tourists.value = touristsInnerState.value }
    private fun refreshCustomer() { customer.value = customerInnerState.value }

    private fun InputState.checkBlank(): InputState = if (text.isBlank()) this.error() else this.normal()
}