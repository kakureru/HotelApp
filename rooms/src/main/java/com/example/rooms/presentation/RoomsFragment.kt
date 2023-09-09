package com.example.rooms.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.core.R
import com.example.core.collectFlowSafely
import com.example.core.recyclerview.decorator.VerticalMarginItemDecoration
import com.example.rooms.databinding.FragmentRoomsBinding
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RoomsFragment : Fragment() {

    private val hotelId: Int by lazy {
        arguments?.getInt(ARG_HOTEL_ID, -1)?.takeIf { it >= 0 } ?: throw IllegalArgumentException()
    }

    private lateinit var binding: FragmentRoomsBinding
    private val vm: RoomsViewModel by viewModel { parametersOf(hotelId) }

    private val roomAdapter = RoomAdapter { roomId -> vm.onChooseRoomClick(roomId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.btnBack.setOnClickListener { vm.onBackClick() }
            listRooms.adapter = roomAdapter
            listRooms.addItemDecoration(VerticalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_s)))
        }
        vm.effects.handleEffect()
        vm.uiState.renderState()
    }

    private fun Flow<RoomsUiState>.renderState() {
        collectFlowSafely {
            collect { state ->
                binding.toolbar.title.text = state.hotelName
                roomAdapter.submitList(state.rooms)
            }
        }
    }

    private fun Flow<RoomsEffect>.handleEffect() {
        collectFlowSafely {
            collect { effect ->
                when (effect) {
                    is RoomsEffect.Error -> {
                        Toast.makeText(context, resources.getString(effect.msgRes), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    companion object {
        private const val ARG_HOTEL_ID = "ARG_HOTEL_ID"

        fun newInstance(hotelId: Int): Fragment = RoomsFragment().apply {
            arguments = bundleOf(ARG_HOTEL_ID to hotelId)
        }
    }
}
