package com.dream.realinterviewquestion.bitmap.photowall

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.util.LruCache
import android.util.TimeUtils
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
import com.dream.realinterviewquestion.utils.BitmapUtils
import java.net.HttpURLConnection
import java.net.URL

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/7
 */
class PhotoWallAdapter(context: Context,resId: Int,gridView: GridView): ArrayAdapter<String>(context,resId,Images.imageThumbUrls),
    OnScrollListener {

    private var mGridView: GridView
    private var taskCollection: MutableSet<BitmapWorkerTask>
    private var lruCache: LruCache<String?,Bitmap?>
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
        mGridView.setOnScrollListener(this)
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val url = getItem(position)
        val view: View? = convertView ?: LayoutInflater.from(context).inflate(R.layout.photo_layout,null)
        val iv = view?.findViewById<ImageView>(R.id.photo)
        iv?.tag = url
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
            Log.d("erdai", "doInBackground: ")
            imageUrl = params[0].toString()
            //开启在后台下载图片
            val bitmap = downloadBitmapInBackground(imageUrl)
            if(bitmap != null){
                //加入内存缓存
                addToMemoryCache(imageUrl,bitmap)
            }
            return bitmap
        }

        private fun downloadBitmapInBackground(imageUrl: String): Bitmap? {
            var bitmap: Bitmap? = null
            var httpURLConnection: HttpURLConnection? = null
            try {
                val url = URL(imageUrl)
                httpURLConnection = url.openConnection() as? HttpURLConnection
                httpURLConnection?.readTimeout = 5 * 1000
                httpURLConnection?.connectTimeout = 5 * 1000
                bitmap = BitmapFactory.decodeStream(httpURLConnection?.inputStream)
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                httpURLConnection?.disconnect()
            }
            return bitmap
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