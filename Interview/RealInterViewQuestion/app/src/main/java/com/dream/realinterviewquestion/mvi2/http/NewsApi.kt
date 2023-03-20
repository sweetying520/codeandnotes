package com.dream.realinterviewquestion.mvi2.http

import retrofit2.http.GET

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/18
 */
interface NewsApi {

    @GET("friend/json")
    suspend fun getFriendJson(): BaseResponse<MutableList<FriendJsonResponse>>
}