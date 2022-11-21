package com.mkk.ru.presentation.screen.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentSaleBinding
import com.mkk.ru.extension.setStatusBar
import com.mkk.ru.presentation.base.BaseFragment

class SaleFragment : BaseFragment<SaleViewModel>() {

    private var _binding: FragmentSaleBinding? = null
    private val binding get() = _binding!!

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
        setStatusBar(R.color.dark_green)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
