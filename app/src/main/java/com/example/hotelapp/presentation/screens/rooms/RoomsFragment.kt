package com.example.hotelapp.presentation.screens.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentRoomsBinding
import com.example.hotelapp.presentation.collectFlowSafely
import com.example.hotelapp.presentation.recyclerview.decorator.MarginItemDecoration
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomsFragment : Fragment() {

    private lateinit var binding: FragmentRoomsBinding
    private val vm: RoomsViewModel by viewModel()

    private val roomAdapter = RoomAdapter { roomId -> vm.onChooseRoomClick(roomId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            listRooms.adapter = roomAdapter
            listRooms.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_s)))
        }
        vm.uiState.render()
    }

    private fun RoomsUiState.render() {
        roomAdapter.submitList(data)
    }

    private fun Flow<RoomsUiState>.render() {
        collectFlowSafely {
            collect {
                it.render()
            }
        }
    }
}
