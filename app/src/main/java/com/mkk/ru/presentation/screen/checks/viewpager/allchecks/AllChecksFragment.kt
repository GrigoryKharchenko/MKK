package com.mkk.ru.presentation.screen.checks.viewpager.allchecks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.databinding.FragmentAllChecksBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class AllChecksFragment : BaseFragment<AllChecksViewModel>() {

    private var _binding: FragmentAllChecksBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { AllChecksAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllChecksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        subscribeToViewModel()
    }

    private fun initUi() {
        with(binding) {
            rvAllChecks.adapter = adapter
        }
    }

    private fun subscribeToViewModel() {
        with(viewModel) {
            allChecksFlow.onEach(adapter::submitList)
                .launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
