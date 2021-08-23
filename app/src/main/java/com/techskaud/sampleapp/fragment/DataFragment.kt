package com.techskaud.sampleapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.adapter.RecyclerAdapter
import com.techskaud.sampleapp.response_model.DataModel
import com.techskaud.sampleapp.utilities.Constants
import com.techskaud.sampleapp.utilities.DataState
import com.techskaud.sampleapp.viewmodel.BaseViewModel
import com.techskaud.sampleapp.viewmodel.MainStateEvent
import com.techskaud.sampleapp.viewmodel.SaveDataViewModel
import com.wh.woohoo.utils.extensionFunction.navigateWithId
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.data_fragment.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DataFragment : BaseFragment(), RecyclerAdapter.OnItemClick {
    private val viewModel: BaseViewModel by viewModels()
    private var mList: List<DataModel> = arrayListOf()
    private val saveDataViewModel: SaveDataViewModel by viewModels()
    val dataAdapter by lazy { RecyclerAdapter<DataModel>(R.layout.item_view_data) }

    override fun getLayoutID(): Int {
        return R.layout.data_fragment
    }

    @InternalCoroutinesApi
    override fun onCreateView() {
        subscribeObservers()
        init()
    }

    @InternalCoroutinesApi
    @SuppressLint("NotifyDataSetChanged")
    fun subscribeObservers() {
        //here are three state of api response success,error,loading but we calling only one state is success we can call other as we need
        viewModel.dataState.observe(requireActivity(), Observer { dataState ->
            when (dataState) {
                is DataState.Success<DataModel> -> {

                    setAdapter(dataState.data)
                    mList = dataState.data
                    saveDataViewModel.saveToLocal(dataState.data.toString())
                }
            }
        })
    }

    fun init() {
        lifecycleScope.launchWhenStarted {
            saveDataViewModel.readFromLocal.collect {
                Log.e("Data", "init: $it")
            }
        }
        viewModel.getData(MainStateEvent.getDataEvents, requireActivity())

    }

    fun setAdapter(dataList: List<DataModel>) {
        dataAdapter.addItems(
            dataList
        )
        dataAdapter.setOnItemClick(this)
        rv_data.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = dataAdapter
        }

    }

    override fun onClick(position: Int,view:View) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.DATA, mList[position])
        requireView().navigateWithId(
            R.id.action_dataFragment_to_detailsViewFragment,
            bundle = bundle
        )
    }
}