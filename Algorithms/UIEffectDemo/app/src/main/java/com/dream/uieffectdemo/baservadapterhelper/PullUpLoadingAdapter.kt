package com.dream.uieffectdemo.baservadapterhelper

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dream.uieffectdemo.R

/**
 * function: PullUpLoadingAdapter
 *
 * @author zy
 * @since 2022/7/20
 */
class PullUpLoadingAdapter: BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_pull_up_loading_view,null) {


    override fun convert(helper: BaseViewHolder, item: String) {
        Log.d("erdai", "convert: ${helper.hashCode()}")
        helper.setText(R.id.tv,item)
    }
}