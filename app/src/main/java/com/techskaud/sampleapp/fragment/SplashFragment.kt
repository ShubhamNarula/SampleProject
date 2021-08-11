package com.techskaud.sampleapp.fragment

import android.os.Handler
import android.os.Looper
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.databinding.SplashFragmentBinding
import com.wh.woohoo.utils.extensionFunction.navigateWithId

class SplashFragment : BaseFragment() {
    lateinit var binding: SplashFragmentBinding
    override fun getLayoutID(): Int {
        return R.layout.splash_fragment
    }

    override fun onCreateView() {
        binding = SplashFragmentBinding.bind(requireView())
        navigateUpto()
    }
    /**
     * Navigate to using Handler
     * */
    private fun navigateUpto() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.root.navigateWithId(R.id.action_splashFragment_to_dataFragment)
        }, 1000)
    }
}