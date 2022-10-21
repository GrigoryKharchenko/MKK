package com.mkk.ru.presentation.screen.requestacceptance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkk.ru.databinding.FragmentRequestAcceptanceBinding
import com.mkk.ru.presentation.base.BaseFragment

class RequestAcceptanceFragment : BaseFragment<RequestAcceptanceViewModel>() {

    private var _binding: FragmentRequestAcceptanceBinding? = null
    private val binding get() = _binding!!
        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            _binding = FragmentRequestAcceptanceBinding.inflate(layoutInflater)
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
