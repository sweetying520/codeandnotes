package com.dream.permission.request

import android.app.Dialog
import android.content.pm.ActivityInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.dream.permission.callback.ExplainReasonCallback
import com.dream.permission.callback.ExplainReasonCallbackWithBeforeParam
import com.dream.permission.callback.ForwardToSettingsCallback
import com.dream.permission.callback.RequestCallback

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
class PermissionBuilder(
    fragmentActivity: FragmentActivity?,
    fragment: Fragment?,
    normalPermissions: MutableSet<String>?,
    specialPermissions: MutableSet<String>?,
) {

    lateinit var activity: FragmentActivity

    private var fragment: Fragment? = null

    private var lightColor = -1
    private var darkColor = -1

    private var originRequestOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    private val fragmentManager: FragmentManager
        get() {
            return fragment?.childFragmentManager?:activity.supportFragmentManager
        }

    private val invisibleFragment: InvisibleFragment
        get() {
            val existedFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
            return if(existedFragment != null){
                existedFragment as InvisibleFragment
            }else{
                val invisibleFragment = InvisibleFragment()
                fragmentManager.beginTransaction()
                    .add(invisibleFragment, FRAGMENT_TAG)
                    .commitNowAllowingStateLoss()
                invisibleFragment
            }
        }

    @JvmField
    var currentDialog: Dialog? = null

    @JvmField
    var normalPermissions: MutableSet<String>? = null

    @JvmField
    var specialPermissions: MutableSet<String>? = null

    @JvmField
    var explainReasonBeforeRequest = false

    @JvmField
    var showDialogCalled = false

    @JvmField
    var grantedPermissions: MutableSet<String> = LinkedHashSet()

    @JvmField
    var deniedPermissions: MutableSet<String> = LinkedHashSet()

    @JvmField
    var permanentDeninedPermissions: MutableSet<String> = LinkedHashSet()

    @JvmField
    var tempPermanentDeniedPermissions: MutableSet<String> = LinkedHashSet()

    @JvmField
    var requestCallback: RequestCallback? = null

    @JvmField
    var explainReasonCallback: ExplainReasonCallback? = null

    @JvmField
    var explainReasonCallbackWithBeforeParam: ExplainReasonCallbackWithBeforeParam? = null

    @JvmField
    var forwardToSettingsCallback: ForwardToSettingsCallback? = null

    val targetSdkVersion: Int
        get() = activity.applicationInfo.targetSdkVersion

    fun onExplainRequestReason(callback: ExplainReasonCallback?): PermissionBuilder {
        explainReasonCallback = callback
        return this
    }

    fun onExplainRequestReason(callback: ExplainReasonCallbackWithBeforeParam?): PermissionBuilder {
        explainReasonCallbackWithBeforeParam = callback
        return this
    }

    fun forwardToSettings(callback: ForwardToSettingsCallback?): PermissionBuilder {
        forwardToSettingsCallback = callback
        return this
    }


    fun explainReasonBeforeRequest(): PermissionBuilder {
        explainReasonBeforeRequest = true
        return this
    }

    fun setDialogTintColor(lightColor: Int,darkColor: Int): PermissionBuilder {
        this.lightColor = lightColor
        this.darkColor = darkColor
        return this
    }

    fun request(callback: RequestCallback?){
        requestCallback = callback
        startRequest()
    }

    private fun startRequest() {

    }

    fun requestNow(permissions: Set<String>,chainTask: ChainTask){
        invisibleFragment.requestNow(this,permissions,chainTask)
    }


    fun requestAccessBackgroundLocationPermissionNow(chainTask: ChainTask){
        invisibleFragment.requestAccessBackgroundLocationPermissionNow(this,chainTask)
    }



    init {
        if(fragmentActivity != null){
            this.activity = fragmentActivity
        }

        if(fragment != null){
            this.fragment = fragment
        }

        this.normalPermissions = normalPermissions
        this.specialPermissions = specialPermissions
    }

    companion object{
        private const val FRAGMENT_TAG = "InvisibleFragment"
    }
}