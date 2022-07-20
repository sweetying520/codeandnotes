package com.dream.refresh;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;


/**
 * function: APP中的刷新控件，主要对{@link SmartRefreshLayout}的二次封装，对默认头部底部进行修改，并且设置适用于APP的属性
 *
 * @author zy
 * @since 2022/7/20
 */
public class AppSmartRefreshLayout extends SmartRefreshLayout {

    private AppRefreshFooter mFooter;

    private boolean isHolderFooter;

    private float lastTouchY;

    @LayoutRes
    private int mNoMoreDataLayout;

    private View mNoMoreDataView;

    @LayoutRes
    private int mErrorLayout;

    private View mErrorView;

    private boolean isChangedFooterFollow;

    public AppSmartRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public AppSmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        setEnableOverScrollBounce(false);
        if (!isChangedFooterFollow) {
            setEnableFooterFollowWhenNoMoreData(true);
        }
    }

    @Override
    public RefreshLayout setEnableFooterFollowWhenNoMoreData(boolean enabled) {
        isChangedFooterFollow = true;
        return super.setEnableFooterFollowWhenNoMoreData(enabled);
    }

    @Override
    protected void onAttachedToWindow() {
        final Context context = getContext();
        if (mRefreshHeader == null) {
            if (sHeaderCreator != null) {
                setRefreshHeader(sHeaderCreator.createRefreshHeader(context, this));
            } else {
                // 指定头部
                LogoAnimationHeader header = new LogoAnimationHeader(context);
//                header.setColorSchemeColors(ContextCompat.getColor(context, R.color.refresh_colorPrimary));
                setRefreshHeader(header);
            }
        }
        if (mRefreshFooter == null) {
            if (sFooterCreator != null) {
                setRefreshFooter(sFooterCreator.createRefreshFooter(context, this));
            } else {
                boolean old = mEnableLoadMore;
                //指定Footer
                MaterialFooter footer = new MaterialFooter(context);
                footer.setColorSchemeColors(ContextCompat.getColor(context, R.color.refresh_colorPrimary));
                setRefreshFooter(footer);
                mEnableLoadMore = old;
            }
        }

        if (mFooter != null) {
            View noMoreDataView = mFooter.getNoMoreView();
            if (noMoreDataView != null) {
                mNoMoreDataView = noMoreDataView;
            }
            if (mNoMoreDataView == null) {
                int noMoreDataLayout = mNoMoreDataLayout == 0 ? R.layout.refresh_footer_no_more_view
                        : mNoMoreDataLayout;
                setFooterNoMoreView(noMoreDataLayout);
            } else {
                setFooterNoMoreView(mNoMoreDataView);
            }

            View errorView = mErrorView;
            if (mErrorView == null) {
                setFooterErrorView(mErrorLayout);
            } else {
                setFooterErrorView(errorView);
            }
        }

        super.onAttachedToWindow();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchY = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isHolderFooter && mSpinner == 0) {
                    if (lastTouchY == 0) {
                        lastTouchY = e.getY();
                    } else if (e.getY() - lastTouchY >= mFooterHeight) {
                        resetNoMoreData();
                        isHolderFooter = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isHolderFooter && mSpinner == 0) {
                    resetNoMoreData();
                    isHolderFooter = false;
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(e);
    }

    /**
     * 完成加载，由于默认底部不固定，复写父类出错时，让其底部固定
     * 只对{@link AppRefreshFooter}有效
     *
     * @param delayed    开始延时
     * @param success    数据是否成功
     * @param noMoreData 是否有更多数据
     * @return RefreshLayout
     */
    @Override
    public AppSmartRefreshLayout finishLoadMore(int delayed, boolean success, boolean noMoreData) {
        if (!success && mRefreshFooter instanceof AppRefreshFooter) {
            isHolderFooter = ((AppRefreshFooter) mRefreshFooter).holderFooterView();
        } else {
            isHolderFooter = false;
        }
        super.finishLoadMore(delayed, success, isHolderFooter || noMoreData);
        return this;
    }

    @Override
    public boolean autoLoadMore(int delayed, int duration, float dragRate, boolean animationOnly) {
        // 如果底部只是固定先恢复其状态，再加载更多
        if (isHolderFooter) {
            resetNoMoreData();
            return super.autoLoadMore(delayed, duration, 1, animationOnly);
        } else {
            return super.autoLoadMore(delayed, duration, dragRate, animationOnly);
        }
    }

    @Override
    public AppSmartRefreshLayout setRefreshFooter(@NonNull RefreshFooter footer) {
        if (footer instanceof AppRefreshFooter) {
            this.mFooter = (AppRefreshFooter) footer;
        }
        return (AppSmartRefreshLayout) super.setRefreshFooter(footer);
    }

    /**
     * 完成加载并标记没有更多数据
     *
     * @return SmartRefreshLayout
     */
    @Override
    public AppSmartRefreshLayout finishLoadMoreWithNoMoreData() {
        // 避免从未加载页调用该方法
        if (mLastOpenTime == 0) {
            return (AppSmartRefreshLayout) super.finishLoadMore(0, true, true);
        } else {
            return (AppSmartRefreshLayout) super.finishLoadMoreWithNoMoreData();
        }
    }

    /**
     * 只刷新不动画，直接回调刷新事件并不会有动画效果，并且是强制调用刷新回调（强制刷新是因为某些场景需要）
     */
    public boolean refresh() {
        if (mState == RefreshState.None) {
            post(() -> {
                if (mRefreshListener != null) {
                    mRefreshListener.onRefresh(this);
                }

            });
            return true;
        }
        return false;
    }

    /**
     * 设置没有更多的状态时所展示的View
     */
    public void setFooterNoMoreView(View view) {
        if (mFooter != null) {
            mFooter.setNoMoreView(view);
        } else {
            mNoMoreDataView = view;
        }
    }

    /**
     * 设置没有更多的状态时所展示的View layout资源id
     */
    public void setFooterNoMoreView(@LayoutRes int layoutRes) {
        if (layoutRes != 0) {
            if (mFooter != null && mFooter.getView() instanceof ViewGroup) {
                View noMoreView = LayoutInflater.from(mFooter.getView().getContext())
                        .inflate(layoutRes, (ViewGroup) mFooter.getView(), false);
                mFooter.setNoMoreView(noMoreView);
            } else {
                mNoMoreDataLayout = layoutRes;
            }
        }
    }

    /**
     * 底部加载出错View
     */
    public void setFooterErrorView(View view) {
        if (mFooter != null) {
            mFooter.setErrorView(view);
        } else {
            mErrorView = view;
        }
    }

    /**
     * 底部加载出错View
     *
     * @param layoutRes layout资源id
     */
    public void setFooterErrorView(@LayoutRes int layoutRes) {
        if (layoutRes != 0) {
            if (mFooter != null && mFooter.getView() instanceof ViewGroup) {
                View errorView = LayoutInflater.from(mFooter.getView().getContext())
                        .inflate(layoutRes, (ViewGroup) mFooter.getView(), false);
                mFooter.setErrorView(errorView);
            } else {
                mErrorLayout = layoutRes;
            }
        }
    }

    /**
     * 加载更多是否可用
     */
    public boolean isEnableLoadMore() {
        return mEnableLoadMore;
    }

    /**
     * 结束刷新与加载更多
     */
    public void finishAll() {
        finishRefresh();
        if (mFooterNoMoreData) {
            finishLoadMoreWithNoMoreData();
        } else {
            finishLoadMore();
        }
    }

    /**
     * 设置FooterView的颜色
     *
     * @param dividerColor 分割线颜色，默认：#E2E5E9
     * @param textColor    文本颜色，默认：#989FA9
     */
    public void setFooterViewColor(@ColorInt int dividerColor, @ColorInt int textColor) {
        try {
            View noMoreView = mFooter.getNoMoreView();
            if (noMoreView != null) {
                //左边分割线
                View leftDivider = noMoreView.findViewById(R.id.no_more_left_divider);
                //右边分割线
                View rightDivider = noMoreView.findViewById(R.id.no_more_right_divider);
                //中间文本
                TextView centerTextView = noMoreView.findViewById(R.id.no_more_product);
                if (leftDivider != null && rightDivider != null && centerTextView != null) {
                    leftDivider.setBackgroundColor(dividerColor);
                    rightDivider.setBackgroundColor(dividerColor);
                    centerTextView.setTextColor(textColor);
                }
            }
        } catch (Throwable ignored) {
        }
    }

    /**
     * 设置FooterView的文本
     *
     * @param text 文本内容
     */
    public void setFooterViewText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        try {
            View noMoreView = mFooter.getNoMoreView();
            //中间文本
            TextView centerTextView = noMoreView.findViewById(R.id.no_more_product);
            centerTextView.setText(text);
        } catch (Throwable ignored) {
        }
    }
}
