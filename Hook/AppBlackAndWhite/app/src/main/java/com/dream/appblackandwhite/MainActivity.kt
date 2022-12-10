package com.dream.appblackandwhite

import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dream.appblackandwhite.hook.HookSetOnClickListenerHelper


class MainActivity: BaseActivity() {

//    //色彩饱和度为 0 的 Paint
//    private val paint by lazy {
//        val p = Paint()
//        val cm = ColorMatrix()
//        cm.setSaturation(0f)
//        p.colorFilter = ColorMatrixColorFilter(cm)
//        p
//    }
//
//
//    private val tvBlackAndWhite by lazy {
//        findViewById<TextView>(R.id.tvBlackAndWhite)
//    }
//
//    private val btnBlackAndWhite by lazy {
//        findViewById<Button>(R.id.btnBlackAndWhite)
//    }

    private val btnHook by lazy {
        findViewById<Button>(R.id.btnHook)
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
//        tvBlackAndWhite.setLayerType(View.LAYER_TYPE_HARDWARE,paint)
//        btnBlackAndWhite.setLayerType(View.LAYER_TYPE_HARDWARE,paint)

        btnHook.setOnClickListener{
            Log.d("MainActivity", "Hello")
        }
        HookSetOnClickListenerHelper.hook(this,btnHook)
    }


    /**
     * Dialog
     */
    fun btnClick(view: View) {
        AlertDialog.Builder(this)
            .setTitle("标题")
            .setMessage("owejfioweofwe")
            .setPositiveButton("确定"){dialog,which->
                dialog.dismiss()
            }
            .setNegativeButton("取消"){dialog,which->
                dialog.dismiss()
            }
            .show()
    }

    /**
     * PopupWindow
     */
    fun btnClick1(view: View) {
        val contentView = layoutInflater.inflate(R.layout.popup_window_view,null)
        val popupWindow = PopupWindow(
            contentView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable())
        popupWindow.showAsDropDown(view)
    }


}