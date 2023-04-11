package com.dream.realinterviewquestion.bitmap

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dream.realinterviewquestion.R
import com.dream.realinterviewquestion.bitmap.photowall.PhotoWallActivity
import com.dream.realinterviewquestion.databinding.ActivityImageTopicBinding
import com.dream.realinterviewquestion.utils.ActivityUtils
import com.dream.realinterviewquestion.utils.BitmapUtils
import java.util.zip.Inflater

class ImageTopicActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityImageTopicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityImageTopicBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getBitmapInfo()

        initListener()
    }

    /**
     * decodeByteArray, decodeFile, decodeResource等方法会为已经构建 bitmap 分配内存，很容易出现 OOM
     * 为了处理这种情况我们可以禁止给 bitmap 分配内存，而是先获取它的宽，高，MIME 类型，根据实际情况压缩后在进行加载
     */
    private fun getBitmapInfo() {
        val bitmapOption = BitmapFactory.Options()
        //这个属性可以禁止给 bitmap 分配内存
        bitmapOption.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources,R.drawable.sun,bitmapOption)
        val imageWidth = bitmapOption.outWidth
        val imageHeight = bitmapOption.outHeight
        val mimeType = bitmapOption.outMimeType
        Log.d("erdai", "getBitmapInfo: $imageWidth  $imageHeight  $mimeType")//1920  1280  image/jpeg
    }

    private fun initListener() {
        val originBitmap = BitmapFactory.decodeResource(resources,R.drawable.sun)
        Log.d("erdai", "initListener origin: ${originBitmap.byteCount}")
        mBinding.btnInSampleSize.setOnClickListener {
            val compressedBitmap =
                BitmapUtils.decodeSampledBitmapFromResource(resources, R.drawable.sun, 100, 100)
            Log.d("erdai", "initListener: ${compressedBitmap.byteCount}")
            mBinding.iv.setImageBitmap(compressedBitmap)
        }


        mBinding.btnLruCache.setOnClickListener {
            ActivityUtils.startActivity<PhotoWallActivity>(this)
        }
    }
}