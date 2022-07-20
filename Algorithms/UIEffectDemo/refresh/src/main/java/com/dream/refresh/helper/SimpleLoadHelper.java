package com.dream.refresh.helper;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * function: 普通简单的刷新控件
 *
 * @author zy
 * @since 2022/7/20
 */
public class SimpleLoadHelper<T> extends BaseLoadHelper<T, SimpleLoadHelper<T>> {

    private OnRefreshListener mOnRefreshListener;

    private OnLoadMoreListener mLoadMoreListener;

    SimpleLoadHelper(RecyclerViewHelper<T> recyclerHelper) {
        super(recyclerHelper);
    }

    /**
     * 下拉刷新和加载更多事件
     */
    public SimpleLoadHelper<T> onRefreshLoadMoreListener(OnRefreshLoadMoreListener listener) {
        this.mOnRefreshListener = listener;
        this.mLoadMoreListener = listener;
        return this;
    }

    /**
     * 刷新事件
     */
    public SimpleLoadHelper<T> onRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
        return this;
    }

    /**
     * 加载更多事件
     */
    public SimpleLoadHelper<T> onLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
        return this;
    }

    @Override
    protected OnRefreshLoadMoreListener provideOnRefreshLoadMoreListener() {
        return new OnRefreshLoadMoreListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onRefresh(refreshLayout);
                }
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore(refreshLayout);
                }
            }
        };
    }
}
