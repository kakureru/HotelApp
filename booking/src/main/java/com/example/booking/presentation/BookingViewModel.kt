package com.example.booking.presentation

import androidx.lifecycle.ViewModel
import com.example.booking.domain.repository.BookingRepository
import com.example.booking.presentation.adapter.item.BookingDataDelegateItem
import com.example.booking.presentation.adapter.item.BookingPriceDelegateItem
import com.example.booking.presentation.adapter.item.CustomerInfoDelegateItem
import com.example.booking.presentation.adapter.item.HotelInfoDelegateItem
import com.example.booking.presentation.adapter.item.TouristCollapsedDelegateItem
import com.example.booking.presentation.adapter.item.TouristExpandedDelegateItem
import com.example.booking.presentation.adapter.item.TouristNewDelegateItem
import com.example.booking.presentation.model.CustomerInfoItem
import com.example.booking.presentation.model.InputState
import com.example.booking.presentation.model.TouristItem
import com.example.booking.presentation.model.error
import com.example.booking.presentation.model.normal
import com.example.booking.presentation.model.toBookingDataItem
import com.example.booking.presentation.model.toBookingPriceItem
import com.example.booking.presentation.model.toHotelInfoItem
import com.example.booking.presentation.navigation.BookingNavigation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import com.example.core.RussianFirstHundredOrdinalSpelling as Ros

class BookingViewModel(
    private val roomId: Int,
    private val bookingNavigation: BookingNavigation,
    private val bookingRepository: BookingRepository,
) : ViewModel() {

    private val initTourist = TouristItem(1, Ros.getSpelling(1).capitalize())

    private val customerInnerState = MutableStateFlow(CustomerInfoItem())
    private val touristsInnerState = MutableStateFlow(listOf(initTourist))

    private val bookingData = flow { emit(bookingRepository.getBooking(roomId)) }
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
        bookingNavigation.exit()
    }

    fun onAddressClick() = Unit

    fun onPurchaseClick() {
        if (checkInputs()) bookingNavigation.navigateToPayment()
    }

    private fun checkInputs(): Boolean {
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
        return true
    }

    private fun String.capitalize() = this.replaceFirstChar { it.uppercase() }

    private fun refreshTourists() { tourists.value = touristsInnerState.value }
    private fun refreshCustomer() { customer.value = customerInnerState.value }

    private fun InputState.checkBlank(): InputState = if (text.isBlank()) this.error() else this.normal()
}