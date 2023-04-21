package com.dream.permission.request

import androidx.fragment.app.DialogFragment
import com.dream.permission.dialog.RationaleDialog

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
class ExplainScope internal constructor(
    private val pb: PermissionBuilder,
    private val chainTask: ChainTask
){

    @JvmOverloads
    fun showRequestReasonDialog(permissions: List<String>,message: String,positiveText: String,negativeText: String? = null){
        pb.showHandlePermissionDialog(chainTask,true,permissions,message,positiveText,negativeText)
    }

    fun showRequestReasonDialog(rationaleDialog: RationaleDialog){
        pb.showHandlePermissionDialog(chainTask,true,rationaleDialog)
    }


    fun showRequestReasonDialog(dialogFragment: DialogFragment){

    }



}