package com.dream.permission.dialog

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.dream.permission.R
import com.dream.permission.databinding.SmartPermissionDefaultDialogLayoutBinding
import com.dream.permission.databinding.SmartPermissionItemBinding

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/20
 */
class DefaultDialog(
    context: Context,
    private val permissions: List<String>,
    private val message: String,
    private val positiveText: String,
    private val negativeText: String?,
    private val lightColor: Int,
    private val darkColor: Int,
) : RationaleDialog(context, R.style.SmartPermissionDefaultDialog) {

    private lateinit var mBinding: SmartPermissionDefaultDialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = SmartPermissionDefaultDialogLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupText()
        buildPermissionsLayout()
        setupWindow()
    }


    private fun setupText() {
        mBinding.messageText.text = message
        mBinding.positiveBtn.text = positiveText
        if (negativeText != null) {
            mBinding.negativeBtn.visibility = View.VISIBLE
            mBinding.negativeBtn.text = negativeText
        } else {
            mBinding.negativeBtn.visibility = View.GONE
        }

        if (isDarkTheme()) {
            if (darkColor != -1) {
                mBinding.positiveBtn.setTextColor(darkColor)
                mBinding.negativeBtn.setTextColor(darkColor)
            }
        } else {
            if (lightColor != -1) {
                mBinding.positiveBtn.setTextColor(lightColor)
                mBinding.negativeBtn.setTextColor(lightColor)
            }
        }
    }

    private fun buildPermissionsLayout() {
        val tempSet = HashSet<String>()
        val currentOsVersion = Build.VERSION.SDK_INT
        for (permission in permissions) {
            val permissionGroup = when {
                currentOsVersion < Build.VERSION_CODES.Q -> {
                    try {
                        val permissionInfo = context.packageManager.getPermissionInfo(permission, 0)
                        permissionInfo.group
                    } catch (e: PackageManager.NameNotFoundException) {
                        e.printStackTrace()
                        null
                    }
                }
                currentOsVersion == Build.VERSION_CODES.Q -> permissionMapOnQ[permission]
                currentOsVersion == Build.VERSION_CODES.R -> permissionMapOnR[permission]
                currentOsVersion == Build.VERSION_CODES.S -> permissionMapOnS[permission]
                currentOsVersion == Build.VERSION_CODES.TIRAMISU -> permissionMapOnT[permission]
                else -> {
                    permissionMapOnT[permission]
                }
            }
            if ((permission in allSpecialPermissions && !tempSet.contains(permissionGroup))
                || (permissionGroup != null && !tempSet.contains(permissionGroup))
            ) {
                val itemBinding = SmartPermissionItemBinding.inflate(
                    layoutInflater,
                    mBinding.permissionsLayout,
                    false
                )
                when {
                    permission == Manifest.permission.ACCESS_BACKGROUND_LOCATION -> {
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_access_background_location)
                        itemBinding.permissionIcon.setImageResource(
                            context.packageManager.getPermissionGroupInfo(
                                permissionGroup!!,
                                0
                            ).icon
                        )
                    }
                    permission == Manifest.permission.SYSTEM_ALERT_WINDOW -> {
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_system_alert_window)
                        itemBinding.permissionIcon.setImageResource(R.drawable.smartpermission_ic_alert)
                    }
                    permission == Manifest.permission.WRITE_SETTINGS -> {
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_write_settings)
                        itemBinding.permissionIcon.setImageResource(R.drawable.smartpermission_ic_setting)
                    }
                    permission == Manifest.permission.MANAGE_EXTERNAL_STORAGE -> {
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_manage_external_storage)
                        itemBinding.permissionIcon.setImageResource(
                            context.packageManager.getPermissionGroupInfo(
                                permissionGroup!!,
                                0
                            ).icon
                        )
                    }
                    permission == Manifest.permission.REQUEST_INSTALL_PACKAGES -> {
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_request_install_packages)
                        itemBinding.permissionIcon.setImageResource(R.drawable.smartpermission_ic_install)
                    }
                    permission == Manifest.permission.POST_NOTIFICATIONS
                            && Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU -> {
                        // When OS version is lower than Android 13, there isn't a notification icon or labelRes for us to get.
                        // So we need to handle it as special permission's way.
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_post_notification)
                        itemBinding.permissionIcon.setImageResource(R.drawable.smartpermission_ic_notification)
                    }
                    permission == Manifest.permission.BODY_SENSORS_BACKGROUND -> {
                        itemBinding.permissionText.text =
                            context.getString(R.string.smartpermission_body_sensor_background)
                        itemBinding.permissionIcon.setImageResource(
                            context.packageManager.getPermissionGroupInfo(
                                permissionGroup!!,
                                0
                            ).icon
                        )
                    }
                    else -> {
                        itemBinding.permissionText.text = context.getString(
                            context.packageManager.getPermissionGroupInfo(
                                permissionGroup!!,
                                0
                            ).labelRes
                        )
                        itemBinding.permissionIcon.setImageResource(
                            context.packageManager.getPermissionGroupInfo(
                                permissionGroup,
                                0
                            ).icon
                        )
                    }
                }
                if (isDarkTheme()) {
                    if (darkColor != -1) {
                        itemBinding.permissionIcon.setColorFilter(
                            darkColor,
                            PorterDuff.Mode.SRC_ATOP
                        )
                    } else {
                        if (lightColor != -1) {
                            itemBinding.permissionIcon.setColorFilter(
                                lightColor,
                                PorterDuff.Mode.SRC_ATOP
                            )
                        }
                    }
                }
                mBinding.permissionsLayout.addView(itemBinding.root)
                tempSet.add(permissionGroup ?: permission)
            }
        }
    }

    private fun setupWindow() {
        val width = context.resources.displayMetrics.widthPixels
        val height = context.resources.displayMetrics.heightPixels
        if (width < height) {
            window?.let {
                val param = it.attributes
                it.setGravity(Gravity.CENTER)
                param.width = (width * 0.86).toInt()
                it.attributes = param
            }
        } else {
            window?.let {
                val param = it.attributes
                it.setGravity(Gravity.CENTER)
                param.width = (width * 0.6).toInt()
                it.attributes = param
            }
        }
    }

    override fun getPositiveButton(): View {
        return mBinding.positiveBtn
    }

    override fun getNegativeButton(): View? {
        return negativeText?.let {
            mBinding.negativeBtn
        }
    }

    override fun getPermissionToRequest(): List<String> {
        return permissions
    }

    private fun isDarkTheme(): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }

    internal fun isPermissionLayoutEmpty(): Boolean {
        return mBinding.permissionsLayout.childCount == 0
    }

}