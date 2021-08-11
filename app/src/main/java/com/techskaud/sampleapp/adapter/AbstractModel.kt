package com.apptunix.peng.model

abstract class AbstractModel {

    var adapterPosition: Int = -1
    var onItemClick: MyViewPagerAdapter.OnItemClick? = null
}