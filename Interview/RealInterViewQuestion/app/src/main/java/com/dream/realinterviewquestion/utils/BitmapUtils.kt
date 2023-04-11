package com.dream.realinterviewquestion.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import kotlin.math.roundToInt


/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/7
 */
object BitmapUtils {

    /**
     * 获取图片采样率
     */
    fun calcInSampleSize(options: BitmapFactory.Options,targetWidth: Int,targetHeight: Int): Int{
        //获取原始图片的宽高
        val originWidth = options.outWidth
        val originHeight = options.outHeight
        var inSampleSize = 1
        if(originWidth > targetWidth || originHeight > targetHeight){
            val widthRatio = (originWidth * 1.0f / targetWidth).roundToInt()
            val heightRatio = (originHeight * 1.0f / targetHeight).roundToInt()
            inSampleSize = if(widthRatio > heightRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }

    /**
     * 通过采样率对图片进行压缩
     */
    fun decodeSampledBitmapFromResource(resource: Resources,resId: Int,targetWidth: Int,targetHeight: Int): Bitmap{
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resource,resId,bitmapOptions)
        bitmapOptions.inSampleSize = calcInSampleSize(bitmapOptions,targetWidth,targetHeight)
        bitmapOptions.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resource,resId,bitmapOptions)
    }

    /**
     * 获取 Bitmap 占用的字节数
     */
    @SuppressLint("ObsoleteSdkInt")
    fun getBitmapSize(bitmap: Bitmap?): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bitmap?.allocationByteCount?:0
        } else {
            bitmap?.byteCount?:0
        }
    }

}