package com.example.booking.presentation.adapter.delegate

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemCustomerInfoBinding
import com.example.booking.presentation.adapter.item.CustomerInfoDelegateItem
import com.example.booking.presentation.model.CustomerInfoItem
import com.example.booking.presentation.model.bindState
import com.example.core.R
import com.example.core.recyclerview.delegate.AdapterDelegate
import com.example.core.recyclerview.delegate.DelegateItem


class CustomerInfoDelegate(
    private val callback: CustomerInfoDelegate.Callback,
) : AdapterDelegate {

    interface Callback {
        fun onPhoneTextChanged(text: String)
        fun onMailTextChanged(text: String)
    }

    inner class CustomerInfoViewHolder(
        private val binding: ItemCustomerInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                etPhone.apply {
                    setOnFocusChangeListener { _, _ ->
                        setHint(R.string.phone_mask_hint)
                        mask = context.getString(R.string.phone_mask)
                        typeface = Typeface.MONOSPACE
                        setShouldKeepText(true)
                    }
                }
                etPhone.addTextChangedListener { callback.onPhoneTextChanged(etPhone.rawText) }
                etMail.addTextChangedListener { callback.onMailTextChanged("${etMail.text}") }
            }
        }

        fun bind(item: CustomerInfoItem) {
            with(binding) {
                etPhone.bindState(item.phone)
                etMail.bindState(item.mail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        CustomerInfoViewHolder(ItemCustomerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as CustomerInfoViewHolder).bind(item.content() as CustomerInfoItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is CustomerInfoDelegateItem
}