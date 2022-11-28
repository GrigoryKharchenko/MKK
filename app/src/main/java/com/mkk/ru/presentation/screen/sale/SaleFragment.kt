package com.mkk.ru.presentation.screen.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentSaleBinding
import com.mkk.ru.extension.addFragment
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.setStatusBarColor
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.plusproduct.AddProductFragment
import com.mkk.ru.presentation.screen.sale.adapter.SaleAdapter
import kotlinx.coroutines.flow.onEach

class SaleFragment : BaseFragment<SaleViewModel>() {

    private var _binding: FragmentSaleBinding? = null
    private val binding get() = _binding!!

    private val adapter: SaleAdapter = SaleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.dark_green)
        initUi()
        subscribeToViewModel()
    }

    private fun initUi() {
        with(binding) {
            btnAddProduct.safeOnClickListener {
                addFragment<AddProductFragment>(R.id.container)
            }
            toolBar.setNavigationOnClickListener { goBack() }
            rvListProduct.adapter = adapter
        }
    }

    private fun subscribeToViewModel() {
        with(viewModel) {
            productFlow.onEach(::handleUiState)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            productCostFlow.onEach {
                binding.tvSum.text = it.toString()
                binding.btnAmount.setAmount(it.toString())
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun handleUiState(state: SaleUiState) {
        with(binding) {
            groupListProduct.isVisible = state.hasProducts
            adapter.submitList(state.products)
            tvEmptyProduct.isGone = state.hasProducts
            tvAddProductOrScan.isGone = state.hasProducts
        }
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
