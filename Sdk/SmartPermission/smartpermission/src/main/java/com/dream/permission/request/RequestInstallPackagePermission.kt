package com.dream.permission.request

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/25
 */
internal class RequestInstallPackagePermission internal constructor(permissionBuilder: PermissionBuilder): BaseTask(permissionBuilder){


    companion object{
        /**
         * Android 6.0 之后才有，因此在申请的时候，注意进行权限的判断
         */
        const val REQUEST_INSTALL_PACKAGE = "android.permission.REQUEST_INSTALL_PACKAGES"
    }


    override fun request() {
        if(pb.shouldRequestInstallPackagePermission() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && pb.targetSdkVersion >= Build.VERSION_CODES.O){
            if(pb.activity.packageManager.canRequestPackageInstalls()){
                finish()
                return
            }

            if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                val requestList = mutableListOf(REQUEST_INSTALL_PACKAGE)
                if(pb.explainReasonCallbackWithBeforeParam != null){
                    pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
                }else{
                    pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
                }
            }else{
                finish()
            }
        }else{
            finish()
        }
    }

    override fun requestAgain(permissions: MutableList<String>?) {
        pb.requestInstallPackagePermissionNow(this)
    }

}