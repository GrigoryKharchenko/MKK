package com.mkk.ru.presentation.screen.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.databinding.FragmentMenuBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class MenuFragment : BaseFragment<MenuViewModel>() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnOpenShift.safeOnClickListener {
                viewModel.changeShift(true)
            }
            btnCloseShift.safeOnClickListener {
                viewModel.changeShift(false)
            }
        }
        viewModel.viewEffectsFlow.onEach(::handleViewEffect)
            .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
    }

    private fun handleViewEffect(viewEffects: MenuViewEffects) {
        when (viewEffects) {
            is MenuViewEffects.ChangeShift -> {
                binding.groupBtnOpenShift.isVisible = viewEffects.isOpenedShift
            }
        }
    }
}

