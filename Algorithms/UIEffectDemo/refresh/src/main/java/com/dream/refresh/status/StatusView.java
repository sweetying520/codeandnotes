package com.dream.refresh.status;


import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.dream.refresh.R;

/**
 * function: 状态View，内容由{@link #setStatusViewProvider(StatusViewProvider)}提供
 *
 * @author zy
 * @since 2022/7/20
 */
public class StatusView extends FrameLayout implements IStatusView {

    private static final int DELAY_TIME = 1500;

    private View loadingView;

    private View emptyView;

    private View errorView;

    private View contentView;

    private int mStatus;

    private long changeTime = 0;

    private Runnable delayRunnable = null;

    private Animation hideAnim;

    private Animation showAnim;

    private StatusViewProvider mStatusViewProvider;

    private OnStatusChangedListener onStatusChangedListener;

    private boolean isChanged;

    private static StatusViewProvider defaultStatusViewProvider;

    public StatusView(Context context) {
        this(context, null);
    }

    public StatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if (defaultStatusViewProvider == null) {
            setDefaultStatusViewProvider(new DefaultStatusViewProvider());
        }
        hideAnim = AnimationUtils.loadAnimation(getContext(), R.anim.status_translucent_exit);
        showAnim = AnimationUtils.loadAnimation(getContext(), R.anim.status_translucent_enter);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        showDefaultView();
    }

    /**
     * 如果从未改变过状态，则默认显示内容量页
     */
    private void showDefaultView() {
        // 如果从未改变过，则默认显示加载View
        if (!isChanged) {
            showContent();
        }
    }

    private void setStatus(final int status) {
        if (mStatus == status) {
            return;
        }
        isChanged = true;
        if (delayRunnable == null && changeTime > 0 && System.currentTimeMillis() - changeTime < DELAY_TIME
                && STATUS_LOADING == mStatus && STATUS_ERROR == status) {
            delayRunnable = new DelayRunnable(status);
            postDelayed(delayRunnable, DELAY_TIME);
            return;
        }
        if (delayRunnable != null) {
            removeCallbacks(delayRunnable);
        }
        changeTime = System.currentTimeMillis();
        int lastStatus = mStatus;
        this.mStatus = status;
        // 需要隐藏的View
        View hideView;
        boolean isShowContent = STATUS_CONTENT == mStatus;
        boolean isHideContent = STATUS_CONTENT == lastStatus;
        boolean runAnimation = false;
        if ((hideView = getView(lastStatus)) != null) {
            // 在内容页中使用CoordinatorLayout时，会出现不显示scrollView与闪屏等问题，因此内容页设置为INVISIBLE
            hideView.setVisibility(isHideContent ? INVISIBLE : GONE);
            /*
             * 1、由于一些状态View嵌套在其他View里面，造成View的闪现，那么这些View不做动画
             * 2、内容页相关的View不做动画
             */
            runAnimation = !isHideContent && !isShowContent && hideView.getParent() == this;
        }
        // 需要展示的View
        View showView;
        if ((showView = getView(mStatus)) != null) {
            showRootView(showView);
            showView.setVisibility(VISIBLE);
            /*
             * 1、由于一些状态View嵌套在其他View里面，造成View的闪现，那么这些View不做动画
             * 2、内容页相关的View不做动画
             */
            runAnimation = runAnimation && showView.getParent() == this;
        }
        if (runAnimation) {
            hideView.startAnimation(hideAnim);
            if (showView != null) {
                showView.startAnimation(showAnim);
            }
        }
        if (onStatusChangedListener != null) {
            onStatusChangedListener.onStatusChanged(lastStatus, mStatus);
        }
    }

    protected View getView(int status) {
        View addView = null;
        switch (status) {
            case STATUS_LOADING:
                if (mStatusViewProvider != null && loadingView == null) {
                    loadingView = mStatusViewProvider.getLoadingView(getContext(), this);
                    if (loadingView == null && defaultStatusViewProvider != null) {
                        loadingView = defaultStatusViewProvider.getLoadingView(getContext(), this);
                    }
                    if (loadingView != null && mStatus != STATUS_LOADING) {
                        loadingView.setVisibility(GONE);
                    }
                }
                addView = loadingView;
                break;
            case STATUS_ERROR:
                if (mStatusViewProvider != null && errorView == null) {
                    errorView = mStatusViewProvider.getErrorView(getContext(), this);
                    if (errorView == null && defaultStatusViewProvider != null) {
                        errorView = defaultStatusViewProvider.getErrorView(getContext(), this);
                    }
                    if (errorView != null && mStatus != STATUS_ERROR) {
                        errorView.setVisibility(GONE);
                    }
                }
                addView = errorView;
                break;
            case STATUS_EMPTY:
                if (mStatusViewProvider != null && emptyView == null) {
                    emptyView = mStatusViewProvider.getEmptyView(getContext(), this);
                    if (emptyView == null && defaultStatusViewProvider != null) {
                        emptyView = defaultStatusViewProvider.getEmptyView(getContext(), this);
                    }
                    if (emptyView != null && mStatus != STATUS_EMPTY) {
                        emptyView.setVisibility(GONE);
                    }
                }
                addView = emptyView;
                break;
            case STATUS_CONTENT:
                if (mStatusViewProvider != null && contentView == null) {
                    contentView = mStatusViewProvider.getContentView(getContext(), this);
                    if (contentView != null && mStatus != STATUS_CONTENT) {
                        contentView.setVisibility(GONE);
                    }
                }
                addView = contentView;
                break;
            default:
                break;
        }
        if (addView != null) {
            ViewParent parent = addView.getParent();
            if (parent == null) {
                addView(addView);
            }
        }
        return addView;
    }

    /**
     * 兼容状态view（譬如LoadingView）为内容View的一部分，因此找到内容View并且展示出来，否则局部状态View无法展示
     */
    private void showRootView(View view) {
        ViewParent parent = view.getParent();
        while (parent != null) {
            // 找到根布局
            boolean isRootView = parent instanceof View && android.R.id.content == ((View) parent).getId();
            if (isRootView
                    || parent.getParent() == null
                    || parent.getParent() == this) {
                break;
            }
            parent = parent.getParent();
        }
        // 展示根布局
        if (parent instanceof View && ((View) parent).getVisibility() != VISIBLE) {
            ((View) parent).setVisibility(VISIBLE);
        }
    }

    @Override
    public void showEmpty() {
        setStatus(STATUS_EMPTY);
    }

    @Override
    public void showEmpty(String tips) {
        setStatus(STATUS_EMPTY);
    }

    @Override
    public void showLoading() {
        setStatus(STATUS_LOADING);
    }

    @Override
    public void showError() {
        setStatus(STATUS_ERROR);
    }

    @Override
    public void showError(CharSequence errorMessage) {
        setStatus(STATUS_ERROR);
    }

    @Override
    public void showContent() {
        setStatus(STATUS_CONTENT);
    }

    @Override
    public int getStatus() {
        return mStatus;
    }

    @Override
    public View getView() {
        return this;
    }

    /**
     * 设置View的提供器
     *
     * @param statusViewProvider 真正的展示内容
     */
    @Override
    public void setStatusViewProvider(StatusViewProvider statusViewProvider) {
        mStatusViewProvider = statusViewProvider;
        showDefaultView();
    }

    private class DelayRunnable implements Runnable {

        private int status;

        private DelayRunnable(int status) {
            this.status = status;
        }

        @Override
        public void run() {
            setStatus(status);
            delayRunnable = null;
        }
    }

    public void setOnStatusChangedListener(OnStatusChangedListener onStatusChangedListener) {
        this.onStatusChangedListener = onStatusChangedListener;
    }

    public static void setDefaultStatusViewProvider(@NonNull StatusViewProvider provider) {
        defaultStatusViewProvider = provider;
    }

    public interface OnStatusChangedListener {

        /**
         * 状态转换事件
         *
         * @param from 从什么状态
         * @param to   转换成什么状态
         */
        void onStatusChanged(int from, int to);
    }
}
