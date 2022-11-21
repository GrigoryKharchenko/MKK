package com.mkk.ru.presentation.screen.plusproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentAddProductBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.setStatusBarColor
import com.mkk.ru.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class AddProductFragment : BaseFragment<AddProductViewModel>() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val unitsAdapter by lazy {
        ArrayAdapter(requireContext(), R.layout.bottom_menu, mutableListOf<String>())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initViewModel()
        setStatusBarColor(R.color.dark_orange)
    }

    private fun initBinding() {
        with(binding) {
            etPrice.doAfterTextChanged { price ->
                viewModel.calculateSum(price = price?.toString(), amount = etAmount.text.toString())
            }
            etAmount.doAfterTextChanged { amount ->
                viewModel.calculateSum(price = etPrice.text.toString(), amount = amount.toString())
            }
            btnAddCheck.text = getString(R.string.add_product_add_check, "0.0")
            tvUnits.setAdapter(unitsAdapter)
            toolBar.setNavigationOnClickListener { backSaleFragment() }
            tvUnits.setOnItemClickListener { _, _, position, _ ->
                viewModel.setSelectedUnit(position)
            }
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            calculateFlow.onEach { sum ->
                binding.btnAddCheck.text = getString(R.string.add_product_add_check, sum)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            selectedUnitFlow.onEach {
                binding.tvUnits.setText(getString(it.unitsResId), false)
                binding.tilPrice.hint = getString(it.priceResId)
                binding.tilAmount.hint = getString(it.amountResId)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            unitsFlow.onEach {
                unitsAdapter.clear()
                unitsAdapter.addAll(it.map { unit ->
                    getString(unit.unitsResId)
                })
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun backSaleFragment() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
