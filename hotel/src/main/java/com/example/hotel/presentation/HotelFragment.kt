package com.example.hotel.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.core.collectFlowSafely
import com.example.core.recyclerview.PeculiarityAdapter
import com.example.core.recyclerview.PhotoAdapter
import com.example.core.recyclerview.decorator.CirclePagerIndicatorDecoration
import com.example.core.recyclerview.decorator.DividerItemDecorator
import com.example.core.recyclerview.decorator.HorizontalMarginItemDecoration
import com.example.hotel.R
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.presentation.feature.FeatureAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.core.R as RCore

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private val vm: HotelViewModel by viewModel()

    private val photoAdapter = PhotoAdapter()
    private val peculiarityAdapter = PeculiarityAdapter()
    private val featureAdapter = FeatureAdapter { featureId -> vm.onFeatureClick(featureId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            listFeatures.apply {
                addItemDecoration(DividerItemDecorator(ResourcesCompat.getDrawable(resources, R.drawable.feature_divider, null)))
                adapter = featureAdapter
            }
            listPeculiarities.apply {
                adapter = peculiarityAdapter
                layoutManager = FlexboxLayoutManager(context).apply { FlexDirection.ROW }
            }
            listPhoto.apply {
                adapter = photoAdapter
                PagerSnapHelper().attachToRecyclerView(this)
                addItemDecoration(CirclePagerIndicatorDecoration())
                addItemDecoration(HorizontalMarginItemDecoration(resources.getDimensionPixelSize(RCore.dimen.margin_m)))
            }
            actionButtonLayout.btnAction.apply {
                text = resources.getString(R.string.action_to_room_choose)
                setOnClickListener { vm.onChooseRoomClick() }
            }
        }
        vm.effects.handleEffect()
        vm.uiState.renderState()
    }

    private fun Flow<HotelUiState>.renderState() {
        collectFlowSafely {
            collect { state ->
                with(binding) {
                    photoAdapter.submitList(state.imageUrls)
                    info.ratingLayout.rating.text = state.rating
                    info.name.text = state.name
                    info.address.text = state.address
                    price.text = resources.getString(R.string.minimal_price, state.minimalPrice)
                    pricePer.text = state.priceForIt
                    peculiarityAdapter.submitList(state.peculiarities)
                    tvDescription.text = state.description
                    featureAdapter.submitList(state.features)
                }
            }
        }
    }

    private fun Flow<HotelEffect>.handleEffect() {
        collectFlowSafely {
            collect { effect ->
                when (effect) {
                    is HotelEffect.Error -> {
                        Toast.makeText(context, resources.getString(effect.msgRes), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}