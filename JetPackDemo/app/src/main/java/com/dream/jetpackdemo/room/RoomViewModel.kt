package com.dream.jetpackdemo.room

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akulaku.chat.model.entity.CombineConversationEntity
import com.dream.jetpackdemo.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/22
 */
class RoomViewModel: ViewModel() {


    fun insertAll(list: MutableList<CombineConversationEntity>){
        viewModelScope.launch(Dispatchers.IO) {
            ChatDBConversationHelper.insertAll(list)
        }
    }

    fun queryAll(): MutableList<CombineConversationEntity>?{
        return ChatDBConversationHelper.queryAllConversation()
    }
}