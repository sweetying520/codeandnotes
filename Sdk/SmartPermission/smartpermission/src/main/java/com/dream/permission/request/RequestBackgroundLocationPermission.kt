package com.dream.permission.request

import android.Manifest
import android.os.Build
import android.util.Log
import com.dream.permission.SmartPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
internal class RequestBackgroundLocationPermission internal constructor(permissionBuilder: PermissionBuilder):
    BaseTask(permissionBuilder) {


    companion object{
        /**
         * Android 10 之后才有，因此在申请的时候，注意进行权限的判断
         */
        const val ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION"
    }

    override fun request() {
        if(pb.shouldRequestBackgroundLocationPermission()){
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                pb.specialPermissions.remove(ACCESS_BACKGROUND_LOCATION)
                pb.permissionsWontRequest.remove(ACCESS_BACKGROUND_LOCATION)
                finish()
                return
            }
            if(SmartPermission.isGranted(pb.activity, ACCESS_BACKGROUND_LOCATION)){
                finish()
                return
            }
            val accessFineLocationGranted = SmartPermission.isGranted(pb.activity,Manifest.permission.ACCESS_FINE_LOCATION)
            val accessCoarseLocationGranted = SmartPermission.isGranted(pb.activity,Manifest.permission.ACCESS_COARSE_LOCATION)
            if(accessFineLocationGranted || accessCoarseLocationGranted){
                if(pb.explainReasonCallback != null ||  pb.explainReasonCallbackWithBeforeParam != null){
                    val requestList = mutableListOf(ACCESS_BACKGROUND_LOCATION)
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        Log.d("erdai", "RequestBackgroundLocationPermission explainReasonCallbackWithBeforeParam...")
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
                    }else{
                        Log.d("erdai", "RequestBackgroundLocationPermission explainReasonCallback...")
                        pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
                    }
                }else{
                    Log.d("erdai", "RequestBackgroundLocationPermission requestAgain(emptyList())...")
                    requestAgain(emptyList())
                }
                return
            }
        }
        finish()
    }

    override fun requestAgain(permissions: List<String>?) {
        pb.requestAccessBackgroundLocationPermissionNow(this)
    }

}