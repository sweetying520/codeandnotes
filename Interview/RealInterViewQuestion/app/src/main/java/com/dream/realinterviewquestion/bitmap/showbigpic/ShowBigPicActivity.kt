package com.dream.realinterviewquestion.bitmap.showbigpic

import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dream.realinterviewquestion.R
import com.dream.realinterviewquestion.databinding.ActivityShowBigPicBinding
import com.dream.realinterviewquestion.utils.ActivityUtils
import com.dream.realinterviewquestion.utils.BitmapUtils
import java.io.File

class ShowBigPicActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityShowBigPicBinding

    companion object{
        const val PARAMS_URL = "url"

        fun start(context: Context, url: String?){
            ActivityUtils.startActivity<ShowBigPicActivity>(context){
                putExtra(PARAMS_URL,url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityShowBigPicBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val imageUrl = intent.getStringExtra(PARAMS_URL)?:""
        val bitmap = BitmapFactory.decodeFile(BitmapUtils.getImagePath(imageUrl))
        if(bitmap != null){
            mBinding.zoomImageView.setImageBitmap(bitmap)
        }
    }
}