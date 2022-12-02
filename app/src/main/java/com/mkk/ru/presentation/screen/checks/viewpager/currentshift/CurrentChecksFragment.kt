package com.mkk.ru.presentation.screen.checks.viewpager.currentshift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.databinding.FragmentCurrentChecksBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class CurrentChecksFragment : BaseFragment<CurrentChecksViewModel>() {

    private var _binding: FragmentCurrentChecksBinding? = null
    private val binding get() = _binding!!

    private val adapter = CurrentChecksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentChecksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        subscribeToViewModel()
    }

    private fun initUi() {
        with(binding) {
            rvCurrentChecks.adapter = adapter
        }
    }

    private fun subscribeToViewModel() {
        with(viewModel) {
            currentChecksFlow.onEach { checks ->
                adapter.submitList(checks)
            }
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
