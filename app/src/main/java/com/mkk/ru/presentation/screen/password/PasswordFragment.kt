package com.mkk.ru.presentation.screen.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentPasswordBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.replaceFragment
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.menu.MenuFragment
import kotlinx.coroutines.flow.onEach

class PasswordFragment : BaseFragment<PasswordViewModel>() {

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        subscribeToViewModel()
    }

    private fun initUi() {
        with(binding) {
            tvExit.setOnClickListener {
                requireActivity().finish()
            }
            btnOne.setOnClickListener {
                viewModel.setValuePassword("1")
            }
            btnTwo.setOnClickListener {
                viewModel.setValuePassword("2")
            }
            btnThree.setOnClickListener {
                viewModel.setValuePassword("3")
            }
            btnFour.setOnClickListener {
                viewModel.setValuePassword("4")
            }
            btnFive.setOnClickListener {
                viewModel.setValuePassword("5")
            }
            btnSix.setOnClickListener {
                viewModel.setValuePassword("6")
            }
            btnSeven.setOnClickListener {
                viewModel.setValuePassword("7")
            }
            btnEight.setOnClickListener {
                viewModel.setValuePassword("8")
            }
            btnNine.setOnClickListener {
                viewModel.setValuePassword("9")
            }
            btnZero.setOnClickListener {
                viewModel.setValuePassword("0")
            }
            btnDelete.setOnClickListener {
                viewModel.deleteValuePassword()
            }
            btnContinue.setOnClickListener {
                viewModel.savePassword()
            }
        }
    }

    private fun subscribeToViewModel() {
        with(viewModel) {
            dotFlow.onEach(::handleUiState)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            textFlow.onEach(::handleUiState)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            nextScreenFlow.onEach {
                replaceFragment<MenuFragment>(R.id.container)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }


    private fun handleUiState(state: PasswordUiState) {
        with(binding) {
            when (state) {
                is PasswordUiState.DotUiState -> {
                    ivFirstDot.setColorFilter(ContextCompat.getColor(requireContext(), state.enabledFirst.tint))
                    ivSecondDot.setColorFilter(ContextCompat.getColor(requireContext(), state.enabledSecond.tint))
                    ivThirdDot.setColorFilter(ContextCompat.getColor(requireContext(), state.enabledThird.tint))
                    ivFourthDot.setColorFilter(ContextCompat.getColor(requireContext(), state.enabledFourth.tint))
                }
                is PasswordUiState.TextUiState -> {
                    tvCreate.isVisible = state.isVisibleCreate
                    btnContinue.isVisible = state.isVisibleContinue
                    tvEnterCode.isVisible = state.isVisibleEnterCode
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
