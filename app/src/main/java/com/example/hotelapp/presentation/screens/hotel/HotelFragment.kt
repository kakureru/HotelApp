package com.example.hotelapp.presentation.screens.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentHotelBinding
import com.example.hotelapp.presentation.utils.recyclerview.OnSnapPositionChangeListener
import com.example.hotelapp.presentation.utils.recyclerview.SnapOnScrollListener
import com.example.hotelapp.presentation.utils.recyclerview.attachSnapHelperWithListener
import com.example.hotelapp.presentation.utils.recyclerview.decorator.CirclePagerIndicatorDecoration
import com.example.hotelapp.presentation.utils.recyclerview.decorator.MarginItemDecoration

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding

    private val onSnapPositionChangeListener = object : OnSnapPositionChangeListener { // FIXME useless
        override fun onSnapPositionChange(position: Int) = Unit
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            listPhoto.apply {
                attachSnapHelperWithListener(
                    snapHelper = PagerSnapHelper(),
                    behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    onSnapPositionChangeListener = onSnapPositionChangeListener,
                )
                addItemDecoration(CirclePagerIndicatorDecoration())
                addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_m)))
            }
        }
    }

}