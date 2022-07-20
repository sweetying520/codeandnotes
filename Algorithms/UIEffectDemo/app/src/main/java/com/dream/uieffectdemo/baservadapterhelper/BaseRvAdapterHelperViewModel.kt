package com.dream.uieffectdemo.baservadapterhelper

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel

/**
 * function: BaseRvAdapterHelperViewModel
 *
 * @author zy
 * @since 2022/7/20
 */
class BaseRvAdapterHelperViewModel(application: Application): AndroidViewModel(application) {

    var click1: View.OnClickListener? = null

}