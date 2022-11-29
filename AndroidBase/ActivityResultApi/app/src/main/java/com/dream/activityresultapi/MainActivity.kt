package com.dream.activityresultapi

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.dream.activityresultapi.customcontract.GetDataFromSecondActivityContract

/**
系统内置的 Contract：
    StartActivityForResult()
    StartIntentSenderForResult()
    RequestMultiplePermissions()
    RequestPermission()
    TakePicturePreview()
    TakePicture()
    TakeVideo()
    PickContact()
    GetContent()
    GetMultipleContents()
    OpenDocument()
    OpenMultipleDocuments()
    OpenDocumentTree()
    CreateDocument()
 */
class MainActivity : AppCompatActivity() {

    private val tvMain by lazy {
        findViewById<TextView>(R.id.tvMain)
    }

    private val ivPreview by lazy {
        findViewById<ImageView>(R.id.ivPreview)
    }

    private val getBackValue =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data?.getStringExtra("key1")
                tvMain.text = data
            }
        }

    private val getCustomBackValue = registerForActivityResult(GetDataFromSecondActivityContract()){
        tvMain.text = it
    }


    private val getBackPermissionValue =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(this, "申请精确位置权限成功", Toast.LENGTH_SHORT).show()
            }
        }

    private val getBackMultiplePermissionValue =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            for ((key, value) in it) {
                Log.d("erdai", "$key : $value")
            }
        }

    private val getBackBitmapValue = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        ivPreview.setImageBitmap(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("key0", "你好，我是 MainActivity")
        getBackValue.launch(intent)
    }

    fun toSecondActivityWithCustomContract(view: View) {
        getCustomBackValue.launch(null)
    }

    /**
     * 申请单个权限
     */
    fun requestPermission(view: View) {
        getBackPermissionValue.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * 申请多个权限
     */
    fun requestMultiplePermission(view: View) {
        getBackMultiplePermissionValue.launch(
            arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
        )
    }


    /**
     * 调用相机拍照并获取系统返回的 bitmap
     */
    fun takePicturePreview(view: View) {
        getBackBitmapValue.launch(null)
    }


}