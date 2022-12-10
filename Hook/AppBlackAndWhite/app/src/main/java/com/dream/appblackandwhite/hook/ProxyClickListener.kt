package com.dream.appblackandwhite.hook

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/10
 */
class ProxyClickListener(var context: Context,var clickListener: View.OnClickListener) : View.OnClickListener {


    override fun onClick(v: View?) {
        Toast.makeText(context,"erdai666",Toast.LENGTH_SHORT).show()
        clickListener.onClick(v)
    }
}