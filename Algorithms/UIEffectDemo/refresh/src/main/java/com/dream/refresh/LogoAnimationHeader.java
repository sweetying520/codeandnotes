package com.dream.refresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

/**
 * function: 自定义Logo动画header
 *
 * @author zy
 * @since 2022/7/20
 */
public class LogoAnimationHeader extends InternalAbstract implements RefreshHeader {
    /**
     * The Logo animation view.
     */
    LottieAnimationView mAnimationView;

    private final ValueAnimator.AnimatorUpdateListener animatorUpdateListener = animation -> {
        if (animation.getAnimatedFraction() >= 0.99) {
            //播放结束,初始化配置
            mAnimationView.cancelAnimation();
            mAnimationView.setProgress(0);
            mAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        }
    };

    public LogoAnimationHeader(Context context) {
        this(context, null);
    }

    public LogoAnimationHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoAnimationHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        mAnimationView.setAnimation("logo_anim1.json");
        mAnimationView.setAnimation("logo_anim2.json");
    }


    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.refresh_header_logo_animation_lottie, this);
        mAnimationView = view.findViewById(R.id.loading_lottie);
    }

    /**
     * 注意不能为null
     */
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {
        mAnimationView.removeUpdateListener(animatorUpdateListener);
        mAnimationView.playAnimation();
    }


    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (newState == RefreshState.PullDownToRefresh) {
            //下拉过程
            mAnimationView.setAnimation("logo_anim1.json");
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        //取消"加载中..."动画
        mAnimationView.cancelAnimation();
        mAnimationView.setProgress(0);
        mAnimationView.setRepeatCount(0);
        mAnimationView.addAnimatorUpdateListener(animatorUpdateListener);
        mAnimationView.setAnimation("logo_anim2.json");
        mAnimationView.playAnimation();
        return 350;
    }

}
