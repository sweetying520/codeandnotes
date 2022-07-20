package com.dream.refresh.status;

import android.view.View;

/**
 * function: 状态view接口
 *
 * @author zy
 * @since 2022/7/20
 */
public interface IStatusView {

    int STATUS_LOADING = 1;

    int STATUS_EMPTY = 2;

    int STATUS_ERROR = 3;

    int STATUS_CONTENT = 4;

    /**
     * 显示空白view
     */
    void showEmpty();
    /**
     * 显示空白view
     */
    void showEmpty(String tips);

    /**
     * 显示加载中View
     */
    void showLoading();

    /**
     * 显示失败View
     */
    void showError();

    /**
     * 显示失败View
     * @param errorMessage 错误信息
     */
    void showError(CharSequence errorMessage);

    /**
     * 显示内容View
     */
    void showContent();

    /**
     * 获取状态
     *
     * @return 当前状态
     */
    int getStatus();

    /**
     * 状态View
     * @return 状态View
     */
    View getView();

    /**
     * 状态View提供器
     * @param provider StatusViewProvider
     */
    void setStatusViewProvider(StatusViewProvider provider);

}
