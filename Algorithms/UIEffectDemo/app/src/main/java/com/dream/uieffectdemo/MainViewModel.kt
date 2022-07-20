package com.dream.uieffectdemo

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel

/**
 * function: MainViewModel
 *
 * @author zy
 * @since 2022/7/4
 */
class MainViewModel(application: Application): AndroidViewModel(application) {


    var baseQuickAdapterClick: View.OnClickListener? = null
}