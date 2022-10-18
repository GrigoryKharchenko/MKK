package com.mkk.ru.presentation.screen.registrationcashbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkk.ru.databinding.FragmentRegistrationCashBoxBinding
import com.mkk.ru.presentation.base.BaseFragment

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = RegistrationCashBoxFragment()
    }
}
