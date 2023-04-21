package com.dream.permission.request

import android.util.Log
import com.dream.permission.SmartPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
internal abstract class BaseTask(@JvmField var pb: PermissionBuilder): ChainTask {


    @JvmField
    var next: ChainTask? = null


    private var explainReasonScope: ExplainScope = ExplainScope(pb,this)
    private var forwardToSettingsScope: ForwardScope = ForwardScope(pb,this)


    override fun getExplainScope(): ExplainScope {
        return explainReasonScope
    }

    override fun getForwardScope(): ForwardScope {
        return forwardToSettingsScope
    }

    override fun finish() {
        if(next == null){
            Log.d("erdai", "no next，execute end request")
        }else{
            Log.d("erdai", "${this.next?.javaClass?.simpleName}  next request...")
        }
        next?.request()?:run {
            Log.d("erdai", "BaseTask finish...")
            val deniedList: MutableList<String> = ArrayList()
            deniedList.addAll(pb.deniedPermissions)
            deniedList.addAll(pb.permanentDeninedPermissions)
            deniedList.addAll(pb.permissionsWontRequest)
            if(pb.shouldRequestBackgroundLocationPermission()){
                if(SmartPermission.isGranted(pb.activity,RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)){
                    pb.grantedPermissions.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                }else{
                    deniedList.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                }
            }
            //todo 一些特殊的权限

            if(pb.requestCallback != null){
                pb.requestCallback!!.onResult(deniedList.isEmpty(),ArrayList(pb.grantedPermissions),deniedList)
            }

            pb.endRequest()
        }
    }

}