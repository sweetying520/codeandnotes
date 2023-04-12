package com.dream.realinterviewquestion.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.util.Log
import com.dream.realinterviewquestion.MyApplication
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
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

    fun decodeSampledBitmapFromFile(path: String,targetWidth: Int,targetHeight: Int): Bitmap?{
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path,bitmapOptions)
        bitmapOptions.inSampleSize = calcInSampleSize(bitmapOptions,targetWidth,targetHeight)
        bitmapOptions.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path,bitmapOptions)
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

    //"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s160-c/A%252520Photographer.jpg"
    fun getImagePath(imageUrl: String): String {
        val index = imageUrl.lastIndexOf("/")
        val imageName = imageUrl.substring(index + 1)
        val parentPath = "${MyApplication.context.externalCacheDir?.path}/image/"
        val parent = File(parentPath)
        if(!parent.exists())parent.mkdirs()
        return "$parentPath$imageName"
    }

    fun dp2px(dpValue: Float): Int{
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun getCacheDir(context: Context,uniqueName: String): File{
        val parentPath: String? = if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
            || !Environment.isExternalStorageRemovable()){
            context.externalCacheDir?.path
        }else{
            context.cacheDir?.path
        }
        return File(parentPath + File.separator + uniqueName)
    }

    fun getAppVersion(context: Context) = try {
        context.packageManager.getPackageInfo(context.packageName, 0).versionCode
    } catch (e: Exception) {
        e.printStackTrace()
        1
    }

    fun downloadUrlToStream(url: String,output: OutputStream): Boolean{
        var urlConnection: HttpURLConnection? = null
        var bufferInputStream: BufferedInputStream? = null
        var bufferOutputStream: BufferedOutputStream? = null
        try {
            val uRL = URL(url)
            urlConnection = uRL.openConnection() as? HttpURLConnection
            bufferInputStream = BufferedInputStream(urlConnection?.inputStream)
            bufferOutputStream = BufferedOutputStream(output)
            val bytes = ByteArray(8*1024)
            var length: Int
            length = bufferInputStream.read(bytes)
            while(length != -1){
                bufferOutputStream.write(bytes,0,length)
                bufferOutputStream.flush()
                length = bufferInputStream.read(bytes)
            }
            Log.d("erdai", "downloadUrlToStream: 下载成功...")
            return true
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            urlConnection?.disconnect()
            bufferInputStream?.close()
            bufferOutputStream?.close()
        }
        return false
    }

    fun hashKeyForDisk(key: String) = try {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(key.toByteArray())
        bytesToHexString(messageDigest.digest())
    } catch (e: Exception) {
        java.lang.String.valueOf(key.hashCode())
    }


    fun bytesToHexString(byteArray: ByteArray): String{
        val sb = StringBuilder()
        for (i in byteArray.indices) {
            val hex = Integer.toHexString(0xFF and byteArray[i].toInt())
            if (hex.length == 1) {
                sb.append('0')
            }
            sb.append(hex)
        }
        return sb.toString()
    }

    fun hashKeyFromUrl(url: String): String {
        val cacheKey: String
        cacheKey = try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(url.toByteArray())
            bytesToHexString1(digest.digest())
        } catch (e: NoSuchAlgorithmException) {
            url.hashCode().toString()
        }
        return cacheKey
    }

    private fun bytesToHexString1(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                sb.append('0')
            }
            sb.append(hex)
        }
        return sb.toString()
    }

}