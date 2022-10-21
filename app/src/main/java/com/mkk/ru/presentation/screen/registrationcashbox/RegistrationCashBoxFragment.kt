package com.mkk.ru.presentation.screen.registrationcashbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentRegistrationCashBoxBinding
import com.mkk.ru.domain.model.SubdivisionModel
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.showSnackbar
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.requestacceptance.RequestAcceptanceFragment
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
            btnSendRequest.setOnClickListener {
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
            showSnackbarFlow.onEach {
                showSnackbar(it)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            openRequestAcceptanceFragmentFlow.onEach {
                openRequestAcceptanceFragment()
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            subdivisionsFlow.onEach {
                setSubdivision(it)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            statusProgressBarFlow.onEach {
                binding.flProgress.isVisible = it
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
        setTaxMode()
    }

    private fun setSubdivision(subdivisionModel: List<SubdivisionModel>) {
        val subdivisionAdapter =
            ArrayAdapter(requireContext(), R.layout.bottom_menu, subdivisionModel.map { it.name })
        binding.tvDislocationTax.setAdapter(subdivisionAdapter)
    }

    private fun setTaxMode() {
        val arrayString = arrayListOf(getString(R.string.tax_mode_regular), getString(R.string.tax_mode_special))
        val taxMode = ArrayAdapter(requireContext(), R.layout.bottom_menu, arrayString)
        binding.tvTaxMode.setAdapter(taxMode)
    }

    private fun openRequestAcceptanceFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(TAG)
            replace<RequestAcceptanceFragment>(R.id.container)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "RegistrationCashBoxFragment"
    }
}
