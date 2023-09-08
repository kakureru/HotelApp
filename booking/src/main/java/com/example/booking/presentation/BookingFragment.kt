package com.example.booking.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.booking.databinding.FragmentBookingBinding
import com.example.booking.presentation.adapter.delegate.BookingDataDelegate
import com.example.booking.presentation.adapter.delegate.BookingPriceDelegate
import com.example.booking.presentation.adapter.delegate.CustomerInfoDelegate
import com.example.booking.presentation.adapter.delegate.HotelInfoDelegate
import com.example.booking.presentation.adapter.delegate.TouristCollapsedDelegate
import com.example.booking.presentation.adapter.delegate.TouristExpandedDelegate
import com.example.booking.presentation.adapter.delegate.TouristNewDelegate
import com.example.core.R
import com.example.core.collectFlowSafely
import com.example.core.recyclerview.decorator.MarginItemDecoration
import com.example.core.recyclerview.delegate.DelegateListAdapter
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private val vm: BookingViewModel by viewModel()

    private val touristExpandedCallback = object : TouristExpandedDelegate.Callback {
        override fun onCollapseClick(ordinal: Int) = vm.onCollapseClick(ordinal)
        override fun onNameTextChanged(ordinal: Int, text: String) = vm.onNameTextChanged(ordinal, text)
        override fun onSecondNameTextChanged(ordinal: Int, text: String) = vm.onSecondNameTextChanged(ordinal, text)
        override fun onBirthdayDateTextChanged(ordinal: Int, text: String) = vm.onBirthdayDateTextChanged(ordinal, text)
        override fun onCitizenshipTextChanged(ordinal: Int, text: String) = vm.onCitizenshipTextChanged(ordinal, text)
        override fun onPassportNumberTextChanged(ordinal: Int, text: String) = vm.onPassportNumberTextChanged(ordinal, text)
        override fun onPassportExpirationTextChanged(ordinal: Int, text: String) = vm.onPassportExpirationTextChanged(ordinal, text)
    }

    private val hotelInfoDelegate = HotelInfoDelegate { vm.onAddressClick() }
    private val bookingDataDelegate = BookingDataDelegate()
    private val customerInfoDelegate = CustomerInfoDelegate()
    private val touristExpandedDelegate = TouristExpandedDelegate(touristExpandedCallback)
    private val touristCollapsedDelegate = TouristCollapsedDelegate { ordinal -> vm.onCollapseClick(ordinal) }
    private val touristNewDelegate = TouristNewDelegate { vm.onAddTouristClick() }
    private val bookingPriceDelegate = BookingPriceDelegate()

    private val adapter = DelegateListAdapter().apply {
        addDelegate(hotelInfoDelegate)
        addDelegate(bookingDataDelegate)
        addDelegate(customerInfoDelegate)
        addDelegate(touristExpandedDelegate)
        addDelegate(touristCollapsedDelegate)
        addDelegate(bookingPriceDelegate)
        addDelegate(touristNewDelegate)
        setHasStableIds(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.title.text = resources.getString(R.string.booking)
            recycler.adapter = adapter
            recycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_s)))
            toolbar.btnBack.setOnClickListener { vm.onBackClick() }
            actionButtonLayout.btnAction.setOnClickListener { vm.onPurchaseClick() }
        }
        vm.uiState.render()
    }

    private fun BookingUiState.render() {
        adapter.submitList(data)
        binding.actionButtonLayout.btnAction.text = context?.resources?.getString(R.string.pay_price, totalCharge)
    }

    private fun Flow<BookingUiState>.render() {
        collectFlowSafely {
            collect {
                it.render()
            }
        }
    }

    companion object {
        private const val ARG_ROOM_ID = "ARG_ROOM_ID"
        fun newInstance(roomId: Int): Fragment = BookingFragment().apply {
            arguments = bundleOf(ARG_ROOM_ID to roomId)
        }
    }
}