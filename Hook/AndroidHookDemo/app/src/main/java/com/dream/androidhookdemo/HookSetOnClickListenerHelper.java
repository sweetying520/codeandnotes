package com.dream.androidhookdemo;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/11
 */
public class HookSetOnClickListenerHelper {


    public static void hook(Context context, View v){
        try {
            //一、从 Hook 对象的持有者中拿到 Hook 对象
            //1、先拿到 ListenerInfo：Hook 对象的持有者
            final Method getListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfoMethod.setAccessible(true);
            final Object getListenerInfo = getListenerInfoMethod.invoke(v);

            //2、拿到 Hook 对象 android.view.View$ListenerInfo 这种是内部类的写法
            final Class<?> listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
            final Field mOnClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");
            final View.OnClickListener mOnClickListener = (View.OnClickListener) mOnClickListenerField.get(getListenerInfo);

            //二、创建 Hook 对象的代理对象
            //方式一：创建一个代理类
            ProxyClickListener proxyClickListener = new ProxyClickListener(mOnClickListener);

            //方式二：由于 View.OnClickListener 是一个接口，这里我们可以直接使用 JDK 的动态代理
            final Object proxyObject = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Log.d("erdai", "invoke: 插入一段自定义逻辑，erdai666：" + method.getName());
                    return method.invoke(mOnClickListener, args);
                }
            });

            //三、使用代理对象替换 Hook 对象
            mOnClickListenerField.set(getListenerInfo,proxyClickListener);
//            mOnClickListenerField.set(getListenerInfo,proxyObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class ProxyClickListener implements View.OnClickListener{

        private View.OnClickListener clickListener;

        public ProxyClickListener(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            Log.d("erdai", "invoke: 插入一段自定义逻辑，erdai777");
            clickListener.onClick(v);
        }
    }

}
