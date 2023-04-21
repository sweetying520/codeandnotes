package com.dream.permission.request

import androidx.fragment.app.DialogFragment
import com.dream.permission.dialog.RationaleDialog

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
class ForwardScope internal constructor(
    private val pb: PermissionBuilder,
    private val chainTask: ChainTask
){

    @JvmOverloads
    fun showForwardToSettingsDialog(permissions: List<String>,message: String,positiveText: String,negativeText: String? = null){
        pb.showHandlePermissionDialog(chainTask,false,permissions,message,positiveText,negativeText)
    }

    fun showForwardToSettingsDialog(rationaleDialog: RationaleDialog){
        pb.showHandlePermissionDialog(chainTask,false,rationaleDialog)
    }


    fun showForwardToSettingsDialog(dialogFragment: DialogFragment){

    }

}