package com.dream.refresh.helper;

import androidx.recyclerview.widget.RecyclerView;

import com.dream.refresh.AppSmartRefreshLayout;
import com.dream.refresh.status.IStatusView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;


/**
 * function: 基础加载帮助类
 *
 * @author zy
 * @since 2022/7/20
 */
public abstract class BaseLoadHelper<T, H extends BaseLoadHelper> {

    protected final RecyclerViewHelper<T> mRecyclerHelper;

    protected IStatusView mStatusView;

    /**
     * 代理刷新监听者：真正的控制刷新流程的控制者
     */
    private OnRefreshLoadMoreListener delegateListener;

    /**
     * 标记是否刷新过
     */
    private boolean firstRefresh = true;

    /**
     * 是否是刷新数据
     */
    private boolean isRefresh;

    /**
     * 关闭加载更多
     */
    private boolean disableLoadMore;

    /**
     * 当数据为空时，是否展示StatusView的EmptyView
     */
    private boolean showEmptyWhenNoData = true;

    /**
     * 当recycler的Adapter有头部的时候是否显示StatusView的EmptyView
     */
    private boolean showEmptyWhenHasHeader = false;

    /**
     * 当刷新列表时已经没有更多数据的时候，是否关闭加载更多
     */
    private boolean mDisableLoadMoreWhenRefreshed = false;

