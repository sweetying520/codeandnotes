package com.dream.performanceoptimization.anr

import android.util.Log
import kotlin.concurrent.thread

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/7
 */
class AnrMock {

    fun testAnr(){

        val lock1 = Object()
        val lock2 = Object()

        //子线程持有锁1，想要竞争锁2
        thread {
            synchronized(lock1){
                Thread.sleep(100)

                synchronized(lock2){
                    Log.d("erdai", "testAnr: getLock2")
                }
            }
        }

        //主线程持有锁2，想要竞争锁1
        synchronized(lock2){
            Thread.sleep(100)

            synchronized(lock1){
                Log.d("erdai", "testAnr: getLock1")
            }
        }
    }
}