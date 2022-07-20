package com.dream.refresh.helper;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dream.refresh.AppRefreshFooter;
import com.dream.refresh.R;
import com.dream.refresh.space.HorizontalSpaceDecoration;
import com.dream.refresh.space.VerticalSpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;


/**
 * function: 刷新RecyclerView帮助类
 *
 * @author zy
 * @since 2022/7/20
 */
public class RecyclerViewHelper<T> {

    SmartRefreshLayout mRefreshLayout;

    /**
     * 填充在刷新控件里的recyclerView
     */
    final RecyclerView mRecyclerView;

    /**
     * recyclerView绑定的Adapter
     */
    final BaseQuickAdapter<T, ? extends BaseViewHolder> mAdapter;

    @SuppressWarnings("unchecked")
    RecyclerViewHelper(RefreshListHelper refreshListHelper) {
        Context context = refreshListHelper.smartRefreshLayout.getContext();
        mRefreshLayout = refreshListHelper.smartRefreshLayout;
        /*
         * 1.获取或者创建RecyclerView
         */
        RecyclerView recyclerView = refreshListHelper.recyclerView;
        if (recyclerView == null) {
            /*
             * 查找刷新控件中的子view是否存在RecyclerView
             */
            for (int i = 0; i < mRefreshLayout.getChildCount(); i++) {
                View child = mRefreshLayout.getChildAt(i);
                if (child instanceof RecyclerView) {
                    recyclerView = (RecyclerView) child;
                    break;
                }
            }
            /*
             * 若未找到则创建RecyclerView，并设置为刷新控件的内容view
             */
            if (recyclerView == null) {
                recyclerView = new RecyclerView(context);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    recyclerView.setId(R.id.base_refresh_recycler_view_id);
                }
                mRefreshLayout.setRefreshContent(recyclerView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            }
        }
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        this.mRecyclerView = recyclerView;
        /*
         * 2.设置LayoutManager
         */
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager == null) {
            layoutManager = refreshListHelper.layoutManager;
            if (layoutManager == null) {
                layoutManager = new LinearLayoutManager(context);
            }
            mRecyclerView.setLayoutManager(layoutManager);
        }
        /*
         * 3.设置间隔装饰等
         */
        if (refreshListHelper.itemDecorations != null) {
            for (RecyclerView.ItemDecoration itemDecoration : refreshListHelper.itemDecorations) {
                this.mRecyclerView.addItemDecoration(itemDecoration);
            }
        } else if (layoutManager instanceof LinearLayoutManager
                && (refreshListHelper.startSpace != 0 || refreshListHelper.endSpace != 0)) {
            // 横向布局设置左右间距，纵向布局设置上下间距
            boolean isHorizontal = ((LinearLayoutManager) layoutManager).getOrientation()
                    == LinearLayoutManager.HORIZONTAL;
            if (isHorizontal) {
                mRecyclerView.addItemDecoration(
                        new HorizontalSpaceDecoration(refreshListHelper.startSpace, refreshListHelper.endSpace));
            } else {
                mRecyclerView.addItemDecoration(
                        new VerticalSpaceDecoration(refreshListHelper.startSpace, refreshListHelper.endSpace));
            }
        }
        /*
         * 4.查找RecyclerView中是否已经绑定了规定的BaseQuickAdapter，
         *   若已绑定adapter则使用该BaseQuickAdapter，若未绑定则绑定参数中的Adapter
         */
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter() instanceof BaseQuickAdapter) {
            mAdapter = (BaseQuickAdapter<T, ? extends BaseViewHolder>) mRecyclerView.getAdapter();
        } else {
            mAdapter = (BaseQuickAdapter<T, ? extends BaseViewHolder>) refreshListHelper.adapter;
        }
        mAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.addOnScrollListener(getOnScrollListener());
    }

    /**
     * 监听滚动事件
     */
    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            private int scrollY = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    scrollY = 0;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // 解决快速滑动时，底部浮在列表上的bug
                RefreshFooter refreshFooter = mRefreshLayout.getRefreshFooter();
                if (!(refreshFooter instanceof AppRefreshFooter)) {
                    return;
                }
                AppRefreshFooter footer = (AppRefreshFooter) refreshFooter;
                scrollY += dy;
                int footHeight = footer.getView().getHeight();
                if (footHeight <= 0) {
                    ViewGroup.LayoutParams params = footer.getView().getLayoutParams();
                    if (params != null) {
                        footHeight = params.height;
                    }
                }
                // 如果滑动距离超过底部距离，并且底部状态不是正在加载更多，则关闭底部view
                if (footHeight > 0 && scrollY < 0 && Math.abs(scrollY) >= footHeight
                        && footer.isNoMoreData()) {
                    mRefreshLayout.closeHeaderOrFooter();
                }
            }
        };
    }

    /**
     * 以页码递增作为分页的帮助类
     *
     * @param pageSize 分页量，即每页的加载量大小
     */
    public PageLoadHelper<T> pageNumberHelper(int pageSize) {
        PageLoadHelper.PageAlgorithm algorithm = new PageLoadHelper.PageAlgorithm() {
            @Override
            public int nextPageIncrement() {
                return 1;
            }

            @Override
            public int pageIncrement(int newDataSize) {
                return 1;
            }
        };
        return new PageLoadHelper<>(pageSize, algorithm, this);
    }

    /**
     * 以offset即数据列表的偏移位置作为分页的帮助类
     *
     * @param pageSize 分页量，即每页的加载量大小
     */
    public PageLoadHelper<T> pageOffsetHelper(int pageSize) {
        PageLoadHelper.PageAlgorithm algorithm = new PageLoadHelper.PageAlgorithm() {
            @Override
            public int nextPageIncrement() {
                return 0;
            }

            @Override
            public int pageIncrement(int newDataSize) {
                return newDataSize;
            }
        };
        return new PageLoadHelper<>(pageSize, algorithm, this);
    }

    /**
     * 获取简单的刷新加载帮助类
     */
    public SimpleLoadHelper<T> simpleLoadHelper() {
        return new SimpleLoadHelper<>(this);
    }


    /**
     * 自定义分页算法，获取分页加载帮助类
     *
     * @param pageSize      分页量，即每页大小
     * @param pageAlgorithm 分页算法接口
     * @return 分页加载帮助类
     */
    public PageLoadHelper<T> pageLoadHelper(int pageSize, PageLoadHelper.PageAlgorithm pageAlgorithm) {
        return new PageLoadHelper<>(pageSize, pageAlgorithm, this);
    }
}
