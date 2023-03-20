package com.dream.realinterviewquestion.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/17
 */
class LoginViewModel: ViewModel() {

    private val _viewStates = MutableStateFlow(LoginViewState())
    val viewStates = _viewStates.asStateFlow()
    private val _viewEvents = MutableSharedFlow<LoginViewEvent>()
    val viewEvents = _viewEvents.asSharedFlow()

    fun dispatch(viewAction: LoginViewAction){
        when(viewAction){
            is LoginViewAction.UpdateUserName -> updateUserName()
            is LoginViewAction.UpdatePassword -> updatePwd()
            is LoginViewAction.Login -> login()
        }
    }

    private fun login() {
    }

    private fun updatePwd() {
    }

    private fun updateUserName() {

    }
}