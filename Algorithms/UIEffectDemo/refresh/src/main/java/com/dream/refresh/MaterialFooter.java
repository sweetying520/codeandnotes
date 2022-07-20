package com.dream.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.dream.refresh.internal.MaterialProgressDrawable;
import com.dream.refresh.material.CircleImageView;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;


/**
 * function: Material风格加载更多view
 *
 * @author zy
 * @since 2022/7/20
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MaterialFooter extends InternalAbstract implements AppRefreshFooter {

    // Maps to ProgressBar.Large style
    public static final int SIZE_LARGE = 0;

    // Maps to ProgressBar default style
    public static final int SIZE_DEFAULT = 1;

    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;

    private static final float MAX_PROGRESS_ANGLE = .8f;

    @VisibleForTesting
    private static final int CIRCLE_DIAMETER = 40;

    @VisibleForTesting
    private static final int CIRCLE_DIAMETER_LARGE = 56;

    private int mCircleDiameter;

    protected RefreshKernel mRefreshKernel;

    protected Integer mPrimaryColor;

    protected int mBackgroundColor;

    private CircleImageView mCircleView;

    private MaterialProgressDrawable mProgress;

    private View noMoreDataView;

    private View errorView;

    private boolean mNoMoreData = false;

    private boolean isFinishError = false;

    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;

    //<editor-fold desc="MaterialFooter">
    public MaterialFooter(Context context) {
        this(context, null);
    }

    public MaterialFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        if (getChildCount() > 1) {
            throw new IndexOutOfBoundsException("The child view just only one");
        }
        noMoreDataView = getChildAt(0);

        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mCircleDiameter = (int) (CIRCLE_DIAMETER * metrics.density);

        mProgress = new MaterialProgressDrawable(this);
        mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        mProgress.setAlpha(255);
        mProgress.setColorSchemeColors(0xff0099cc, 0xffff4444, 0xff669900, 0xffaa66cc, 0xffff8800);
        mProgress.setArrowScale(1);
        setClipToPadding(false);

        mCircleView = new CircleImageView(context, CIRCLE_BG_LIGHT);
        mCircleView.setImageDrawable(mProgress);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT);
        addView(mCircleView, params);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MaterialFooter);
        mSpinnerStyle = SpinnerStyle.values[ta
                .getInt(R.styleable.MaterialFooter_materialSpinnerStyle,
                        mSpinnerStyle.ordinal)];
        ta.recycle();

        setMinimumHeight(mCircleDiameter + Utils.dp2px(20));
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgroundFor(this, mBackgroundColor);
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void onReleased(RefreshLayout layout, int footerHeight, int extendHeight) {
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {
        if (!mNoMoreData) {
            if (noMoreDataView != null) {
                noMoreDataView.setVisibility(INVISIBLE);
            }
            if (errorView != null) {
                errorView.setVisibility(GONE);
            }
            mProgress.showArrow(false);
            mProgress.setStartEndTrim(0f, MAX_PROGRESS_ANGLE);
            mProgress.start();
            mCircleView.setVisibility(VISIBLE);
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        isFinishError = !success;
        if (!mNoMoreData) {
            mProgress.stop();
        }
        return 0;
    }

    @Override
    public boolean holderFooterView() {
        return errorView != null;
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {
    }

    public MaterialFooter setFootBackground(@ColorInt int primaryColor) {
        mBackgroundColor = mPrimaryColor = primaryColor;
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundFor(this, mBackgroundColor);
        }
        return this;
    }

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        // 为false时，只是复位没有更多状态
        if (!noMoreData) {
            isFinishError = false;
        }
        if (holderFooterView()) {
            // 只有加载成功noMoreData为true的情况下才为真正的没有更多，否则只是出错固定底部
            noMoreData = !isFinishError && noMoreData;
        }
        if (isFinishError) {
            mProgress.stop();
            mCircleView.setVisibility(GONE);
            if (holderFooterView()) {
                errorView.setVisibility(VISIBLE);
            }
        }
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData;
            if (noMoreData) {
                if (noMoreDataView != null) {
                    noMoreDataView.setVisibility(VISIBLE);
                }
                mCircleView.setVisibility(GONE);
            } else {
                if (noMoreDataView != null) {
                    noMoreDataView.setVisibility(INVISIBLE);
                }
                mCircleView.setVisibility(VISIBLE);
            }
            mProgress.stop();
        }
        return true;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }

    /**
     * 设置 ColorScheme
     *
     * @param colors ColorScheme
     * @return MaterialFooter
     */
    public MaterialFooter setColorSchemeColors(int... colors) {
        mProgress.setColorSchemeColors(colors);
        return this;
    }

    /**
     * 设置大小尺寸
     *
     * @param size One of DEFAULT, or LARGE.
     * @return MaterialFooter
     */
    public MaterialFooter setSize(int size) {
        if (size != SIZE_LARGE && size != SIZE_DEFAULT) {
            return this;
        }
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        if (size == SIZE_LARGE) {
            mCircleDiameter = (int) (CIRCLE_DIAMETER_LARGE * metrics.density);
        } else {
            mCircleDiameter = (int) (CIRCLE_DIAMETER * metrics.density);
        }
        // force the bounds of the progress circle inside the circle view to
        // update by setting it to null before updating its size and then
        // re-setting it
        mCircleView.setImageDrawable(null);
        mProgress.updateSizes(size);
        mCircleView.setImageDrawable(mProgress);
        return this;
    }

    public MaterialFooter setNoMoreView(@LayoutRes int layoutRes) {
        View noMoreView = LayoutInflater.from(getContext()).inflate(layoutRes, this, false);
        setNoMoreView(noMoreView);
        return this;
    }

    @Override
    public MaterialFooter setNoMoreView(View noMoreDataView) {
        if (this.noMoreDataView != null) {
            removeView(this.noMoreDataView);
        }
        this.noMoreDataView = noMoreDataView;
        if (this.noMoreDataView != null) {
            if (this.noMoreDataView.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.noMoreDataView.getParent()).removeView(this.noMoreDataView);
            }
            noMoreDataView.setVisibility(mNoMoreData ? VISIBLE : INVISIBLE);
            addView(this.noMoreDataView, generateLayoutParams(this.noMoreDataView));
        }
        return this;
    }

    @Override
    public View getNoMoreView() {
        return noMoreDataView;
    }

    /**
     * 底部错误View
     *
     * @param layoutRes layout资源id
     */
    public MaterialFooter setErrorView(@LayoutRes int layoutRes) {
        if (layoutRes != 0) {
            View errorView = LayoutInflater.from(getContext()).inflate(layoutRes, this, false);
            setErrorView(errorView);
        }
        return this;
    }

    /**
     * 底部错误View
     */
    @Override
    public MaterialFooter setErrorView(View errorView) {
        if (this.errorView != null) {
            removeView(this.errorView);
        }
        this.errorView = errorView;
        if (errorView != null) {
            if (this.errorView.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.errorView.getParent()).removeView(this.errorView);
            }
            errorView.setVisibility(GONE);
            addView(this.errorView, generateLayoutParams(this.errorView));
        }
        return this;
    }

    private LayoutParams generateLayoutParams(View view) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams sourceParams = view.getLayoutParams();
        if (sourceParams != null) {
            if (sourceParams instanceof LayoutParams) {
                params = (LayoutParams) sourceParams;
            }
            if (sourceParams.height < 0) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        params.addRule(CENTER_IN_PARENT);
        return params;
    }

    @Override
    public boolean isNoMoreData() {
        return mNoMoreData;
    }
}
