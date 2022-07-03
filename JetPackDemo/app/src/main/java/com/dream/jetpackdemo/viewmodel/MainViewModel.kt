package com.dream.jetpackdemo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/6
 */
const val NUMBER_KEY = "number_key"

class MainViewModel(private val state: SavedStateHandle? = null): ViewModel() {
    var number: Int
    get() {
        return state?.get<Int>(NUMBER_KEY) ?: 0
    }
    set(value) {
        state?.set(NUMBER_KEY, value)
    }
}