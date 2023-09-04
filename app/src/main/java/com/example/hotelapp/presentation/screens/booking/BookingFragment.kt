package com.example.hotelapp.presentation.screens.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentBookingBinding
import com.example.hotelapp.presentation.collectFlowSafely
import com.example.hotelapp.presentation.recyclerview.decorator.MarginItemDecoration
import com.example.hotelapp.presentation.recyclerview.delegate.DelegateListAdapter
import com.example.hotelapp.presentation.screens.booking.adapter.delegate.BookingDataDelegate
import com.example.hotelapp.presentation.screens.booking.adapter.delegate.BookingPriceDelegate
import com.example.hotelapp.presentation.screens.booking.adapter.delegate.CustomerInfoDelegate
import com.example.hotelapp.presentation.screens.booking.adapter.delegate.HotelInfoDelegate
import com.example.hotelapp.presentation.screens.booking.adapter.delegate.TouristExpandedDelegate
import com.example.hotelapp.presentation.screens.booking.adapter.delegate.TouristNewDelegate
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private val vm: BookingViewModel by viewModel()

    private val hotelInfoDelegate = HotelInfoDelegate { vm.onAddressClick() }
    private val bookingDataDelegate = BookingDataDelegate()
    private val customerInfoDelegate = CustomerInfoDelegate()
    private val touristExpandedDelegate = TouristExpandedDelegate()
    private val touristNewDelegate = TouristNewDelegate { vm.onAddTouristClick() }
    private val bookingPriceDelegate = BookingPriceDelegate()

    private val adapter = DelegateListAdapter().apply {
        addDelegate(hotelInfoDelegate)
        addDelegate(bookingDataDelegate)
        addDelegate(customerInfoDelegate)
        addDelegate(touristExpandedDelegate)
        addDelegate(bookingPriceDelegate)
        addDelegate(touristNewDelegate)
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
        }
        vm.uiState.render()
    }

    private fun BookingUiState.render() {
        adapter.submitList(data)
    }

    private fun Flow<BookingUiState>.render() {
        collectFlowSafely {
            collect {
                it.render()
            }
        }
    }
}