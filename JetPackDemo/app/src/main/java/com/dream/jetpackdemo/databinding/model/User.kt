package com.dream.jetpackdemo.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/10
 */
class User : BaseObservable() {
    var name: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var pwd: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.pwd)
        }
}