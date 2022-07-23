package com.dream.uieffectdemo.utils

import android.content.Context
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception

/**
 * function: 获取 assets 下的 json 文件
 *
 * @author zy
 * @since 2022/7/23
 */
object JsonFileReader {

    fun getJson(context: Context, fileName: String?): String {
        val baos = ByteArrayOutputStream()
        try {
            val assetManager = context.assets
            val inputStream: InputStream = assetManager.open(fileName!!)
            val bufferedInputStream = BufferedInputStream(inputStream)
            val buffer = ByteArray(1024)
            var len: Int
            while (bufferedInputStream.read(buffer).also { len = it } != -1) {
                baos.write(buffer, 0, len)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                baos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return baos.toString()
    }
}