package com.dream.permission.request

import android.Manifest
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.dream.permission.SmartPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/25
 */
internal class RequestBodySensorsBackgroundPermission internal constructor(permissionBuilder: PermissionBuilder): BaseTask(permissionBuilder){


    companion object{
        const val BODY_SENSORS_BACKGROUND = "android.permission.BODY_SENSORS_BACKGROUND"
    }


    override fun request() {
        if(pb.shouldRequestBodySensorsBackgroundPermission()){
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
                pb.specialPermissions.remove(BODY_SENSORS_BACKGROUND)
                pb.permissionsWontRequest.add(BODY_SENSORS_BACKGROUND)
                finish()
                return
            }

            if(SmartPermission.isGranted(pb.activity, BODY_SENSORS_BACKGROUND)){
                finish()
                return
            }

            val bodySensorGranted = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH){
                SmartPermission.isGranted(pb.activity,Manifest.permission.BODY_SENSORS)
            }else{
                false
            }

            if(bodySensorGranted){
                if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                    val requestList = mutableListOf(BODY_SENSORS_BACKGROUND)
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
                    }else{
                        pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
                    }
                }else{
                    requestAgain(emptyList())
                }
                return
            }
        }
        finish()
    }

    override fun requestAgain(permissions: List<String>?) {
        pb.requestBodySensorsBackgroundPermissionNow(this)
    }

}