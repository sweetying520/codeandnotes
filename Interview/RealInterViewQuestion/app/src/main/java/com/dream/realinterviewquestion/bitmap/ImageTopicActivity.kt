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
import com.jakewharton.disklrucache.DiskLruCache
import java.util.zip.Inflater
import kotlin.concurrent.thread

class ImageTopicActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityImageTopicBinding

    private lateinit var diskLruCache: DiskLruCache

    companion object{
        const  val imageUrl = "https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d6e402af2a774dbdbca9fc37e8027cfa~tplv-k3u1fbpfcp-watermark.image?"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityImageTopicBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getBitmapInfo()

        initListener()

        val cacheDir = BitmapUtils.getCacheDir(this, "bitmap")
        val appVersion = BitmapUtils.getAppVersion(this)
        diskLruCache = DiskLruCache.open(cacheDir,appVersion,1,10 * 1024 * 1024)
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

        mBinding.btnDiskLruCacheSave.setOnClickListener {
            thread {
                try {
                    val key = BitmapUtils.hashKeyForDisk(imageUrl)
                    val editor = diskLruCache.edit(key)
                    if(editor != null){
                        val outputStream = editor.newOutputStream(0)
                        if(BitmapUtils.downloadUrlToStream(imageUrl,outputStream)){
                            editor.commit()
                        }else{
                            editor.abort()
                        }
                    }
                    diskLruCache.flush()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        mBinding.btnDiskLruCacheGet.setOnClickListener {
            val key = BitmapUtils.hashKeyForDisk(imageUrl)
            val snapshot = diskLruCache.get(key)
            if(snapshot != null){
                val bitmap = BitmapFactory.decodeStream(snapshot.getInputStream(0))
                mBinding.iv.setImageBitmap(bitmap)
            }
        }

        mBinding.btnDiskLruCacheRemove.setOnClickListener {
            val key = BitmapUtils.hashKeyForDisk(imageUrl)
            diskLruCache.remove(key)
        }

        //size()：返回缓存数据的总字节数，以 byte 为单位
        //flush()：将内存中的操作记录同步到日志文件
        //close()：关闭 DiskLruCache
        //delete()：删除缓存中的所有数据

    }
}