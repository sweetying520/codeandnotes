package com.dream.jetpackdemo.room

import com.akulaku.chat.model.entity.CombineConversationEntity
import com.dream.jetpackdemo.MyApplication

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/22
 */
object ChatDBConversationHelper {

    private val chatConversationDao: CombineConversationDao = AppDatabase.getDataBase(MyApplication.context!!).combineConversationDao()


    suspend fun insertAll(conversationList: MutableList<CombineConversationEntity>){
        chatConversationDao.insertAll(conversationList)
    }

    fun queryAllConversation(): MutableList<CombineConversationEntity>{
        return chatConversationDao.queryAll()
    }
}