package com.mkk.ru.presentation.screen.claimstatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentClaimStatusBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.extension.safeOnClickListener
import com.mkk.ru.extension.showSnackbar
import com.mkk.ru.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class ClaimStatusFragment : BaseFragment<ClaimStatusViewModel>() {

    private var _binding: FragmentClaimStatusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClaimStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnClose.setOnClickListener {
                requireActivity().finish()
            }
            btnUpdate.safeOnClickListener {
                viewModel.updateRequest()
            }
        }
        with(viewModel) {
            statusProgressBarFlow.onEach { statusProgressBar ->
                binding.flProgress.isVisible = statusProgressBar
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            currentDateFlow.onEach { currentDate ->
                binding.tvLastUpdateClaim.text = getString(R.string.claim_status_date, currentDate)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
            showSnackBarFlow.onEach { snackBar ->
                showSnackbar(snackBar)
            }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
