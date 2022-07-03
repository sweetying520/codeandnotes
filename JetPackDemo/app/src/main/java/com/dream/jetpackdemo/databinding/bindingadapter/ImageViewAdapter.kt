package com.dream.jetpackdemo.databinding.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/10
 */
object ImageViewAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView,imageUrl: String?){
        imageUrl.apply {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}