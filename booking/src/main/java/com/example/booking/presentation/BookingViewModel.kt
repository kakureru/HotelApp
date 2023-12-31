package com.example.booking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking.R
import com.example.booking.domain.model.Booking
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
import com.example.booking.presentation.model.toBookingDataItem
import com.example.booking.presentation.model.toBookingPriceItem
import com.example.booking.presentation.model.toHotelInfoItem
import com.example.booking.presentation.navigation.BookingNavigation
import com.example.core.runCatchingNonCancellation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties
import com.example.core.RussianFirstHundredOrdinalSpelling as Ros

class BookingViewModel(
    private val roomId: Int,
    private val bookingNavigation: BookingNavigation,
    private val bookingRepository: BookingRepository,
) : ViewModel() {

    private val initTourist = TouristItem(1, Ros.getSpelling(1).capitalize())

    private val customerInnerState = MutableStateFlow(CustomerInfoItem())
    private val touristsInnerState = MutableStateFlow(listOf(initTourist))

    private val tourists = MutableStateFlow(touristsInnerState.value)
    private val customer = MutableStateFlow(customerInnerState.value)

    val uiState: StateFlow<BookingUiState> =
        combine(
            loadBookingData(),
            tourists,
            customer
        ) { bookingData, touristItems, customerInfo ->
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
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = BookingUiState(),
        )

    private val _effects = MutableSharedFlow<BookingEffect>()
    val effects: SharedFlow<BookingEffect> = _effects.asSharedFlow()

    private fun loadBookingData(): Flow<Booking> = flow {
        runCatchingNonCancellation {
            emit(bookingRepository.getBooking(roomId))
        }.onFailure {
            _effects.emit(BookingEffect.Error(R.string.error_failed_to_load_booking_data))
        }
    }

    fun onPhoneTextChanged(text: String) {
        customerInnerState.update {
            it.copy(phone = InputState.Accepted(text))
        }
    }

    fun onMailTextChanged(text: String) {
        customerInnerState.update {
            it.copy(mail = InputState.Accepted(text))
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
                if (tourist.ordinal == ordinal) tourist.copy(name = text.checkBlank())
                else tourist
            }
        }
    }

    fun onSecondNameTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(secondName = text.checkBlank())
                else tourist
            }
        }
    }

    fun onBirthdayDateTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(birthdayDate = text.checkBlank())
                else tourist
            }
        }
    }

    fun onCitizenshipTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(citizenship = text.checkBlank())
                else tourist
            }
        }
    }

    fun onPassportNumberTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(passportNumber = text.checkBlank())
                else tourist
            }
        }
    }

    fun onPassportExpirationTextChanged(ordinal: Int, text: String) {
        touristsInnerState.update { tourists ->
            tourists.map {  tourist ->
                if (tourist.ordinal == ordinal) tourist.copy(passportExpirationDate = text.checkBlank())
                else tourist
            }
        }
    }

    fun onAddTouristClick() = viewModelScope.launch(Dispatchers.Default) {
        if (checkTourists()) {
            touristsInnerState.update { it.addNewTourist() }
            refreshTourists()
        }
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

    fun onPurchaseClick() = viewModelScope.launch(Dispatchers.Default) {
        val customerCheckResult = checkCustomer()
        val touristsCheckResult = checkTourists()
        if (touristsCheckResult && customerCheckResult)
            bookingNavigation.navigateToPayment()
    }

    private fun checkCustomer(): Boolean {
        customerInnerState.update {
            it.copy(
                phone = it.phone.text.checkBlank(),
                mail = it.mail.text.checkMail(),
            )
        }
        refreshCustomer()
        for (prop in CustomerInfoItem::class.memberProperties)
            if (prop.get(customerInnerState.value) is InputState.Rejected)
                return false
        return true
    }

    private fun checkTourists(): Boolean {
        touristsInnerState.update { tourists ->
            tourists.map {
                it.copy(
                    name = it.name.text.checkBlank(),
                    secondName = it.secondName.text.checkBlank(),
                    birthdayDate = it.birthdayDate.text.checkBlank(),
                    citizenship = it.citizenship.text.checkBlank(),
                    passportNumber = it.passportNumber.text.checkBlank(),
                    passportExpirationDate = it.passportExpirationDate.text.checkBlank(),
                )
            }
        }
        refreshTourists()
        touristsInnerState.value.forEach { touristStatus ->
            for (prop in TouristItem::class.memberProperties)
                if (prop.get(touristStatus) is InputState.Rejected)
                    return false
        }
        return true
    }

    private fun String.capitalize() = this.replaceFirstChar { it.uppercase() }

    private fun refreshTourists() { tourists.value = touristsInnerState.value }

    private fun refreshCustomer() { customer.value = customerInnerState.value }

    private fun String.checkBlank(): InputState = if (isBlank()) InputState.Rejected(this) else InputState.Accepted(this)

    private fun String.checkMail(): InputState = if (isValidMail()) InputState.Accepted(this) else InputState.Rejected(this)

    private fun String.isValidMail() = matches(Regex("^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}\$", RegexOption.IGNORE_CASE))
}