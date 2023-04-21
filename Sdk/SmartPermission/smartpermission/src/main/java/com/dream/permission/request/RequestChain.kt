package com.dream.permission.request

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/20
 */
class RequestChain {

    private var headTask: BaseTask? = null
    private var tailTask: BaseTask? = null


    internal fun addTaskToChain(task: BaseTask){
        if(headTask == null){
            headTask = task
        }
        tailTask?.next = task
        tailTask = task
    }

    internal fun runTask(){
        headTask?.request()
    }
}