    public BaseLoadHelper(RecyclerViewHelper<T> recyclerHelper) {
        this.mRecyclerHelper = recyclerHelper;
        mRecyclerHelper.mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoad());
    }


    /**
     * 当数据为空时，是否展示StatusView的EmptyView，默认展示
     *
     * @param showEmptyWhenNoData true展示，false不展示
     */
    @SuppressWarnings("unchecked")
    public H showEmptyWhenNoData(boolean showEmptyWhenNoData) {
        this.showEmptyWhenNoData = showEmptyWhenNoData;
        return (H) this;
    }

    /**
     * 当数据为空时，并且recycler的Adapter有头部的时候，是否展示StatusView的EmptyView，默认不展示
     *
     * @param showEmptyWhenHasHeader true展示，false不展示
     */
    public H showEmptyWhenHasHeader(boolean showEmptyWhenHasHeader) {
        this.showEmptyWhenHasHeader = showEmptyWhenHasHeader;
        return (H) this;
    }

    /**
     * 设置状态view，初始化或adapter为空的情况下会展示loading状态
     *
     * @param statusView 状态view
     */
    @SuppressWarnings("unchecked")
    public H statusView(IStatusView statusView) {
        this.mStatusView = statusView;
        boolean showLoading = allowShowLoading() || mRecyclerHelper.mAdapter.getData().isEmpty();
        if (mStatusView != null && showLoading) {
            mStatusView.showLoading();
        }
        return (H) this;
    }

    /**
     * 当刷新列表的时候，如果没有更多数据，则关闭加载更多
     *
     * @param disableLoadMoreWhenRefreshed true关闭加载更多，false不关闭加载更多，默认关闭
     */
    public H disableLoadMoreWhenRefreshed(boolean disableLoadMoreWhenRefreshed) {
        this.mDisableLoadMoreWhenRefreshed = disableLoadMoreWhenRefreshed;
        return (H) this;
    }

    /**
     * 刷新数据，调用此方法务必不要自己刷新adapter的数据，否则会出现重复数据
     *
     * @param data 以新数据刷新
     */
    protected void refreshData(List<T> data) {
        if (isRefresh()) {
            mRecyclerHelper.mAdapter.setNewData(data);
        } else {
            // 停止滚动，避免加载更多后自动滚动列表
            mRecyclerHelper.mRecyclerView.stopScroll();
            if (data != null && !data.isEmpty()) {
                mRecyclerHelper.mAdapter.addData(data);
            }
        }
        // 判断是否显示头部
        boolean showHeader = !showEmptyWhenHasHeader && mRecyclerHelper.mAdapter.getHeaderLayoutCount() > 0;
        // 列表为空时显示EmptyView
        boolean showEmpty = mRecyclerHelper.mAdapter.getData().isEmpty() && !showHeader;
        if (mStatusView != null && showEmptyWhenNoData && showEmpty) {
            mStatusView.showEmpty();
        } else {
            // 显示状态的内容View
            if (mStatusView != null) {
                mStatusView.showContent();
            }
            firstRefresh = false;
        }
        isRefresh = false;
    }

    /**
     * 结束刷新与加载，仅仅不展示刷新控件的刷新状态
     *
     * @param noMoreData 是否没有更多，true:没有更多了，false:还有更多
     */
    private void finishLoad(boolean noMoreData) {
        mRecyclerHelper.mRefreshLayout.finishRefresh();
        if (noMoreData) {
            mRecyclerHelper.mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            mRecyclerHelper.mRefreshLayout.finishLoadMore();
        }
    }

    /**
     * 结束刷新与加载，调用此方法务必不要自己刷新adapter的数据，否则会出现重复数据
     *
     * @param data       以新数据结束与加载
     * @param noMoreData 是否没有更多，true:没有更多了，false:还有更多
     */
    private void finishRefreshLoadMore(List<T> data, boolean noMoreData) {
        refreshData(data);
        finishLoad(noMoreData);
    }

    /**
     * 结束刷新与加载，调用此方法务必不要自己刷新adapter的数据，否则会出现重复数据
     *
     * @param data 以新数据结束刷新与加载
     */
    public void finishWithData(List<T> data) {
        finishWithData(data, data == null || data.isEmpty());
    }

    /**
     * 结束刷新与加载，调用此方法务必不要自己刷新adapter的数据，否则会出现重复数据
     *
     * @param data       以新数据结束与加载
     * @param noMoreData 是否没有更多，true:没有更多了，false:还有更多
     */
    public void finishWithData(List<T> data, boolean noMoreData) {

        /*
         * 如果是下拉刷新操作或者adapter列表为空的情况，代表新刷的数据，那么以下场景关闭加载更多
         * 1、新的数据列表为空；
         * 2、有新的数据，但是已经没有更多了，并且开启了关闭加载更多的开关。
         */
        boolean isRefresh = mRecyclerHelper.mAdapter.getData().isEmpty() || isRefresh();
        boolean isDisableLoadMore = (data == null || data.isEmpty()) || (mDisableLoadMoreWhenRefreshed && noMoreData);
        if (isRefresh && isDisableLoadMore) {
            // 取消加载更多
            finishWithDisableLoadMore(data);
        } else {
            /*
             * 刷新成功，打开以下开关
             */
            // 如果是刷新加载并且还有更多数据，则恢复其加载更多状态
            if (isRefresh() && !noMoreData) {
                // 下拉刷新，恢复加载更多的状态
                mRecyclerHelper.mRefreshLayout.resetNoMoreData();
            }
            // 如果加载更多为关闭状态，则打开
            if (disableLoadMore) {
                disableLoadMore = false;
                mRecyclerHelper.mRefreshLayout.setEnableLoadMore(true);
            }
            finishRefreshLoadMore(data, noMoreData);
        }
    }

    /**
     * 结束刷新与加载时，并关闭加载更多，调用此方法务必不要自己刷新adapter的数据，否则会出现重复数据
     * 该方法只对{@link AppSmartRefreshLayout}有效
     *
     * @param data 以新数据结束刷新与加载
     */
    public void finishWithDisableLoadMore(List<T> data) {
        AppSmartRefreshLayout refreshLayout = null;
        if (mRecyclerHelper.mRefreshLayout instanceof AppSmartRefreshLayout) {
            refreshLayout = (AppSmartRefreshLayout) mRecyclerHelper.mRefreshLayout;
        }
        if (refreshLayout != null && refreshLayout.isEnableLoadMore()) {
            disableLoadMore = true;
            mRecyclerHelper.mRefreshLayout.setEnableLoadMore(false);
        }
        finishRefreshLoadMore(data, false);
    }

    /**
     * 完成刷新，但是数据失败，如果adapter列表为空将会展示StatusView的错误页面；
     * 将会回调{@link com.dream.refresh.status.StatusView#showError(CharSequence)}方法
     */
    public void finishWithError(CharSequence errorMessage) {
        if (isRefresh) {
            mRecyclerHelper.mRefreshLayout.finishRefresh(false);
        } else {
            mRecyclerHelper.mRefreshLayout.finishLoadMore(false);
            mRecyclerHelper.mRefreshLayout.closeHeaderOrFooter();
        }
        // 如果为空让错误空缺页，否则只结束刷新
        if (mStatusView != null && mRecyclerHelper.mAdapter.getData().isEmpty()) {
            if (errorMessage != null) {
                mStatusView.showError(errorMessage);
            } else {
                mStatusView.showError();
            }
        }
    }

    /**
     * 完成刷新，但是数据失败，如果adapter列表为空将会展示StatusView的错误页面；
     * 将会回调{@link com.dream.refresh.status.StatusView#showError()}方法
     */
    public void finishWithError() {
        finishWithError(null);
    }

    /**
     * 强制刷新列表
     * {@link #refresh(boolean)})}
     */
    public void refresh() {
        refresh(true);
    }

    /**
     * 刷新列表
     *
     * @param force 是否强制刷新，true调用刷新控件直接刷新，不会有动画效果；false调用{@link com.scwang.smartrefresh.layout.SmartRefreshLayout#autoRefresh()}
     */
    public void refresh(boolean force) {
        if (force && mRecyclerHelper.mRefreshLayout instanceof AppSmartRefreshLayout) {
            ((AppSmartRefreshLayout) mRecyclerHelper.mRefreshLayout).refresh();
        } else {
            mRecyclerHelper.mRefreshLayout.autoRefresh();
        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerHelper.mRecyclerView;
    }

    /**
     * 此方法在刷新adapter数据时调用，是刷新还是加载更多
     *
     * @return true刷新控件，false加载更多
     */
    protected boolean isRefresh() {
        return isRefresh;
    }

    /**
     * 是否显示loading状态
     *
     * @return 显示loading状态
     */
    private boolean allowShowLoading() {
        return firstRefresh || IStatusView.STATUS_ERROR == mStatusView.getStatus();
    }

    /**
     * 提供刷新与加载更多事件
     *
     * @return 刷新加载更多事件
     */
    protected abstract OnRefreshLoadMoreListener provideOnRefreshLoadMoreListener();

    /**
     * 控制statusView的刷新加载监听者
     */
    private class OnRefreshLoad implements OnRefreshLoadMoreListener {

        @Override
        public void onRefresh(RefreshLayout refreshLayout) {
            if (delegateListener == null) {
                delegateListener = provideOnRefreshLoadMoreListener();
            }
            isRefresh = true;
            // 显示状态View加载中的状态
            if (mStatusView != null && allowShowLoading()) {
                mStatusView.showLoading();
            }
            firstRefresh = false;
            if (delegateListener != null) {
                delegateListener.onRefresh(refreshLayout);
            }
        }

        @Override
        public void onLoadMore(RefreshLayout refreshLayout) {
            if (delegateListener == null) {
                delegateListener = provideOnRefreshLoadMoreListener();
            }
            isRefresh = false;
            if (delegateListener != null) {
                delegateListener.onLoadMore(refreshLayout);
            }
        }
    }

}
