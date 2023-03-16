package com.example.startupinit

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
class MyInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        Log.d("erdai", "create: MyInitializer")
        DrawableManager.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}