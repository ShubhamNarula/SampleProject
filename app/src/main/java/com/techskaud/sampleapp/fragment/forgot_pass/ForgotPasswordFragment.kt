package com.techskaud.sampleapp.fragment.forgot_pass

import androidx.fragment.app.viewModels
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.databinding.ForgotPassFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment() {
    lateinit var binding: ForgotPassFragBinding
    private val viewModel: ForgotPassVM by viewModels()
    override fun getLayoutID(): Int {
        return R.layout.forgot_pass_frag
    }

    override fun onCreateView() {
        binding = ForgotPassFragBinding.bind(requireView())
        binding.viewModel = viewModel
    }
}