package com.dream.uieffectdemo.imedittext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/7
 */




object ContactTool {

    @JvmStatic
    fun getTitle(text: String): String{
        return "_$text"
    }

    @JvmStatic
    @BindingAdapter("myImageUrl")
    fun loadImage(imageView: ImageView,imageUrl: String){
       Glide.with(imageView.context).load(imageUrl).into(imageView)
    }
}