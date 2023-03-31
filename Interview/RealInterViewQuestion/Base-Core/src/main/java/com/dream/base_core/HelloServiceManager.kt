package com.dream.base_core

import java.util.ServiceLoader

/**
 * function: HelloService 管理器
 *
 * @author zy
 * @since 2023/3/31
 */
object HelloServiceManager {

    private val iterator: Iterator<HelloService>

    init {
        iterator = ServiceLoader.load(HelloService::class.java).iterator()
    }

    fun getHelloService(): HelloService{
        return iterator.next()
    }

    fun hasNext(): Boolean{
        return iterator.hasNext()
    }
}