package com.dream.realinterviewquestion.bitmap.photowall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dream.realinterviewquestion.databinding.ActivityPhotoWallBinding
import com.jakewharton.disklrucache.DiskLruCache

class PhotoWallActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityPhotoWallBinding
    private lateinit var mAdapter: PhotoWallAdapter
    private lateinit var mAdapter2: PhotoWallAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPhotoWallBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAdapter = PhotoWallAdapter(this,0,mBinding.gridPhotoWall)
        mBinding.gridPhotoWall.adapter = mAdapter

//        mAdapter2 = PhotoWallAdapter2(this,0,mBinding.gridPhotoWall)
//        mBinding.gridPhotoWall.adapter = mAdapter2
    }



    override fun onDestroy() {
        super.onDestroy()
        mAdapter.cancelAllTasks()
//        mAdapter2.cancelAllTasks()
    }
}