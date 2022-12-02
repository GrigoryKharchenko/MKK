package com.mkk.ru.presentation.screen.checks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentChecksBinding
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.checks.adapter.ChecksVIewPagerAdapter

class ChecksFragment : BaseFragment<ChecksViewModel>() {

    private var _binding: FragmentChecksBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChecksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        with(binding) {
            viewPager.adapter = ChecksVIewPagerAdapter(childFragmentManager, lifecycle)
            val arrayTitle = arrayOf(getString(R.string.checks_current), getString(R.string.checks_all))
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = arrayTitle[position]
            }.attach()
            toolBar.setNavigationOnClickListener { goBack() }
        }
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
