package com.dream.coroutinedemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dream.coroutinedemo.repositories.MainRepositories
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlin.concurrent.thread

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/8
 */
class MainViewModel : ViewModel() {


    val timeFlow = flow {
        var time = 0
        while (true) {
            emit(time)
            delay(1000)
            time++
        }
    }




    private val repositories = MainRepositories()

    val friendJsonLiveData: MutableLiveData<String?> = MutableLiveData()


    fun getFriendJson() {
        viewModelScope.launch {
            val friendJson = repositories.getFriendJson()
            Log.d("erdai", "getFriendJson: $friendJson")
            friendJsonLiveData.value = friendJson
        }
    }

    /**
     * 测试
     */
    fun test() {
        viewModelScope.launch {
            coroutineScope {
                delay(1000)
                Log.d("erdai1", "test内部: ${Thread.currentThread()}")
                thread {
                    Log.d("erdai", "test thread: ${Thread.currentThread()}")
                }

                delay(1000)
                Log.d("erdai1", "test1内部: ${Thread.currentThread()}")

            }
            Log.d("erdai1", "test: ${Thread.currentThread()}")
        }
    }

    /**
     * 测试1
     */
    fun testClick1() {
        viewModelScope.launch {
            Log.d("erdai", "testClick1: ${Thread.currentThread()}")
            val friendJson1 = try {
                repositories.getFriendJson1()
            } catch (e: Exception) {
                e.printStackTrace()
                "erdai"
                Log.d("erdai", "testClick1: ${Thread.currentThread()}")
            }
            Log.d("erdai", "testClick1: $friendJson1")
        }
    }
}