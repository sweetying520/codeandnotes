package com.dream.refresh;

import android.content.res.Resources;

/**
 * function: 帮助类
 *
 * @author zy
 * @since 2022/7/20
 */
class Utils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}
