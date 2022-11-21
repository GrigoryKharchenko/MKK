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
import com.mkk.ru.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class AddProductFragment : BaseFragment<AddProductViewModel>() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun initBinding() {
        with(binding) {
            etPrice.doAfterTextChanged { price ->
                viewModel.calculateSum(price = price?.toString(), amount = etAmount.text.toString())
            }
            etAmount.doAfterTextChanged { amount ->
                viewModel.calculateSum(price = etPrice.text.toString(), amount = amount.toString())
            }
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            calculateFlow.onEach { sum ->
                binding.btnAddCheck.text = getString(R.string.add_product_add_check, sum)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            unitsFlow.onEach { typeUnits ->
                setUnitsAdapter(typeUnits)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun setUnitsAdapter(typeUnits: List<TypeUnits>) {
        with(binding) {
            val unitsAdapter = ArrayAdapter(requireContext(), R.layout.bottom_menu, typeUnits.map { unit ->
                getString(unit.unitsResId)
            })
            tvUnits.setOnItemClickListener { _, _, position, _ ->
                tilPrice.hint = getString(typeUnits[position].priceResId)
                tilAmount.hint = getString(typeUnits[position].amountResId)
            }
            tvUnits.setAdapter(unitsAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
