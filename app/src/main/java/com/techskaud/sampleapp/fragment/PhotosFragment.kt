package com.techskaud.sampleapp.fragment

import android.os.Build
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.adapter.AlbumAdapter
import com.techskaud.sampleapp.response_model.AlbumModel
import com.techskaud.sampleapp.utilities.DataState
import com.techskaud.sampleapp.viewmodel.BaseViewModel
import com.techskaud.sampleapp.viewmodel.MainStateEvent
import com.wh.woohoo.utils.extensionFunction.navigateWithId
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.data_fragment.*
@AndroidEntryPoint
class PhotosFragment : BaseFragment() {
    private val viewModel: BaseViewModel by viewModels()
    override fun getLayoutID(): Int {
        return R.layout.data_fragment
    }

    override fun onCreateView() {
        subscribeObservers()
        init()
    }
    fun subscribeObservers(){
        viewModel.dataStateForPhoto.observe(requireActivity(), Observer { dataState ->
            when (dataState) {
                is DataState.Success<AlbumModel> -> {
                    if (dataState.data!=null){
                        setAdapter(dataState.data)

                    }
                }
            }
        })
    }

    fun init(){
        viewModel.getPhotos(MainStateEvent.getDataEvents,requireActivity())
    }
    fun setAdapter(dataList : List<AlbumModel>){
        rv_data.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            val decoration  = DividerItemDecoration(requireActivity(), LinearLayout.VERTICAL)
            addItemDecoration(decoration)
            adapter = AlbumAdapter(dataList,{
                requireView().navigateWithId(R.id.action_photosFragment_to_userProfile)
            })

        }
    }
}