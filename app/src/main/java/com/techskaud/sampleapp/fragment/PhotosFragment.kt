package com.techskaud.sampleapp.fragment

import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.adapter.RecyclerAdapter
import com.techskaud.sampleapp.response_model.AlbumModel
import com.techskaud.sampleapp.utilities.DataState
import com.techskaud.sampleapp.viewmodel.BaseViewModel
import com.techskaud.sampleapp.viewmodel.MainStateEvent
import com.wh.woohoo.utils.extensionFunction.navigateWithId
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.data_photos_frag.*

@AndroidEntryPoint
class PhotosFragment : BaseFragment(),RecyclerAdapter.OnItemClick {
    private val viewModel: BaseViewModel by viewModels()
    val dataAdapter by lazy { RecyclerAdapter<AlbumModel>(R.layout.album_data) }
    override fun getLayoutID(): Int {
        return R.layout.data_photos_frag
    }

    override fun onCreateView() {
        subscribeObservers()
        init()
    }
    fun subscribeObservers(){
        viewModel.dataStateForPhoto.observe(requireActivity(), Observer { dataState ->
            when (dataState) {
                is DataState.Success<AlbumModel> -> {
                    setAdapter(dataState.data)
                }
            }
        })
    }

    fun init(){
        viewModel.getPhotos(MainStateEvent.getDataEvents,requireActivity())
    }
    fun setAdapter(dataList : List<AlbumModel>){
        dataAdapter.addItems(
            dataList
        )
        dataAdapter.setOnItemClick(this)
        rv_data.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            val decoration  = DividerItemDecoration(requireActivity(), LinearLayout.VERTICAL)
            addItemDecoration(decoration)
            adapter = dataAdapter
        }
    }

    override fun onClick(position: Int, view: View) {
        requireView().navigateWithId(R.id.action_photosFragment_to_loginFragment)
    }
}