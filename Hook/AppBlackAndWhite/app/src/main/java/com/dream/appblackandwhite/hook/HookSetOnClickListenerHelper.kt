package com.dream.appblackandwhite.hook

import android.content.Context
import android.view.View
import android.widget.Toast
import java.lang.reflect.Proxy

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/10
 */
object HookSetOnClickListenerHelper {

    /**
     * context：上下文
     * view：当前 view 对象
     */
    fun hook(context: Context,view: View){
        //一、拿到当前 View 对象的 ListenerInfo 对象
        val getListenerInfoMethod = View::class.java.getDeclaredMethod("getListenerInfo")
        //破坏封装，让我们能访问 private 修饰的成员
        getListenerInfoMethod.isAccessible = true
        val listenerInfo = getListenerInfoMethod.invoke(view)

        //二、通过 ListenerInfo 对象在拿到 OnClickListener 对象
        //android.view.View$ListenerInfo 这种是内部类的写法
        val mOnClickListenerFiled = Class.forName("android.view.View\$ListenerInfo").getDeclaredField("mOnClickListener")
        val mOnClickListener = mOnClickListenerFiled.get(listenerInfo) as View.OnClickListener

        //三、创建代理对象
        //1、法一
        val proxyClickListener = ProxyClickListener(context,mOnClickListener)

        //2、法二：因为 OnClickListener 是一个接口，我们可以使用 JDK 动态代理
//        val proxyClickListener = Proxy.newProxyInstance(
//            context.classLoader,
//            arrayOf(View.OnClickListener::class.java)
//        ) { proxy, method, args ->
//            Toast.makeText(context, "erdai666", Toast.LENGTH_SHORT).show()
//            method?.invoke(mOnClickListener, *args)
//        }


        //四、使用代理对象替换原始对象
        mOnClickListenerFiled.set(listenerInfo,proxyClickListener)
    }
}