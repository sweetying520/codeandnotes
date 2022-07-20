package com.dream.refresh.helper;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;


/**
 * function: 分页监听事件
 *
 * @author zy
 * @since 2022/7/20
 */
public interface PageLoadListener {


    /**
     * {@link SmartRefreshLayout}刷新回调，配合{@link BaseLoadHelper}finishWithData将会重新刷新整个RecyclerView
     *
     * @param size 刷新时，分页的起始位置，页码(使用{@link RecyclerViewHelper#pageNumberHelper(int)}永远为1)
     *             或者offset({使用@link RecyclerViewHelper#pageOffsetHelper(int)} (int)}永远为0)
     */
    void onRefresh(int size);

    /**
     * {@link SmartRefreshLayout}加载更多回调，进行分页加载
     *
     * @param size 即将要加载的下一页的页码(每次递增1)或offset(每次递增实际加载的大小)
     */
    void onLoadPage(int size);

}
