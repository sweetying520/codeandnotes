package com.dream.refresh.helper;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * function: 分页加载帮助类
 *
 * @author zy
 * @since 2022/7/20
 */
public class PageLoadHelper<T> extends BaseLoadHelper<T, PageLoadHelper<T>> {

    /**
     * 分页量，即每页的数量大小
     */
    private final int pageSize;

    /**
     * 页码，或为页码大小或为数据列表的offset，具体看实现类
     */
    private int size = 0;

    /**
     * 正在加载的页码
     */
    private int loadPage = 0;

    /**
     * 分页监听
     */
    private PageLoadListener mPageLoadListener;

    /**
     * 分页算法
     */
    private PageAlgorithm mPageAlgorithm;

    /**
     * @param pageSize           一页的加载量大小
     * @param pageAlgorithm      分页算法
     * @param recyclerViewHelper RecyclerViewHelper
     */
    PageLoadHelper(int pageSize, PageAlgorithm pageAlgorithm, RecyclerViewHelper<T> recyclerViewHelper) {
        super(recyclerViewHelper);
        this.mPageAlgorithm = pageAlgorithm;
        this.pageSize = pageSize;
    }

    /**
     * 设置分页监听
     */
    @SuppressWarnings("unchecked")
    public PageLoadHelper<T> pageLoadListener(PageLoadListener pageLoadListener) {
        mPageLoadListener = pageLoadListener;
        return this;
    }

    @Override
    protected OnRefreshLoadMoreListener provideOnRefreshLoadMoreListener() {
        return new OnRefreshLoadMoreListener() {

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadPage = nextPageIncrement();
                if (mPageLoadListener != null) {
                    mPageLoadListener.onRefresh(loadPage);
                }
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadPage += nextPageIncrement();
                if (mPageLoadListener != null) {
                    mPageLoadListener.onLoadPage(loadPage);
                }
            }
        };
    }

    @Override
    protected boolean isRefresh() {
        return super.isRefresh() || size == 0;
    }

    /**
     * 结束刷新，调用此方法务必不要自己刷新adapter的数据，否则会出现重复数据
     *
     * @param data 若data.size < pageSize，即新数据少于每页数据，则会显示是否更多
     */
    @Override
    public void finishWithData(List<T> data) {
        boolean noMoreData = data == null || data.size() < pageSize;
        finishWithData(data, noMoreData);
    }

    /**
     * 获取当前加载的分页，或为页码大小或为数据列表的offset
     */
    public int getSize() {
        return loadPage;
    }

    /**
     * 数据刷新后计算页码
     */
    @Override
    protected void refreshData(List<T> data) {
        super.refreshData(data);
        /*
         * 1、恢复到加载前上一页
         */
        size = loadPage - nextPageIncrement();
        /*
         * 2、计算加载后的页码
         */
        size += pageIncrement(data == null ? 0 : data.size());
        /*
         * 3、把页码赋值给加载页码，表示真正加载成功，如此加载页码能正确的加载下一页
         */
        loadPage = size;
    }

    @Override
    public void finishWithError() {
        super.finishWithError();
        // 加载失败恢复页码
        loadPage = size;
    }

    /**
     * 即将加载下一页时，递增的增量
     *
     * @return 即将加载下一页时页码的增量大小
     */
    private int nextPageIncrement() {
        if (mPageAlgorithm != null) {
            return mPageAlgorithm.nextPageIncrement();
        }
        return 0;
    }

    /**
     * 加载成功后页码增量
     *
     * @param newDataSize 加载成功的分页大小
     * @return 加载成功后分页的增量大小
     */
    private int pageIncrement(int newDataSize) {
        if (mPageAlgorithm != null) {
            return mPageAlgorithm.pageIncrement(newDataSize);
        }
        return newDataSize;
    }

    /**
     * 分页算法
     */
    public interface PageAlgorithm {

        /**
         * 即将加载下一页时，递增的增量
         *
         * @return 即将加载下一页时页码的增量大小
         */
        int nextPageIncrement();

        /**
         * 加载成功后页码增量
         *
         * @param newDataSize 加载成功的分页大小
         * @return 加载成功后分页的增量
         */
        int pageIncrement(int newDataSize);


    }
}
