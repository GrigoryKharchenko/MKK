package com.mkk.ru.presentation.screen.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentMenuBinding
import com.mkk.ru.databinding.MenuDialogBinding
import com.mkk.ru.extension.addFragment
import com.mkk.ru.extension.isValidFullName
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.setStatusBarColor
import com.mkk.ru.extension.showDialog
import com.mkk.ru.extension.showSnackbar
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.checks.ChecksFragment
import com.mkk.ru.presentation.screen.sale.SaleFragment
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
        initUi()
        subscribeToViewModel()
        setStatusBarColor(R.color.teal_basic)
    }

    private fun initUi() {
        with(binding) {
            btnOpenShift.safeOnClickListener {
                showNameCashierDialog()
            }
            btnCloseShift.safeOnClickListener {
                showCloseShiftDialog()
            }
            btnSale.safeOnClickListener {
                addFragment<SaleFragment>(R.id.container)
            }
            btnChecks.safeOnClickListener {
                addFragment<ChecksFragment>(R.id.container)
            }
        }
    }

    private fun subscribeToViewModel() {
        with(viewModel) {
            stateShiftFlow.onEach(::handleShiftState)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    private fun handleShiftState(shiftState: Boolean) {
        with(binding) {
            groupBtnOpenShift.isVisible = shiftState
            btnOpenShift.isGone = shiftState
            btnCloseShift.isVisible = shiftState
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
            onClickPositiveButton = { viewModel.setStateShift(false) },
        )
    }

    private fun showNameCashierDialog() {
        val bindingDialog = MenuDialogBinding.inflate(layoutInflater)
        val customDialogBuilder = MaterialAlertDialogBuilder(requireContext())
            .setView(bindingDialog.root)
            .show()
        bindingDialog.tvNext.setOnClickListener {
            val etNameCashier = bindingDialog.etNameCashier.text.toString()
            if (etNameCashier.isValidFullName()) {
                viewModel.setStateShift(true)
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
