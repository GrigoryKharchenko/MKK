package com.mkk.ru.presentation.screen.plusproduct

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.appComp
import com.mkk.ru.databinding.FragmentAddProductBinding
import com.mkk.ru.di.ViewModelFactory
import com.mkk.ru.di.component.DaggerFragmentComponent
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.setStatusBarColor
import com.mkk.ru.presentation.screen.sale.SaleViewModel
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val unitsAdapter by lazy {
        ArrayAdapter(requireContext(), R.layout.bottom_menu, mutableListOf<String>())
    }
//    @Inject
//    lateinit var defaultViewModelFactory: ViewModelFactory
//
//    val viewModel by lazy {
//        ViewModelProvider(this, viewModelFactory)
//            .get(getViewModelClass())
//    }
//
//    val viewModelFactory: ViewModelProvider.Factory
//        get() = defaultViewModelFactory
//
//    @Suppress("UNCHECKED_CAST")
//    private fun getViewModelClass() =
//        (javaClass.genericSuperclass as ParameterizedType)
//            .actualTypeArguments[0] as Class<AddProductViewModel>
//    @Inject
//    lateinit var addProductViewModelFactory: Lazy<AddProductViewModel.Factory>
//
//    private val addProductViewModel: AddProductViewModel by viewModels {
//        addProductViewModelFactory.get()
//    }

    override fun onAttach(context: Context) {
        DaggerFragmentComponent.factory()
            .create(context.appComp)
            .inject(this@AddProductFragment)
        super.onAttach(context)
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
        initUi()
        subscribeToViewModel()
        setStatusBarColor(R.color.dark_orange)
    }

    private fun initUi() {
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
            btnAddCheck.setOnClickListener {
                viewModel.addProducts(
                    productName = etProduct.text.toString(),
                    price = etPrice.text.toString(),
                    amount = etAmount.text.toString(),
                    productCode = etProductCode.text.toString(),
                )
            }
            etPrice.setOnFocusChangeListener { _, _ ->
                viewModel.hidePriceError()
            }
            etProduct.setOnFocusChangeListener { _, _ ->
                viewModel.hideProductError()
            }
            etAmount.setOnFocusChangeListener { _, _ ->
                viewModel.hideAmountError()
            }
            etProductCode.setOnFocusChangeListener { _, _ ->
                viewModel.hideProductCodeError()
            }
        }
    }

    private fun subscribeToViewModel() {
        with(viewModel) {
            calculateFlow.onEach { sum ->
                binding.btnAddCheck.text = getString(R.string.add_product_add_check, sum)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            selectedUnitFlow.onEach(::processingSelectedUnitFlow)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            unitsFlow.onEach(::processingUnitsFlow)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            errorFlow.onEach(::checkValidation)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            backFlow.onEach {
                goBack()
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

    private fun checkValidation(error: ErrorValidation) {
        with(binding) {
            tilProduct.error = error.errorProduct?.let { getString(it) }
            tilPrice.error = error.errorPrice?.let { getString(it) }
            tilAmount.error = error.errorAmount?.let { getString(it) }
            tilProductCode.error = error.errorProductCode?.let { getString(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val INIT_SUM = 0.0
    }
}
