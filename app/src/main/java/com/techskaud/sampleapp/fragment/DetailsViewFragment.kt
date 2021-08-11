package com.techskaud.sampleapp.fragment

import com.example.woohoo.base.BaseFragment
import com.techskaud.sampleapp.R
import com.techskaud.sampleapp.response_model.DataModel
import com.techskaud.sampleapp.utilities.Constants
import com.wh.woohoo.utils.extensionFunction.navigateBack
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailsViewFragment : BaseFragment() {
    private lateinit var dataModel: DataModel

    override fun getLayoutID(): Int {
        return R.layout.detail_fragment
    }

    override fun onCreateView() {
        init()
        setData()
        clickEvents()

    }
    fun init(){
        arguments.let {
            dataModel = it!!.getParcelable<DataModel>(Constants.DATA)!!
        }
    }
    fun setData(){
        txt_title.text = dataModel.title
        txt_body.text = dataModel.body
    }

    fun clickEvents(){
        btn_back.setOnClickListener {
            goBack()
        }
    }

}