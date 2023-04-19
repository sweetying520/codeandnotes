package com.dream.permission.request

import android.Manifest
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.dream.permission.SmartPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
class InvisibleFragment: Fragment() {

    private val handler = Handler(Looper.getMainLooper())

    private lateinit var pb: PermissionBuilder

    private lateinit var task: ChainTask

    private val requestNormalPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()
        ) {
            postForResult {
                onRequestNormalPermissionsResult(it)
            }
        }

    private val requestBackgroundLocationLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            postForResult {
                onRequestBackgroundLocationPermissionResult(it)
            }
        }


    fun requestNow(
        permissionBuilder: PermissionBuilder,
        permissions: Set<String>,
        chainTask: ChainTask
    ){
        pb = permissionBuilder
        task = chainTask
        requestNormalPermissionLauncher.launch(permissions.toTypedArray())
    }

    fun requestAccessBackgroundLocationPermissionNow(
        permissionBuilder: PermissionBuilder,
        chainTask: ChainTask,
    ) {

    }

    private fun onRequestNormalPermissionsResult(grantResults: Map<String,Boolean>){
        if(checkForGC()){
            pb.grantedPermissions.clear()
            val showReasonList: MutableList<String> = mutableListOf()
            val forwardList: MutableList<String> = mutableListOf()
            for((permission,granted) in grantResults){
                if(granted){
                    pb.grantedPermissions.add(permission)
                    pb.deniedPermissions.remove(permission)
                    pb.permanentDeninedPermissions.remove(permission)
                }else{
                    val shouldShowRationale = shouldShowRequestPermissionRationale(permission)
                    if(shouldShowRationale){
                        showReasonList.add(permission)
                        pb.deniedPermissions.add(permission)
                    }else{
                        forwardList.add(permission)
                        pb.permanentDeninedPermissions.add(permission)
                        pb.deniedPermissions.remove(permission)
                    }
                }
            }
            val deniedPermissions: MutableList<String> = mutableListOf()
            deniedPermissions.addAll(pb.deniedPermissions)
            deniedPermissions.addAll(pb.permanentDeninedPermissions)

            for(permission in deniedPermissions){
                if(SmartPermission.isGranted(requireContext(),permission)){
                    pb.deniedPermissions.remove(permission)
                    pb.grantedPermissions.add(permission)
                }
            }

            val allGranted = pb.grantedPermissions.size == pb.normalPermissions?.size
            if(allGranted){
                task.finish()
            }else{
                var shouldFinishTheTask = true
                if((pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null) && showReasonList.isNotEmpty()){
                    shouldFinishTheTask = false
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(task.explainScope, ArrayList(pb.deniedPermissions),false)
                    }else{
                        pb.explainReasonCallback!!.onExplainReason(task.explainScope,ArrayList(pb.deniedPermissions))
                    }
                    pb.tempPermanentDeniedPermissions.addAll(forwardList)
                }else if(pb.forwardToSettingsCallback != null && (forwardList.isNotEmpty() || pb.permanentDeninedPermissions.isNotEmpty())){
                    shouldFinishTheTask = false
                    pb.tempPermanentDeniedPermissions.clear()
                    pb.forwardToSettingsCallback!!.onForwardToSettings(task.forwardScope,ArrayList(pb.permanentDeninedPermissions))
                }

                if(shouldFinishTheTask || !pb.showDialogCalled){
                    task.finish()
                }

                pb.showDialogCalled = false
            }
        }
    }

    private fun onRequestBackgroundLocationPermissionResult(granted: Boolean?) {
        if(checkForGC()){
            postForResult {

            }
        }
    }




    private fun onRequestManageExternalStoragePermissionResult(){
        if(checkForGC()){
            postForResult {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.R){
                    if(Environment.isExternalStorageManager()){
                        task.finish()
                    }else if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                        if(pb.explainReasonCallbackWithBeforeParam != null){
                            pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(
                                task.explainScope,
                                listOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE),
                                false
                            )
                        }else{
                            pb.explainReasonCallback!!.onExplainReason(
                                task.explainScope,
                                listOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                            )
                        }
                    }
                }else{
                    task.finish()
                }
            }
        }
    }



    private fun checkForGC(): Boolean{
        if(!::pb.isInitialized || !::task.isInitialized){
            return false
        }
        return true
    }


    private fun postForResult(callback: () -> Unit){
        handler.post {
            callback()
        }
    }


}