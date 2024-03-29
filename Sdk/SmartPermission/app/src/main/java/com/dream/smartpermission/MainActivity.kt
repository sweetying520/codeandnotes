package com.dream.smartpermission

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dream.permission.SmartPermission
import com.dream.permission.callback.RequestCallback
import com.dream.smartpermission.databinding.ActivityMainBinding
import com.dream.smartpermission.databinding.ActivityMainBinding.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)

        initListener()
    }

    private fun initListener() {
        mBinding.btnPermission1.setOnClickListener {
            val requestList = mutableListOf<String>()
            //1、蓝牙
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                requestList.add(android.Manifest.permission.BLUETOOTH_SCAN)
//                requestList.add(android.Manifest.permission.BLUETOOTH_ADVERTISE)
//                requestList.add(android.Manifest.permission.BLUETOOTH_CONNECT)
//            }else{
//                requestList.add(android.Manifest.permission.BLUETOOTH)
//                requestList.add(android.Manifest.permission.BLUETOOTH_ADMIN)
//            }
            //2、存储
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                requestList.add(android.Manifest.permission.READ_MEDIA_IMAGES)
//                requestList.add(android.Manifest.permission.READ_MEDIA_VIDEO)
//                requestList.add(android.Manifest.permission.READ_MEDIA_AUDIO)
//            }else{
//                requestList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//            }

            //3、附近 wifi 权限
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
//                requestList.add(android.Manifest.permission.NEARBY_WIFI_DEVICES)
//            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH){
                requestList.add(android.Manifest.permission.BODY_SENSORS)
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                requestList.add(android.Manifest.permission.BODY_SENSORS_BACKGROUND)
            }

            SmartPermission.init(this)
                .permissions(requestList)
//                .permissions(listOf(/*android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.REQUEST_INSTALL_PACKAGES*//*,android.Manifest.permission.CALL_PHONE*/))
                .explainReasonBeforeRequest()
                .onExplainRequestReason{scope,deniedList,beforeRequest->
//                    if(beforeRequest){
                        scope.showRequestReasonDialog(deniedList,"为了保证程序的正常运行，请同意以下权限","确定","取消")
//                    }else{
//                        val filterDeniedList = deniedList.filter {
//                            it == android.Manifest.permission.CAMERA
//                        }
//                        scope.showRequestReasonDialog(filterDeniedList,"拍照权限是必须的","我已明白")
//                    }
                }
                .onForwardToSettings{scope,deniedList->
                    scope.showForwardToSettingsDialog(deniedList,"不再询问，需要去设置里面打开","确定","取消")
                }
                .request { allGranted, grantedList, deniedList ->
                    Log.d("erdai", "initListener allGranted: $allGranted")
                    Log.d("erdai", "initListener grantedList: $grantedList")
                    Log.d("erdai", "initListener deniedList: $deniedList")
                    if(allGranted){
                        Toast.makeText(this, "二代666", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}