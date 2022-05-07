package com.hung.wallup.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hung.wallup.databinding.M000SplashFragmentBinding
import com.hung.wallup.presentation.base.BaseActivity
import com.hung.wallup.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    companion object {
        val TAG: String = SplashFragment::class.java.name
        fun newInstance(): SplashFragment = SplashFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return M000SplashFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            goToHomeScreen()
        }, 3000)
    }

    private fun goToHomeScreen() {
        (activity as BaseActivity).showFragment(HomeFragment.TAG, HomeFragment.newInstance(), false)
    }
}