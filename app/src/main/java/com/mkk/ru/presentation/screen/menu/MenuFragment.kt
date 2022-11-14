package com.mkk.ru.presentation.screen.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentMenuBinding
import com.mkk.ru.databinding.MenuDialogBinding
import com.mkk.ru.extension.isValidFullName
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.showDialog
import com.mkk.ru.extension.showSnackbar
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
                showNameCashierDialog()
            }
            btnCloseShift.safeOnClickListener {
                showCloseShiftDialog()
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

    private fun showCloseShiftDialog() {
        showDialog(
            title = R.string.menu_dialog_close_shift_title,
            message = R.string.menu_dialog_close_shift_description,
            positiveButton = R.string.menu_dialog_close_shift_pos_button,
            negativeButton = R.string.dialog_cancel_button,
            onClickPositiveButton = { showShiftClosedDialog() }
        )
    }

    private fun showShiftClosedDialog() {
        showDialog(
            title = R.string.menu_dialog_shift_closed_title,
            message = R.string.menu_dialog_shift_closed_description,
            positiveButton = R.string.dialog_ok_button,
            onClickPositiveButton = { viewModel.changeShift(false) },
        )
    }

    private fun showNameCashierDialog() {
        val bindingDialog = MenuDialogBinding.inflate(layoutInflater)
        val customDialogBuilder = MaterialAlertDialogBuilder(requireContext())
            .setView(bindingDialog.root)
            .show()
        bindingDialog.tvNext.setOnClickListener {
            val editText = bindingDialog.etNameCashier.text.toString()
            if (editText.isValidFullName()) {
                viewModel.changeShift(true)
                showSnackbar(R.string.menu_snackbar_open_shift)
                customDialogBuilder.dismiss()
            } else {
                bindingDialog.tilNameCashier.error = getString(R.string.menu_dialog_invalid_name_cashier)
            }
        }
        bindingDialog.tvCancel.setOnClickListener {
            customDialogBuilder.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
