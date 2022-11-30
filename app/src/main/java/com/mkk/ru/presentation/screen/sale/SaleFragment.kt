package com.mkk.ru.presentation.screen.sale

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.appComp
import com.mkk.ru.databinding.FragmentSaleBinding
import com.mkk.ru.di.ViewModelFactory
import com.mkk.ru.di.component.DaggerFragmentComponent
import com.mkk.ru.extension.addFragment
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.setStatusBarColor
import com.mkk.ru.presentation.screen.plusproduct.AddProductFragment
import com.mkk.ru.presentation.screen.sale.adapter.SaleAdapter
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

class SaleFragment : Fragment() {

    private var _binding: FragmentSaleBinding? = null
    private val binding get() = _binding!!

    private val adapter: SaleAdapter = SaleAdapter()

//    @Inject
//    lateinit var defaultViewModelFactory: ViewModelFactory
//
//    val viewModel by lazy {
//        ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
//    }
//
//    val viewModelFactory: ViewModelProvider.Factory
//        get() = defaultViewModelFactory
//
//    @Suppress("UNCHECKED_CAST")
//    private fun getViewModelClass() =
//        (javaClass.genericSuperclass as ParameterizedType)
//            .actualTypeArguments[0] as Class<SaleViewModel>

//    @Inject
//    lateinit var saleViewModelFactory: Lazy<SaleViewModel.Factory>
//
//    private val saleViewModel: SaleViewModel by viewModels {
//        saleViewModelFactory.get()
//    }

    override fun onAttach(context: Context) {
        DaggerFragmentComponent.factory()
            .create(context.appComp)
            .inject(this@SaleFragment)
        super.onAttach(context)
    }

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
