package com.dream.permission.request

import android.Manifest
import android.os.Build
import android.provider.Settings

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/24
 */
internal class RequestWriteSettingsPermission internal constructor(permissionBuilder: PermissionBuilder): BaseTask(permissionBuilder){


    override fun request() {
        if(pb.shouldRequestWriteSettingsPermission()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && pb.targetSdkVersion >= Build.VERSION_CODES.M){
                if(Settings.System.canWrite(pb.activity)){
                    finish()
                    return
                }

                if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                    val requestList = mutableListOf(Manifest.permission.WRITE_SETTINGS)
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
                    }else{
                        pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
                    }
                }else{
                    finish()
                }
            }else{
                pb.grantedPermissions.add(Manifest.permission.SYSTEM_ALERT_WINDOW)
                pb.specialPermissions.remove(Manifest.permission.SYSTEM_ALERT_WINDOW)
                finish()
            }
        }else{
            finish()
        }
    }

    override fun requestAgain(permissions: MutableList<String>?) {
        pb.requestSystemSettingsPermissionNow(this)
    }

}