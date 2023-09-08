package com.example.hotel.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.core.R
import com.example.core.recyclerview.OnSnapPositionChangeListener
import com.example.core.recyclerview.SnapOnScrollListener
import com.example.core.recyclerview.attachSnapHelperWithListener
import com.example.core.recyclerview.decorator.CirclePagerIndicatorDecoration
import com.example.core.recyclerview.decorator.MarginItemDecoration
import com.example.hotel.databinding.FragmentHotelBinding

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