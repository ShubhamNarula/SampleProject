package com.techskaud.sampleapp.fragment.paginglist

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.adapter.UserAdapter
import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.pagingsource.UserVM
import com.techskaud.sampleapp.response_model.ApiResponse
import kotlinx.android.synthetic.main.fragment_paging_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PagingListFragment : BaseFragment() {

   // private val viewModel: UserVM by viewModels()
    lateinit var userAdapter: UserAdapter
    lateinit var viewModel: UserVM

    override fun getLayoutID(): Int {
        return R.layout.fragment_paging_list
    }

    override fun onCreateView() {
        setViewModel()
        setAdapter()
        setupView()

    }

    private fun setViewModel()
    {
        viewModel =
            ViewModelProvider(
                this,
                PagingViewModuleFactory(ApiInterface.getApiService())
            )[UserVM::class.java]

    }


    private fun setupView() {

        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                userAdapter.submitData(it)
            }
        }

    }


    fun setAdapter() {
        userAdapter = UserAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = userAdapter
        }

    }


}