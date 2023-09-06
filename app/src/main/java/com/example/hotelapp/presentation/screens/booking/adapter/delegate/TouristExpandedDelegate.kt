package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.databinding.ItemTouristExpandedBinding
import com.example.hotelapp.presentation.recyclerview.delegate.AdapterDelegate
import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristExpandedDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem
import com.example.hotelapp.presentation.screens.booking.model.bindState

class TouristExpandedDelegate(
    private val callback: Callback,
) : AdapterDelegate {

    interface Callback {
        fun onCollapseClick(ordinal: Int)
        fun onNameTextChanged(ordinal: Int, text: String)
        fun onSecondNameTextChanged(ordinal: Int, text: String)
        fun onBirthdayDateTextChanged(ordinal: Int, text: String)
        fun onCitizenshipTextChanged(ordinal: Int, text: String)
        fun onPassportNumberTextChanged(ordinal: Int, text: String)
        fun onPassportExpirationTextChanged(ordinal: Int, text: String)
    }

    inner class TouristViewHolder(
        private val context: Context,
        private val binding: ItemTouristExpandedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TouristItem) {
            with(binding) {
                titleTourist.text = item.ordinalName

                etName.bindState(item.name)
                etSecondName.bindState(item.secondName)
                etBirthdayDate.bindState(item.birthdayDate)
                etCitizenship.bindState(item.citizenship)
                etPassportNumber.bindState(item.passportNumber)
                etPassportExpirationDate.bindState(item.passportExpirationDate)

                etName.addTextChangedListener { callback.onNameTextChanged(item.ordinal, "${etName.text}") }
                etSecondName.addTextChangedListener { callback.onSecondNameTextChanged(item.ordinal, "${etSecondName.text}") }
                etBirthdayDate.addTextChangedListener { callback.onBirthdayDateTextChanged(item.ordinal, "${etBirthdayDate.text}") }
                etCitizenship.addTextChangedListener { callback.onCitizenshipTextChanged(item.ordinal, "${etCitizenship.text}") }
                etPassportNumber.addTextChangedListener { callback.onPassportNumberTextChanged(item.ordinal, "${etPassportNumber.text}") }
                etPassportExpirationDate.addTextChangedListener { callback.onPassportExpirationTextChanged(item.ordinal, "${etPassportExpirationDate.text}") }

                btnCollapse.setOnClickListener { callback.onCollapseClick(item.ordinal) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        TouristViewHolder(
            parent.context,
            ItemTouristExpandedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as TouristViewHolder).bind(item.content() as TouristItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TouristExpandedDelegateItem
}