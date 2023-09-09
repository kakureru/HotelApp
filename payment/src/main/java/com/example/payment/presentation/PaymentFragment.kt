package com.example.payment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.collectFlowSafely
import com.example.payment.R
import com.example.payment.databinding.FragmentPaymentBinding
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentFragment : Fragment() {

    private val vm: PaymentViewModel by viewModel()
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title.text = resources.getString(R.string.order_paid)
            btnBack.setOnClickListener { vm.onBackClick() }
        }
        binding.actionButton.btnAction.apply {
            text = resources.getString(R.string.action_super)
            setOnClickListener { vm.onActionClick() }
        }
        vm.uiState.render()
    }

    private fun PaymentUiState.render() {
        binding.message.text = resources.getString(R.string.msg_order_approval, orderNumber)
    }

    private fun Flow<PaymentUiState>.render() {
        collectFlowSafely {
            collect {
                it.render()
            }
        }
    }

}