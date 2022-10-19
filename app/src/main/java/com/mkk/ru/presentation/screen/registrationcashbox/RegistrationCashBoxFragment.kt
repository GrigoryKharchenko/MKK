package com.mkk.ru.presentation.screen.registrationcashbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentRegistrationCashBoxBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.presentation.base.BaseFragment
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
        getSubdivision()
    }

    private fun getSubdivision() {
        viewModel.subdivisionsFlow.onEach {
            val subdivisionAdapter =
                ArrayAdapter(requireContext(), R.layout.bottom_menu, it)
            binding.tvDislocationTax.setAdapter(subdivisionAdapter)
        }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = RegistrationCashBoxFragment()
    }
}
