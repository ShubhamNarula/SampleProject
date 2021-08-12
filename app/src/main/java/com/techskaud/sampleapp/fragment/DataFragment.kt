package com.techskaud.sampleapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woohoo.base.BaseFragment
import com.google.gson.Gson
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.adapter.DataAdapter
import com.techskaud.sampleapp.datastore.DataStoreClass
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
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DataFragment : BaseFragment() {
    private val viewModel: BaseViewModel by viewModels()
    private var mList : List<DataModel> = arrayListOf()
    private val saveDataViewModel: SaveDataViewModel by viewModels()
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
                    if (dataState.data!=null){
                        setAdapter(dataState.data)
                        mList=dataState.data
                        saveDataViewModel.saveToLocal(dataState.data.toString())

                    }
                }
            }
        })
    }
    fun init(){
        lifecycleScope.launchWhenStarted {
            saveDataViewModel.readFromLocal.collect {
                Log.e("Data", "init: $it", )
            }
        }
        viewModel.getData(MainStateEvent.getDataEvents, requireActivity())

    }

     fun setAdapter(dataList : List<DataModel>){
        rv_data.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            val decoration  = DividerItemDecoration(requireActivity(), VERTICAL)
            addItemDecoration(decoration)
            adapter = DataAdapter(dataList,{
                val bundle = Bundle()
                bundle.putParcelable(Constants.DATA,it)
                requireView().navigateWithId(R.id.action_dataFragment_to_detailsViewFragment,bundle = bundle)
            })

        }
    }


}