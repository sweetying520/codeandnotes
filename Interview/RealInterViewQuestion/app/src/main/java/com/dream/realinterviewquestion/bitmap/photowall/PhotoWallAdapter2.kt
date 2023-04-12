package com.dream.realinterviewquestion.bitmap.photowall

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import com.dream.realinterviewquestion.R
import com.dream.realinterviewquestion.bitmap.Images
import com.dream.realinterviewquestion.bitmap.showbigpic.ShowBigPicActivity
import com.dream.realinterviewquestion.utils.BitmapUtils
import com.jakewharton.disklrucache.DiskLruCache
import com.jakewharton.disklrucache.DiskLruCache.Snapshot
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * function: LruCache + DiskLruCache + inSampleSize 压缩
 *
 * @author zy
 * @since 2023/4/7
 */
class PhotoWallAdapter2(context: Context,resId: Int,gridView: GridView): ArrayAdapter<String>(context,resId,Images.imageThumbUrls),
    OnScrollListener {

    private var mGridView: GridView
    private var taskCollection: MutableSet<BitmapWorkerTask>
    private var lruCache: LruCache<String?,Bitmap?>
    private var diskLruCache: DiskLruCache
    private var mFirstVisibleIndex = 0
    private var mVisibleItemCount = 0
    private var isFirstEnter = true



    init {
        mGridView = gridView
        val maxMemory = Runtime.getRuntime().maxMemory().toInt()
        val cacheSize = maxMemory / 8
        taskCollection = mutableSetOf()
        lruCache = object : LruCache<String?,Bitmap?>(cacheSize){
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return BitmapUtils.getBitmapSize(value)
            }
        }
        diskLruCache = DiskLruCache.open(BitmapUtils.getCacheDir(context,"bitmap1"),BitmapUtils.getAppVersion(context),1,10*1024*1024)
        mGridView.setOnScrollListener(this)
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val url = getItem(position)
        val view: View? = convertView ?: LayoutInflater.from(context).inflate(R.layout.photo_layout,null)
        val iv = view?.findViewById<ImageView>(R.id.photo)
        iv?.tag = url
        iv?.setOnClickListener {
            ShowBigPicActivity.start(context,url)
        }
        setImageView(url,iv)
        return view!!
    }

    private fun setImageView(url: String?, iv: ImageView?) {
        val bitmap = getBitmapFromMemoryCache(url)
        if(bitmap != null){
            iv?.setImageBitmap(bitmap)
        }else{
            iv?.setImageResource(R.mipmap.ic_launcher)
        }
    }

    private fun addToMemoryCache(url: String?,bitmap: Bitmap?) {
        if(getBitmapFromMemoryCache(url) == null){
            lruCache.put(url,bitmap)
        }
    }

    private fun getBitmapFromMemoryCache(url: String?): Bitmap?{
        return lruCache.get(url)
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        if(scrollState == SCROLL_STATE_IDLE){
            Log.d("erdai", "onScrollStateChanged: $mFirstVisibleIndex $mVisibleItemCount")
            loadBitmaps(mFirstVisibleIndex,mVisibleItemCount)
        }else{
            cancelAllTasks()
        }
    }

    override fun onScroll(
        view: AbsListView?,
        firstVisibleItem: Int,
        visibleItemCount: Int,
        totalItemCount: Int,
    ) {
        mFirstVisibleIndex = firstVisibleItem
        mVisibleItemCount = visibleItemCount
        if(isFirstEnter && visibleItemCount > 0){
            Log.d("erdai", "onScroll: first $mFirstVisibleIndex  $mVisibleItemCount")
            isFirstEnter = false
            loadBitmaps(firstVisibleItem,visibleItemCount)
        }
    }

    private fun loadBitmaps(firstVisibleItem: Int, visibleItemCount: Int) {
        try {
            for (i in firstVisibleItem until firstVisibleItem + visibleItemCount){
                val imageUrl = getItem(i)
                val bitmap = getBitmapFromMemoryCache(imageUrl)
                if(bitmap == null){
                    val bitmapTask = BitmapWorkerTask()
                    taskCollection.add(bitmapTask)
                    bitmapTask.execute(imageUrl)
                }else{
                    val iv = mGridView.findViewWithTag<ImageView>(imageUrl)
                    iv?.setImageBitmap(bitmap)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun cancelAllTasks(){
        taskCollection.forEach {
            it.cancel(false)
        }
        taskCollection.clear()
    }


    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    inner class BitmapWorkerTask: AsyncTask<String,Unit,Bitmap>(){

        private lateinit var imageUrl: String

        override fun onPreExecute() {
            Log.d("erdai", "onPreExecute: BitmapWorkerTask init....")
        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            imageUrl = params[0].toString()
            Log.d("erdai", "doInBackground: $imageUrl")
            var inputStream: FileInputStream? = null
            var fd: FileDescriptor? = null
            var snapshot: Snapshot?
            try {
                val key = BitmapUtils.hashKeyForDisk(imageUrl)
                snapshot = diskLruCache.get(key)
                if(snapshot == null){
                    val editor = diskLruCache.edit(key)
                    if(editor != null){
                        val newOutputStream = editor.newOutputStream(0)
                        if(downloadBitmapInBackground(imageUrl,newOutputStream)){
                            editor.commit()
                        }else{
                            editor.abort()
                        }
                    }
                    snapshot = diskLruCache.get(key)
                }
                //diskLruCache.flush()
                if(snapshot != null){
                    inputStream = snapshot.getInputStream(0) as? FileInputStream
                    fd = inputStream?.fd
                }

                val bitmap = BitmapFactory.decodeFileDescriptor(fd)
                if(bitmap != null){
                    addToMemoryCache(imageUrl,bitmap)
                }
                return bitmap
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                inputStream?.close()
            }
            return null
        }

        private fun loadImage(imageUrl: String): Bitmap? {
            val imageFile = File(BitmapUtils.getImagePath(imageUrl))
            if(!imageFile.exists()){
                //downloadBitmapInBackground(imageUrl)
            }
            val bitmap = BitmapFactory.decodeFile(imageFile.path)
            Log.d("erdai", "loadImage: 压缩前：${bitmap.allocationByteCount}")
            val compressBitmap = BitmapUtils.decodeSampledBitmapFromFile(imageFile.path,BitmapUtils.dp2px(90f),BitmapUtils.dp2px(90f))
            Log.d("erdai", "loadImage: 压缩后：${compressBitmap?.allocationByteCount}")

            if(compressBitmap != null){
                addToMemoryCache(imageUrl,compressBitmap)
                return compressBitmap
            }
            return null
        }

        private fun downloadBitmapInBackground(imageUrl: String,outputStream: OutputStream): Boolean {
            var httpURLConnection: HttpURLConnection? = null
            var input: InputStream? = null
            var output: OutputStream? = null
            try {
                val url = URL(imageUrl)
                httpURLConnection = url.openConnection() as? HttpURLConnection
                httpURLConnection?.readTimeout = 5 * 1000
                httpURLConnection?.connectTimeout = 5 * 1000
                input = BufferedInputStream(httpURLConnection?.inputStream)
                output = BufferedOutputStream(outputStream)
                val bytes = ByteArray(8*1024)
                var length: Int
                length = input.read(bytes)
                while (length != -1){
                    output.write(bytes,0,length)
                    output.flush()
                    length = input.read(bytes)
                }
                return true
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                httpURLConnection?.disconnect()
                input?.close()
                output?.close()
            }
            return false
        }


        override fun onProgressUpdate(vararg values: Unit?) {
            Log.d("erdai", "onProgressUpdate: ....")
        }

        override fun onPostExecute(result: Bitmap?) {
            Log.d("erdai", "onPostExecute: ")
            val iv = mGridView.findViewWithTag<ImageView>(imageUrl)
            iv?.setImageBitmap(result)
            taskCollection.remove(this)
        }
    }
}