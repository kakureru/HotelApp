package com.example.booking.presentation.model

import android.widget.EditText
import com.example.core.R

data class TouristItem(
    val ordinal: Int,
    val ordinalName: String,
    val name: InputState = InputState.Normal(""),
    val secondName: InputState = InputState.Normal(""),
    val birthdayDate: InputState = InputState.Normal(""),
    val citizenship: InputState = InputState.Normal(""),
    val passportNumber: InputState = InputState.Normal(""),
    val passportExpirationDate: InputState = InputState.Normal(""),
    val isExpanded: Boolean = true,
)

sealed class InputState(open val text: String) {
    class Error(override val text: String) : InputState(text)
    class Normal(override val text: String) : InputState(text)
}

fun InputState.normal() = InputState.Normal(text)
fun InputState.error() = InputState.Error(text)

fun EditText.bindState(state: InputState) {
    setBackgroundResource(
        if (state is InputState.Error) R.drawable.bg_rounded_edittext_error
        else R.drawable.bg_rounded_edittext
    )
    setText(state.text)
}