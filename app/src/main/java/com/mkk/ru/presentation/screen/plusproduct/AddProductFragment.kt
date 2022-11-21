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
            btnAddCheck.text = getString(R.string.add_product_add_check, INIT_SUM)
            tvUnits.setAdapter(unitsAdapter)
            toolBar.setNavigationOnClickListener { goBack() }
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
            selectedUnitFlow.onEach { typeUnits ->
                processingSelectedUnitFlow(typeUnits)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            unitsFlow.onEach { listTypeUnits ->
                processingUnitsFlow(listTypeUnits)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun processingSelectedUnitFlow(typeUnits: TypeUnits) {
        with(binding) {
            tvUnits.setText(getString(typeUnits.unitsResId), false)
            tilPrice.hint = getString(typeUnits.priceResId)
            tilAmount.hint = getString(typeUnits.amountResId)
        }
    }

    private fun processingUnitsFlow(listTypeUnits: List<TypeUnits>) {
        unitsAdapter.clear()
        unitsAdapter.addAll(listTypeUnits.map { unit ->
            getString(unit.unitsResId)
        })
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val INIT_SUM = "0.0"
    }
}
