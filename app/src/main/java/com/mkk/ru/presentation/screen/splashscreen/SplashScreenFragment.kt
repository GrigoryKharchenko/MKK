package com.mkk.ru.presentation.screen.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.mkk.ru.BuildConfig
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentSplashScreenBinding
import com.mkk.ru.extension.launchWhenStarted
import com.mkk.ru.presentation.base.BaseFragment
import com.mkk.ru.presentation.screen.registrationcashbox.RegistrationCashBoxFragment
import kotlinx.coroutines.flow.onEach

class SplashScreenFragment : BaseFragment<SplashScreenViewModel>() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvVersion.text =
            getString(R.string.splash_screen_fragment_app_version, BuildConfig.VERSION_NAME)
        viewModel.fragmentFlow.onEach {
            openFragment()
        }.launchWhenStarted(lifecycleScope, viewLifecycleOwner.lifecycle)
    }

    private fun openFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add<RegistrationCashBoxFragment>(R.id.container)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SplashScreenFragment()
    }
}
