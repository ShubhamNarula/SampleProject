package com.apptunix.peng.model

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyViewPagerAdapter(manager: FragmentManager, val list: ArrayList<Fragment> = ArrayList(), val titleList: ArrayList<String> = ArrayList()) : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(position: Int): Fragment {
        return list[position]
    }
    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }
    fun addFragments(fragment: Fragment , title:String){
        list.add(fragment)
        titleList.add(title)
    }
    interface OnItemClick {
        fun onClick(view: View, position: Int, type: String)
    }
}