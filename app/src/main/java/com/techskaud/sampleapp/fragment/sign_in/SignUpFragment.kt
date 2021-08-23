package com.techskaud.sampleapp.fragment.sign_in

import androidx.fragment.app.viewModels
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.databinding.SingUpFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {
    lateinit var binding: SingUpFragmentBinding
    private val viewModel: SignUpVM by viewModels()

    override fun getLayoutID(): Int {
        return R.layout.sing_up_fragment
    }

    override fun onCreateView() {
        binding = SingUpFragmentBinding.bind(requireView())
        binding.vm = viewModel
    }
}