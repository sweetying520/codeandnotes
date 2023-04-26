package com.dream.permission.request

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
            Log.d("erdai", "requestNormalPermissionLauncher 向系统发送普通权限请求")
            postForResult {
                onRequestNormalPermissionsResult(it)
            }
        }

    private val requestBackgroundLocationLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            postForResult {
                Log.d("erdai", "requestBackgroundLocationLauncher 向系统请求后台定位权限")
                onRequestBackgroundLocationPermissionResult(it)
            }
        }

    private val requestSystemAlertWindowLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            postForResult {
                Log.d("erdai", "requestSystemAlertWindowLauncher 向系统请求悬浮窗权限")
                onRequestSystemAlertWindowPermissionResult()
            }
        }

    private val requestWriteSettingsWindowLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            postForResult {
                Log.d("erdai", "requestWriteSettingsWindowLauncher 向系统请求修改系统设置权限")
                onRequestWriteSettingsPermissionResult()
            }
        }

    private val requestManageExternalStorageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            postForResult {
                Log.d("erdai", "requestManageExternalStorageLauncher 向系统请求管理存储权限")
                onRequestManageExternalStoragePermissionResult()
            }
        }


    private val requestInstallPackageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            postForResult {
                Log.d("erdai", "requestInstallPackageLauncher 向系统请求安装未知来源包")
                onRequestInstallPackagePermissionResult()
            }
        }



    private val forwardToSettingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(checkForGC()){
                Log.d("erdai", "forwardToSettingsLauncher 跳转到系统设置")
                task.requestAgain(ArrayList(pb.forwardPermissions))
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
        pb = permissionBuilder
        task = chainTask
        requestBackgroundLocationLauncher.launch(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
    }

    fun requestSystemAlertWindowPermissionNow(
        permissionBuilder: PermissionBuilder,
        chainTask: ChainTask,
    ) {
        pb = permissionBuilder
        task = chainTask
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(pb.activity)){
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            intent.data = Uri.parse("package:${requireActivity().packageName}")
            requestSystemAlertWindowLauncher.launch(intent)
        }else{
            onRequestSystemAlertWindowPermissionResult()
        }

    }

    fun requestWriteSettingsPermissionNow(
        permissionBuilder: PermissionBuilder,
        chainTask: ChainTask,
    ) {
        pb = permissionBuilder
        task = chainTask
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(pb.activity)){
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            intent.data = Uri.parse("package:${requireActivity().packageName}")
            requestWriteSettingsWindowLauncher.launch(intent)
        }else{
            onRequestWriteSettingsPermissionResult()
        }
    }


    @SuppressLint("QueryPermissionsNeeded")
    fun requestManageExternalStoragePermissionNow(
        permissionBuilder: PermissionBuilder,
        chainTask: ChainTask,
    ) {
        pb = permissionBuilder
        task = chainTask
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()){
            var intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:${requireActivity().packageName}")
            if(intent.resolveActivity(requireActivity().packageManager) == null){
                intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            }
            requestManageExternalStorageLauncher.launch(intent)
        }else{
            onRequestManageExternalStoragePermissionResult()
        }
    }

    fun requestInstallPackagePermissionNow(permissionBuilder: PermissionBuilder, chainTask: ChainTask) {
        pb = permissionBuilder
        task = chainTask
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !pb.activity.packageManager.canRequestPackageInstalls()){
            val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
            intent.data = Uri.parse("package:${requireActivity().packageName}")
            requestInstallPackageLauncher.launch(intent)
        }else{
            onRequestInstallPackagePermissionResult()
        }
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

            Log.d("erdai", "onRequestNormalPermissionsResult: 结果处理")
            val allGranted = pb.grantedPermissions.size == pb.normalPermissions.size
            if(allGranted){
                task.finish()
            }else{
                var shouldFinishTheTask = true
                if((pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null) && showReasonList.isNotEmpty()){
                    shouldFinishTheTask = false
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        Log.d("erdai", "onRequestNormalPermissionsResult: explainReasonCallbackWithBeforeParam")
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(task.explainScope, ArrayList(pb.deniedPermissions),false)
                    }else{
                        Log.d("erdai", "onRequestNormalPermissionsResult: explainReasonCallback")
                        pb.explainReasonCallback!!.onExplainReason(task.explainScope,ArrayList(pb.deniedPermissions))
                    }
                    pb.tempPermanentDeniedPermissions.addAll(forwardList)
                }else if(pb.forwardToSettingsCallback != null && (forwardList.isNotEmpty() || pb.permanentDeninedPermissions.isNotEmpty())){
                    shouldFinishTheTask = false
                    pb.tempPermanentDeniedPermissions.clear()
                    pb.forwardToSettingsCallback!!.onForwardToSettings(task.forwardScope,ArrayList(pb.permanentDeninedPermissions))
                    Log.d("erdai", "onRequestNormalPermissionsResult: onForwardToSettings")
                }

                if(shouldFinishTheTask || !pb.showDialogCalled){
                    task.finish()
                }

                pb.showDialogCalled = false
            }
        }
    }

    private fun onRequestBackgroundLocationPermissionResult(granted: Boolean) {
        if(checkForGC()){
            postForResult {
                Log.d("erdai", "onRequestBackgroundLocationPermissionResult: 结果处理")
                if(granted){
                    Log.d("erdai", "onRequestBackgroundLocationPermissionResult granted: true")
                    pb.grantedPermissions.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                    pb.deniedPermissions.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                    pb.permanentDeninedPermissions.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                    task.finish()
                }else{
                    var goesToRequestCallback = true
                    val shouldShowRationale = shouldShowRequestPermissionRationale(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                    if((pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null) && shouldShowRationale){
                        goesToRequestCallback = false
                        val permissionsToExplain: MutableList<String> = ArrayList()
                        permissionsToExplain.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                        if(pb.explainReasonCallbackWithBeforeParam != null){
                            Log.d("erdai", "onRequestBackgroundLocationPermissionResult explainReasonCallbackWithBeforeParam")
                            pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(task.explainScope,permissionsToExplain,false)
                        }else{
                            Log.d("erdai", "onRequestBackgroundLocationPermissionResult explainReasonCallback")
                            pb.explainReasonCallback!!.onExplainReason(task.explainScope,permissionsToExplain)
                        }
                    }else if(pb.forwardToSettingsCallback != null && !shouldShowRationale){
                        Log.d("erdai", "onRequestBackgroundLocationPermissionResult forwardToSettingsCallback")
                        goesToRequestCallback = false
                        val permissionsToForward: MutableList<String> = ArrayList()
                        permissionsToForward.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                        pb.forwardToSettingsCallback!!.onForwardToSettings(task.forwardScope,permissionsToForward)
                    }

                    if(goesToRequestCallback || !pb.showDialogCalled){
                        Log.d("erdai", "onRequestBackgroundLocationPermissionResult task.finish()")
                        task.finish()
                    }
                }
            }
        }
    }

    private fun onRequestSystemAlertWindowPermissionResult() {
        if(checkForGC()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(Settings.canDrawOverlays(requireContext())){
                    task.finish()
                }else if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(task.explainScope,
                            listOf(Manifest.permission.SYSTEM_ALERT_WINDOW),
                            false
                        )
                    }else{
                        pb.explainReasonCallback!!.onExplainReason(task.explainScope, listOf(Manifest.permission.SYSTEM_ALERT_WINDOW))
                    }
                }
            }else{
                task.finish()
            }
        }
    }

    private fun onRequestWriteSettingsPermissionResult() {
        if(checkForGC()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(Settings.System.canWrite(requireContext())){
                    task.finish()
                }else if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(task.explainScope,
                            listOf(Manifest.permission.WRITE_SETTINGS),
                            false
                        )
                    }else{
                        pb.explainReasonCallback!!.onExplainReason(task.explainScope, listOf(Manifest.permission.WRITE_SETTINGS))
                    }
                }
            }else{
                task.finish()
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

    private fun onRequestInstallPackagePermissionResult() {
        if(checkForGC()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                if(requireActivity().packageManager.canRequestPackageInstalls()){
                    task.finish()
                }else if(pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null){
                    if(pb.explainReasonCallbackWithBeforeParam != null){
                        pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(task.explainScope,
                            listOf(RequestInstallPackagePermission.REQUEST_INSTALL_PACKAGE),
                            false
                        )
                    }else{
                        pb.explainReasonCallback!!.onExplainReason(task.explainScope, listOf(RequestInstallPackagePermission.REQUEST_INSTALL_PACKAGE))
                    }
                }
            }else{
                task.finish()
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

    fun forwardToSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package",requireActivity().packageName,null)
        intent.data = uri
        forwardToSettingsLauncher.launch(intent)
    }

}