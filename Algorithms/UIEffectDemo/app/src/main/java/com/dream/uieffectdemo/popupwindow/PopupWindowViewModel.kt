package com.dream.uieffectdemo.popupwindow

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * function: PopupWindowViewModel
 *
 * @author zy
 * @since 2022/7/4
 */
class PopupWindowViewModel(application: Application) : AndroidViewModel(application) {

    fun mockDataList(): MutableList<String> {
        val mDataList = mutableListOf<String>()
        for (i in 1..20) {
            mDataList.add("$i: First things first, would you like me to help you calculate your coverage needs?")
        }
        return mDataList
    }


}