package com.dream.refresh;

import com.scwang.smartrefresh.layout.api.RefreshFooter;

import android.view.View;

/**
 * function: App底部接口，对SmartRefreshLayout底部的扩展
 *
 * @author zy
 * @since 2022/7/20
 */
public interface AppRefreshFooter extends RefreshFooter {

    /**
     * 是否保持底部固定，不回弹
     *
     * @return 是否固定底部
     */
    boolean holderFooterView();


    /**
     * 设置没有更多了展示View
     *
     * @param noMoreView 没有更多View
     */
    AppRefreshFooter setNoMoreView(View noMoreView);


    /**
     * 设置出错展示View
     *
     * @param errorView 出错View
     */
    AppRefreshFooter setErrorView(View errorView);

    /**
     * 是否已经没有更多数据
     * @return true没有更多数据，false还有更多数据
     */
    boolean isNoMoreData();

    /**
     * 获取没有更多数据View
     * @return 没有更多数据View
     */
    View getNoMoreView();


}
