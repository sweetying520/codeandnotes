package com.dream.uieffectdemo.baservadapterhelper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

/**
 * function: PullUpLoadingViewModel
 *
 * @author zy
 * @since 2022/7/20
 */
class PullUpLoadingViewModel: ViewModel() {

    var pageNum = 1

    var mDataListLiveData: MutableLiveData<MutableList<String>?> = MutableLiveData()

    fun getNetWorkDataList(){
        thread {
            if(pageNum == 1){
                Thread.sleep(1000)
            }
            val targetList = mutableListOf<String>()
            for(i in 0..9){
                targetList.add("数据：$pageNum $i")
            }
            if(pageNum == 5){
                mDataListLiveData.postValue(null)
                return@thread
            }
            mDataListLiveData.postValue(targetList)
            pageNum++
        }
    }

    fun onRefresh() {
        pageNum = 1
        getNetWorkDataList()
    }

    fun onLoadMore() {
        getNetWorkDataList()
    }
}