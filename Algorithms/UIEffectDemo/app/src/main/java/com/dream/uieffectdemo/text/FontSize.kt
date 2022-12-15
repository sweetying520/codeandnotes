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

/**
 * function: 规定的字体 size
 *
 * @author zy
 * @since 2022/12/15
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
