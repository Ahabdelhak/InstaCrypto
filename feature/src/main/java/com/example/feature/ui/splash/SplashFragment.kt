package com.example.feature.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.feature.R
import com.example.feature.core.BaseFragment
import com.example.feature.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(){

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {

            binding.imgAppLogo.apply {
                alpha = 0f
                animate().alpha(1f).duration = 2000
            }

            delay(3000)
            findNavController().navigate(R.id.coinsResultFragment)
        }
    }
}