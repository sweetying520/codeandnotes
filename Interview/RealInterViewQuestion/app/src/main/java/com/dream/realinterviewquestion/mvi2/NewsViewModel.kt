package com.dream.realinterviewquestion.mvi2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dream.realinterviewquestion.mvi2.http.BaseResponse
import com.dream.realinterviewquestion.mvi2.http.FriendJsonResponse
import com.dream.realinterviewquestion.mvi2.http.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/18
 */

sealed class NewsIntent{
    object GetNews: NewsIntent()
    object GetNewsDetail: NewsIntent()
    object QueryNews: NewsIntent()
}

class NewsViewModel: ViewModel(){
    val newsChannel = Channel<NewsIntent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow<LatestNewsUiState>(LatestNewsUiState.Success(null))
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            newsChannel.consumeAsFlow().collect{
                when(it){
                    is NewsIntent.GetNews -> getNewData()
                    is NewsIntent.GetNewsDetail -> getNewsDetail()
                    is NewsIntent.QueryNews -> queryNews()
                }
            }
        }
    }

    private fun queryNews() {
        TODO("Not yet implemented")
    }

    private fun getNewsDetail() {
        TODO("Not yet implemented")
    }

    private fun getNewData() {
        _uiState.value = LatestNewsUiState.Loading
        viewModelScope.launch {
            latestNews.flowOn(Dispatchers.Default)
                .catch { exception ->
                    _uiState.value = LatestNewsUiState.Error(exception)
                }.collect{ response ->
                    _uiState.value = LatestNewsUiState.Success(response.data)
                }
        }
    }

    private val latestNews: Flow<BaseResponse<MutableList<FriendJsonResponse>>> = flow {
        val latestNewsList = NetworkService.newApi.getFriendJson()
        emit(latestNewsList)
    }
}