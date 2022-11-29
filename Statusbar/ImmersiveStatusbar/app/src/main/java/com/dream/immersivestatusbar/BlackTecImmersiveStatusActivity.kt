package com.dream.immersivestatusbar

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.blankj.utilcode.util.BarUtils

class BlackTecImmersiveStatusActivity : AppCompatActivity() {

    private lateinit var bgImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_black_tec_immersive_status)


        MyBarUtils.enableTransparentStatusBar(this)
        bgImageView = findViewById(R.id.bg_image)
        val darkImageBtn = findViewById<Button>(R.id.dark_image_btn)
        val lightImageBtn = findViewById<Button>(R.id.light_image_btn)
        val splitImageBtn = findViewById<Button>(R.id.split_image_btn)

        darkImageBtn.setOnClickListener {
            setBgImageByResource(R.drawable.dark_image)
        }
        lightImageBtn.setOnClickListener {
            setBgImageByResource(R.drawable.light_image)
        }
        splitImageBtn.setOnClickListener {
            setBgImageByResource(R.drawable.split_image)
        }

        setBgImageByResource(R.drawable.dark_image)
    }

    private fun setBgImageByResource(imageResource: Int) {
        val bitmap = BitmapFactory.decodeResource(resources, imageResource)
        bgImageView.setImageBitmap(bitmap)
        detectBitmapColor(bitmap)
    }

    private fun detectBitmapColor(bitmap: Bitmap) {
        val colorCount = 5
        val top = 0
        val bottom = getStatusBarHeight()
        val left = 0
        val right = getScreenWidth()
        Palette
            .from(bitmap)
            .maximumColorCount(colorCount)
            .setRegion(left,top,right,bottom)
            .generate {
                it?.let { palette ->
                    var mostPopularSwatch: Palette.Swatch? = null
                    for (swatch in palette.swatches) {
                        if(mostPopularSwatch == null || swatch.population > mostPopularSwatch.population){
                            mostPopularSwatch = swatch
                        }
                    }
                    mostPopularSwatch?.let { swatch ->
                        val luminance = ColorUtils.calculateLuminance(swatch.rgb)
                        if(luminance < 0.5){
                            //亮度小于 0.5 => 暗色，设置亮色状态栏
                            setDartStatusBar()
                        }else{
                            //亮度大于等于 0.5 => 亮色，设置暗色状态栏
                            setLightStatusBar()
                        }

                    }
                }
            }
    }

    private fun setLightStatusBar() {
        val flags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setDartStatusBar() {
        val flags = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun getScreenWidth(): Int{
//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        return displayMetrics.widthPixels
        return resources.displayMetrics.widthPixels
    }


    private fun getStatusBarHeight(): Int{
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if(resourceId > 0){
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}