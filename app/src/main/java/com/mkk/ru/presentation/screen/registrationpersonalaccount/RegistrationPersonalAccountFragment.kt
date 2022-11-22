package com.mkk.ru.presentation.screen.registrationpersonalaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentRegistrationInPersonalAccountBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.replaceFragment
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.showDialog
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.menu.MenuFragment
import kotlinx.coroutines.flow.onEach

class RegistrationPersonalAccountFragment : BaseFragment<RegistrationPersonalAccountViewModel>() {

    private var _binding: FragmentRegistrationInPersonalAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationInPersonalAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnRegistration.safeOnClickListener {
                viewModel.checkValidation(
                    etLogin.text.toString(),
                    etPassword.text.toString()
                )
            }
            etLogin.doAfterTextChanged {
                tilLogin.error = null
            }
            etPassword.doAfterTextChanged {
                tilPassword.error = null
            }
        }
        viewModel.viewEffectFlow.onEach(::handleViewState)
            .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
    }

    private fun handleViewState(viewState: RegistrationPersonalAccountViewEffect) = with(viewState) {
        when (this) {
            is RegistrationPersonalAccountViewEffect.ErrorValidationUiModel -> {
                binding.tilLogin.error = emailMessage?.let(::getString)
                binding.tilPassword.error = passwordMessage?.let(::getString)
            }
            RegistrationPersonalAccountViewEffect.SuccessRegistration -> {
                binding.flProgress.isVisible = false
                showSuccessDialog()
            }
            RegistrationPersonalAccountViewEffect.FailureRegistration -> {
                binding.flProgress.isVisible = false
                showFailureDialog()
            }
            RegistrationPersonalAccountViewEffect.ShowProgressBar ->
                binding.flProgress.isVisible = true
        }
    }

    private fun showSuccessDialog() {
        showDialog(
            title = R.string.dialog_success_registration_in_personal_account_title,
            message = R.string.dialog_success_registration_in_personal_account_description,
            positiveButton = R.string.dialog_next_button,
            onClickPositiveButton = {
                replaceFragment<MenuFragment>(R.id.container)
            }
        )
    }

    private fun showFailureDialog() {
        showDialog(
            title = R.string.dialog_failure_registration_in_personal_account_title,
            message = R.string.dialog_failure_registration_in_personal_account_description,
            positiveButton = R.string.dialog_next_button,
            onClickPositiveButton = {}
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
