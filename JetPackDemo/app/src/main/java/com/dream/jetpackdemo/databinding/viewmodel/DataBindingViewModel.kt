package com.dream.jetpackdemo.databinding.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/8
 */
class DataBindingViewModel: ViewModel() {

    var nameLiveData = MutableLiveData<String>()

    var observableFieldClick = ObservableField<View.OnClickListener>()

    var imageUrlLiveData = MutableLiveData<String>()

    var customText = "erdai66666"

    //通过 ViewModel 的方法绑定 click 事件
    fun click(){
        Log.d("erdai", "click: ViewModel")
    }

    fun click(name: String){
        Log.d("erdai", "click: $name")
    }

    fun click(view: View){
        Log.d("erdai", "click: View")
    }
}