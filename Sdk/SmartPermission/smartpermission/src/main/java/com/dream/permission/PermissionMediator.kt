package com.dream.permission

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dream.permission.dialog.allSpecialPermissions
import com.dream.permission.request.PermissionBuilder
import com.dream.permission.request.RequestBackgroundLocationPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
class PermissionMediator {

    private var activity: FragmentActivity? = null
    private var fragment: Fragment? = null


    constructor(activity: FragmentActivity){
        this.activity = activity
    }

    constructor(fragment: Fragment){
        this.fragment = fragment
    }


    fun permissions(permissions: List<String>): PermissionBuilder {
        val normalPermissionSet = LinkedHashSet<String>()
        val specialPermissionSet = LinkedHashSet<String>()
        val osVersion = Build.VERSION.SDK_INT
        val targetSdkVersion = if(activity != null){
            activity!!.applicationInfo.targetSdkVersion
        }else{
            fragment!!.requireContext().applicationInfo.targetSdkVersion
        }
        for(permission in permissions){
            if(permission in allSpecialPermissions){
                specialPermissionSet.add(permission)
            }else{
                normalPermissionSet.add(permission)
            }
        }
        if(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION in specialPermissionSet){
            if(osVersion == Build.VERSION_CODES.Q || (osVersion == Build.VERSION_CODES.R && targetSdkVersion < Build.VERSION_CODES.R)){
                specialPermissionSet.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
                normalPermissionSet.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)
            }
        }
        //todo 逻辑补齐
        return PermissionBuilder(activity,fragment,normalPermissionSet,specialPermissionSet)
    }

    fun permissions(vararg permissions: String): PermissionBuilder {
        return permissions(listOf(*permissions))
    }
}