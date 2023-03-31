package com.dream.module_chat

import com.dream.base_core.HelloService

/**
 * function: HelloService 实现类
 *
 * @author zy
 * @since 2023/3/31
 */
class HelloServiceImpl: HelloService {

    override fun sayHello(content: String): String {
        return "Hello $content"
    }

}