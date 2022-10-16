package com.mkk.ru.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkk.ru.BuildConfig
import com.mkk.ru.R
import com.mkk.ru.databinding.FragmentSplashScreenBinding
import com.mkk.ru.presentation.base.BaseFragment

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SplashScreenFragment()
    }
}
