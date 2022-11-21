package com.mkk.ru.presentation.screen.registrationcashbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentRegistrationCashBoxBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.replaceFragment
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.showSnackbar
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.claimstatus.ClaimStatusFragment
import kotlinx.coroutines.flow.onEach

class RegistrationCashBoxFragment : BaseFragment<RegistrationCashBoxViewModel>() {

    private var _binding: FragmentRegistrationCashBoxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationCashBoxBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnSendRequest.safeOnClickListener {
                viewModel.checkValidation(
                    nameSubject = etNameSubject.text.toString(),
                    innSubject = etInn.text.toString(),
                    addressSubject = etAddressSubject.text.toString(),
                    dislocationTax = tvDislocationTax.text.toString(),
                    taxMode = tvTaxMode.text.toString(),
                    typeAction = etTypeAction.text.toString(),
                    contactNumber = etContactNumber.text.toString(),
                    typeObject = etTypeObject.text.toString(),
                    nameObject = etNameObject.text.toString(),
                    addressObject = etAddressObject.text.toString()
                )
            }
        }
        with(viewModel) {
            showSnackbarFlow.onEach { showSnackBar ->
                showSnackbar(showSnackBar)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            openRequestAcceptanceFragmentFlow.onEach {
                replaceFragment<ClaimStatusFragment>(R.id.container)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            subdivisionsFlow.onEach { subdivisions ->
                setSubdivision(subdivisions)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            statusProgressBarFlow.onEach { statusProgressBar ->
                binding.flProgress.isVisible = statusProgressBar
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
        setTaxMode()
    }

    private fun setSubdivision(subdivisionModel: List<String>) {
        val subdivisionAdapter = ArrayAdapter(
            requireContext(),
            R.layout.bottom_menu,
            subdivisionModel
        )
        binding.tvDislocationTax.setAdapter(subdivisionAdapter)
    }

    private fun setTaxMode() {
        val arrayString = arrayListOf(getString(R.string.tax_mode_regular), getString(R.string.tax_mode_special))
        val taxMode = ArrayAdapter(requireContext(), R.layout.bottom_menu, arrayString)
        binding.tvTaxMode.setAdapter(taxMode)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
