package com.dream.coroutinedemo.common

import android.util.Log
import com.dream.coroutinedemo.model.bean.BaseResponse
import com.dream.coroutinedemo.model.bean.FriendJsonResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

/**
 * function: 玩安卓接口
 *
 * @author zy
 * @since 2022/3/14
 */
interface WanAndroidApiService {

    @GET("friend/json")
    suspend fun getFriendJson(): BaseResponse<MutableList<FriendJsonResponse>>

    @GET("friend/json")
    fun getFriendJson1(): Call<BaseResponse<MutableList<FriendJsonResponse>>>


    companion object{
        fun createWanAndroidApiService(): WanAndroidApiService{
            val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    Log.d("erdai666", message)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .sslSocketFactory(SSL.sslSocketFactory()!!,SSL.trustManager())
                .hostnameVerifier(SSL.hostnameVerifier())
                .build()

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(WanAndroidApiService::class.java)

        }
    }
}