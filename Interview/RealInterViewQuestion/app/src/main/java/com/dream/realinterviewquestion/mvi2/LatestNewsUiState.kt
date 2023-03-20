package com.dream.realinterviewquestion.mvi2

import com.dream.realinterviewquestion.mvi2.http.FriendJsonResponse

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/18
 */
sealed class LatestNewsUiState {

    data class Success(var dataList: MutableList<FriendJsonResponse>?): LatestNewsUiState()
    data class Error(var exception: Throwable): LatestNewsUiState()
    object Loading: LatestNewsUiState()
}