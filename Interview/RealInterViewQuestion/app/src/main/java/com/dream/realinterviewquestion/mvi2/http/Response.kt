package com.dream.realinterviewquestion.mvi2.http

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/18
 */

@Parcelize
data class BaseResponse<T>(
    var errorCode: String? = null,
    var errorMsg: String? = null,
) : Parcelable {
    @IgnoredOnParcel
    var data: T? = null
}


@Parcelize
data class FriendJsonResponse(
    @Expose(serialize = false)
    var category: String? = null,
    @Expose(serialize = false)
    var icon: String? = null,
    @Expose(serialize = false)
    var id: Int = 0,
    @Expose(serialize = false)
    var order: Int = 0,
    @Expose(serialize = false)
    var visible: Int = 0,


    var link: String? = null,
    var name: String? = null,
) : Parcelable