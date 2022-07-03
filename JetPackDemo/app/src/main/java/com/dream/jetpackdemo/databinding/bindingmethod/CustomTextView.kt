package com.dream.jetpackdemo.databinding.bindingmethod

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/10
 */
@BindingMethods(
    value =
    [
        BindingMethod(
            type = CustomTextView::class,
            attribute = "android:custom_text",
            method = "showCustomToast"
        )
    ]
)
class CustomTextView : AppCompatTextView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun showCustomToast(text: String){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }
}