package com.mkk.ru.presentation.screen.checks.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mkk.ru.presentation.screen.checks.viewpager.allchecks.AllChecksFragment
import com.mkk.ru.presentation.screen.checks.viewpager.currentshift.CurrentChecksFragment

class ChecksVIewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = AMOUNT_FRAGMENT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CurrentChecksFragment()
            1 -> AllChecksFragment()
            else -> CurrentChecksFragment()
        }
    }

    companion object {
        private const val AMOUNT_FRAGMENT = 2
    }
}
