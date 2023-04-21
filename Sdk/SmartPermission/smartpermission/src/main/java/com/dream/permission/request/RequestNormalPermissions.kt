package com.dream.permission.request

import android.util.Log
import com.dream.permission.SmartPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/20
 */
internal class RequestNormalPermissions internal constructor(permissionBuilder: PermissionBuilder): BaseTask(permissionBuilder) {


    override fun request() {
        Log.d("erdai", "RequestNormalPermissions request...")
        val requestList = ArrayList<String>()
        for(permission in pb.normalPermissions){
            if(SmartPermission.isGranted(pb.activity,permission)){
                pb.grantedPermissions.add(permission)
            }else{
                requestList.add(permission)
            }
        }
        if(requestList.isEmpty()){
            finish()
            return
        }

        if(pb.explainReasonBeforeRequest && (pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null)){
            pb.explainReasonBeforeRequest = false
            pb.deniedPermissions.addAll(requestList)
            if(pb.explainReasonCallbackWithBeforeParam != null){
                Log.d("erdai", "RequestNormalPermissions explainReasonCallbackWithBeforeParam...")
                pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope,requestList,true)
            }else{
                Log.d("erdai", "RequestNormalPermissions explainReasonCallback...")
                pb.explainReasonCallback!!.onExplainReason(explainScope,requestList)
            }
        }else{
            Log.d("erdai", "RequestNormalPermissions requestNow")
            pb.requestNow(pb.normalPermissions,this)
        }

    }

    override fun requestAgain(permissions: List<String>) {
        Log.d("erdai", "RequestNormalPermissions requestAgain...")
        val permissionsToRequestAgain: MutableSet<String> = HashSet(pb.grantedPermissions)
        permissionsToRequestAgain.addAll(permissions)
        if(permissionsToRequestAgain.isNotEmpty()){
            pb.requestNow(permissionsToRequestAgain,this)
        }else{
            finish()
        }
    }
}