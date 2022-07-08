package com.dream.uieffectdemo.imedittext

/**
 * function: 联系人选择结果监听
 *
 * @author zy
 * @since 2022/7/7
 */
interface OnContactSelectResultListener {
    /**
     * 联系人结果
     *
     * @param result 选择联系人结果
     */
    fun onContactSelectResult(result: Map<String?, String?>?)
}