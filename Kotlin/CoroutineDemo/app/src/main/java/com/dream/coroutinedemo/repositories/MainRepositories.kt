package com.dream.coroutinedemo.repositories

import android.util.Log
import com.dream.coroutinedemo.common.WanAndroidApiService
import com.dream.coroutinedemo.model.bean.BaseResponse
import com.dream.coroutinedemo.model.bean.FriendJsonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/8
 */
class MainRepositories {

    suspend fun getFriendJson(): String?{
        try {
            val friendJson = WanAndroidApiService.createWanAndroidApiService().getFriendJson()
            Log.d("erdai", "response: ${friendJson.data}")
            return friendJson.data?.get(0)?.name
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    suspend fun getFriendJson1(): String{
        return suspendCoroutine {
            val friendJson1 = WanAndroidApiService.createWanAndroidApiService().getFriendJson1()
            friendJson1.enqueue(object : Callback<BaseResponse<MutableList<FriendJsonResponse>>>{
                override fun onResponse(
                    call: Call<BaseResponse<MutableList<FriendJsonResponse>>>,
                    response: Response<BaseResponse<MutableList<FriendJsonResponse>>>
                ) {
                    val result = response.body()?.data?.get(0)?.name?:""
                    it.resume(result)
                }

                override fun onFailure(
                    call: Call<BaseResponse<MutableList<FriendJsonResponse>>>,
                    t: Throwable
                ) {
                    it.resumeWithException(Exception(t.message))
                }

            })
        }
    }
}