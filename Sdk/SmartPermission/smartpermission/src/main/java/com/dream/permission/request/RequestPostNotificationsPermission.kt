package com.dream.permission.request

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
internal class RequestPostNotificationsPermission internal constructor(permissionBuilder: PermissionBuilder): BaseTask(permissionBuilder){




    override fun request() {
        if(pb.shouldRequestPostNotificationsPermission()){
            if(SmartPermission.areNotificationsEnabled(pb.activity)){
                finish()
                return
            }
            if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                val requestList = mutableListOf(SmartPermission.Permission.POST_NOTIFICATIONS)
                if(pb.explainReasonCallbackWithBeforeParam != null){
                    pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
                }else{
                    pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
                }
            }
            return
        }
        finish()
    }

    override fun requestAgain(permissions: MutableList<String>?) {
        pb.requestPostNotificationsPermissionNow(this)
    }

}