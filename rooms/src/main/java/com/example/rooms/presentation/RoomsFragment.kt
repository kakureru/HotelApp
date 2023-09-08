package com.example.rooms.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.R
import com.example.core.collectFlowSafely
import com.example.core.recyclerview.decorator.MarginItemDecoration
import com.example.rooms.databinding.FragmentRoomsBinding
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
//            mToolbar.btnBack.set
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
