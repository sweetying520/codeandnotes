package com.dream.uieffectdemo.text

import androidx.annotation.IntDef
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_10
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_11
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_12
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_14
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_16
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_18
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_20
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_24
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_28
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_32
import com.dream.uieffectdemo.text.FontType.Companion.FONT_BOLD
import com.dream.uieffectdemo.text.FontType.Companion.FONT_MEDIUM
import com.dream.uieffectdemo.text.FontType.Companion.FONT_REGULAR

/**
 * function: 字体相关注解
 *
 * @author zy
 * @since 2023/1/13
 */
@IntDef(value = [FONT_SIZE_32,FONT_SIZE_28, FONT_SIZE_24, FONT_SIZE_20, FONT_SIZE_18, FONT_SIZE_16, FONT_SIZE_14, FONT_SIZE_12, FONT_SIZE_11, FONT_SIZE_10])
annotation class FontSize {
    companion object {
        /**
         * 字体大小
         */
        const val FONT_SIZE_32 = 32
        const val FONT_SIZE_28 = 28
        const val FONT_SIZE_24 = 24
        const val FONT_SIZE_20 = 20
        const val FONT_SIZE_18 = 18
        const val FONT_SIZE_16 = 16
        const val FONT_SIZE_14 = 14
        const val FONT_SIZE_12 = 12
        const val FONT_SIZE_11 = 11
        const val FONT_SIZE_10 = 10
    }
}

@IntDef(value = [FONT_REGULAR,FONT_MEDIUM,FONT_BOLD])
annotation class FontType {
    companion object {
        /**
         * 字体类型
         */
        const val FONT_REGULAR = 1
        const val FONT_MEDIUM = 2
        const val FONT_BOLD = 3

    }
}