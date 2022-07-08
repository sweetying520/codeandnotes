package com.dream.uieffectdemo.imedittext

/**
 * function: 群聊通用接口
 *
 * @author zy
 * @since 2022/7/7
 */
interface OnNavigationContactListener {
    /**
     * 跳转选择联系人
     *
     * @param onContactSelectResultListener 选择联系人结果监听
     */
    fun onNavigationContact(onContactSelectResultListener: OnContactSelectResultListener?)
}