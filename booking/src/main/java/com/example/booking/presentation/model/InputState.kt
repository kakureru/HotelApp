package com.example.booking.presentation.model

import android.widget.EditText
import com.example.core.R

sealed class InputState(open val text: String) {
    class Rejected(override val text: String) : InputState(text)
    class Accepted(override val text: String) : InputState(text)
}

fun EditText.bindState(state: InputState) {
    setBackgroundResource(
        if (state is InputState.Rejected) R.drawable.bg_rounded_edittext_error
        else R.drawable.bg_rounded_edittext
    )
    setText(state.text)
}