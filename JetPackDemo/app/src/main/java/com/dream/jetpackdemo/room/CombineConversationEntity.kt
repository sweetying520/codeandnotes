package com.akulaku.chat.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * function: 组合后的会话实体类
 *
 * @author zy
 * @since 2022/5/17
 */
@Entity(tableName = "tb_conversation")
@Keep
@Parcelize
class CombineConversationEntity(
    /**
     * 会话id
     */
    @PrimaryKey
    var id: String,
    /**
     * 会话标题
     */
    var title: String? = null,
    /**
     * 会话描述
     */
    var desc: String? = null,
    /**
     * 头像
     */
    var avatar: String? = null,
    /**
     * 更新事件
     */
    var updateTime: Long = 0,
    /**
     * 未读数
     */
    var unreadNum: Int = 0,
    /**
     * 组织 Id
     */
    var orgId: Long = 0,
    /**
     * type：0：livechat 1：push
     */
    var type: Int = 0,
    /**
     * 推送类型：1：普通会话 2：系统会话
     */
    var pushType: Int = 0,
    /**
     * 是否置顶
     */
    var isTop: Boolean = false,
    /**
     * 对方账号
     */
    var otherSideAccount: String? = null,
    /**
     * 组织类型账号
     */
    var orgAccountType: Long = 0,
    /**
     * 会话ID 中台系统
     */
    var cid: String? = null,
): Parcelable