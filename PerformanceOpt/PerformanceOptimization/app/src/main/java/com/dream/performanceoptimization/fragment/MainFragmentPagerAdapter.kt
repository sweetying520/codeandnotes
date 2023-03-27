package com.dream.performanceoptimization.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/24
 */
class MainFragmentPagerAdapter(
    activity: AppCompatActivity,
    var fragmentList: MutableList<Fragment>,
) : FragmentPagerAdapter(activity.supportFragmentManager) {

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "Tab1"
            1 -> "Tab2"
            2 -> "Tab3"
            else -> ""
        }
    }


}