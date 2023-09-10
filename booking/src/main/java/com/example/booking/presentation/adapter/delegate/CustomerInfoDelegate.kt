package com.example.booking.presentation.adapter.delegate

import android.content.Context
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
        private val context: Context,
        private val binding: ItemCustomerInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CustomerInfoItem) {
            with(binding) {
                etPhone.setOnFocusChangeListener { v, hasFocus ->
                    etPhone.setHint(R.string.phone_mask_hint)
//                    etPhone.mask = context.getString(R.string.phone_mask)
                }
                etPhone.bindState(item.phone)
                etMail.bindState(item.mail)

                etPhone.addTextChangedListener { callback.onPhoneTextChanged("${etPhone.text}") }
                etMail.addTextChangedListener { callback.onMailTextChanged("${etMail.text}") }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        CustomerInfoViewHolder(
            parent.context,
            ItemCustomerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as CustomerInfoViewHolder).bind(item.content() as CustomerInfoItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is CustomerInfoDelegateItem
}