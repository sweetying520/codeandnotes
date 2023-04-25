package com.dream.permission.request

import android.Manifest
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.annotation.RequiresApi

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/25
 */
internal class RequestManageExternalStoragePermission internal constructor(permissionBuilder: PermissionBuilder): BaseTask(permissionBuilder){


    companion object{
        /**
         * Android 11 之后才有，因此在申请的时候，注意进行权限的判断
         */
        const val MANAGE_EXTERNAL_STORAGE = "android.permission.MANAGE_EXTERNAL_STORAGE"
    }


    override fun request() {
        if(pb.shouldRequestManageExternalStoragePermission() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if(Environment.isExternalStorageManager()){
                finish()
                return
            }

            if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                val requestList = mutableListOf(MANAGE_EXTERNAL_STORAGE)
                if(pb.explainReasonCallbackWithBeforeParam != null){
                    pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
                }else{
                    pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
                }
            }else{
                finish()
            }
            return
        }

        finish()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun requestAgain(permissions: MutableList<String>?) {
        pb.requestManageExternalStoragePermissionNow(this)
    }

}