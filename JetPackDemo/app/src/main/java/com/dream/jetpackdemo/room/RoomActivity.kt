package com.dream.jetpackdemo.room

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dream.jetpackdemo.databinding.ActivityRoomBinding
import kotlin.concurrent.thread

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/22
 */
class RoomActivity: AppCompatActivity() {


    private lateinit var mBinding: ActivityRoomBinding
    private val mViewModel by viewModels<RoomViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initClickListener()
    }

    private fun initClickListener() {
        mBinding.btnInsertAll.setOnClickListener {
            val list = mockCombineConversationListData()
            mViewModel.insertAll(list)
        }

        mBinding.btnQueryAll.setOnClickListener {
            thread {
                val queryAll = mViewModel.queryAll()
                queryAll?.forEach {
                    Log.d("erdai", "initClickListener: ${it.desc}")
                }
            }
        }
    }

    private fun mockCombineConversationListData(): MutableList<CombineConversationEntity> {
        val list = mutableListOf<CombineConversationEntity>()
        for(i in 1..20){
            val conversation = CombineConversationEntity(
                id = "erdai $i",
                title = "哈哈 $i",
                desc = "这是一段描述 $i"
            )
            list.add(conversation)
        }
        return list
    }

}