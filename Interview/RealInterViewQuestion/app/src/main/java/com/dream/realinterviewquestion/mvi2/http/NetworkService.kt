package com.dream.realinterviewquestion.mvi2.http

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/18
 */
object NetworkService {

    private var httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("erdai666", message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    private val retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .callTimeout(5,TimeUnit.SECONDS)
                .build()
        )
        .baseUrl("https://www.wanandroid.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newApi: NewsApi = retrofit.create(NewsApi::class.java)
}