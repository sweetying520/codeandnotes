package com.dream.jetpackdemo.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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