package com.dream.uieffectdemo.imedittext

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ContactSelectBean(
    var nickName: String? = "",
    var account: String? = "",
    var imageUrl: String? = "https://raw.githubusercontent.com/sweetying520/picgo/master/img/im2.png"
): Parcelable