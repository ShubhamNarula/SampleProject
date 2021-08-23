package com.techskaud.sampleapp.fragment.login

import androidx.fragment.app.viewModels
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.databinding.LoginFragmentBinding

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