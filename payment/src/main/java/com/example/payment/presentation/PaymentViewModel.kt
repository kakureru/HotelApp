package com.example.payment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payment.presentation.navigation.PaymentNavigation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class PaymentViewModel(
    private val paymentNavigation: PaymentNavigation,
) : ViewModel() {

    val uiState: StateFlow<PaymentUiState> = emptyFlow<PaymentUiState>()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = PaymentUiState(orderNumber = Random.nextInt(1, 999999))
        )

    fun onBackClick() {
        paymentNavigation.exit()
    }

    fun onActionClick() {
        paymentNavigation.navigateToHotel()
    }
}