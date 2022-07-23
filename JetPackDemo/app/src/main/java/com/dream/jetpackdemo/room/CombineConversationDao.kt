package com.dream.jetpackdemo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akulaku.chat.model.entity.CombineConversationEntity

/**
 * function: 通过接口注解定义数据的增删查改方法

 *
 * @author zy
 * @since 2022/7/22
 */

@Dao
interface CombineConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(conversationList: MutableList<CombineConversationEntity>)

    @Query("SELECT * FROM tb_conversation")
    fun queryAll(): MutableList<CombineConversationEntity>
}