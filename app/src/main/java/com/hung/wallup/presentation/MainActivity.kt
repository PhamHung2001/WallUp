package com.hung.wallup.presentation

import android.os.Bundle
import com.hung.wallup.databinding.ActivityMainBinding
import com.hung.wallup.presentation.base.BaseActivity
import com.hung.wallup.presentation.splash.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        showFragment(SplashFragment.TAG, SplashFragment.newInstance(), false)
    }
}
