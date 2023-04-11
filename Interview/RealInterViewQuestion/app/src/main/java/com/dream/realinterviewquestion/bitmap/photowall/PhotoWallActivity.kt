package com.dream.realinterviewquestion.bitmap.photowall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dream.realinterviewquestion.R
import com.dream.realinterviewquestion.databinding.ActivityImageTopicBinding
import com.dream.realinterviewquestion.databinding.ActivityPhotoWallBinding

class PhotoWallActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityPhotoWallBinding
    private lateinit var mAdapter: PhotoWallAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPhotoWallBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mAdapter = PhotoWallAdapter(this,0,mBinding.gridPhotoWall)
        mBinding.gridPhotoWall.adapter = mAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter.cancelAllTasks()
    }
}