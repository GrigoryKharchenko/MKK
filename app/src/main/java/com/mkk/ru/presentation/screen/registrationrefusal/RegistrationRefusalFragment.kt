package com.mkk.ru.presentation.screen.registrationrefusal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkk.ru.databinding.FragmentRegistrationRefusalBinding
import com.mkk.ru.presentation.base.BaseFragment

class RegistrationRefusalFragment : BaseFragment<RegistrationRefusalViewModel>() {

    private var _binding: FragmentRegistrationRefusalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationRefusalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
