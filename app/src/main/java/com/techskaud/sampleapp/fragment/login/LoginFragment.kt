package com.techskaud.sampleapp.fragment.login

import androidx.fragment.app.viewModels
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginVM by viewModels()
    override fun getLayoutID(): Int {
        return R.layout.login_fragment
    }

    override fun onCreateView() {
        binding = LoginFragmentBinding.bind(requireView())
        binding.vm = viewModel
    }
}