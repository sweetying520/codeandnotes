package com.dream.hotfixandplugindemo.plugin.hook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.dream.hotfixandplugindemo.plugin.hook.proxy.ProxyHandlerCallback;
import com.dream.hotfixandplugindemo.plugin.utils.Util;
import com.dream.hotfixandplugindemo.plugin.hook.proxy.ProxyInstrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * function: 针对高版本 API 28 及以上版本
 * 1、主要 Hook Instrumentation
 * 2、主要使用 Activity 占坑的方式
 * 3、由于A ppCompatActivity 存在 PMS 检测，如果这里不 hook 的话，就会报 PackageNameNotFoundException
 *
 * @author zy
 * @since 2022/11/10
 */
@SuppressWarnings("JavaReflectionMemberAccess")
public class ProxyUtils {

    public static void hook(Activity activity){
       doHandlerHook();
       doInstrumentationHook(activity);
       hookPM(activity);
    }

    @SuppressLint("DiscouragedPrivateApi")
    private static void doInstrumentationHook(Activity activity) {
        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField;
        try {
            mInstrumentationField = Activity.class.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);

            // 创建代理对象
            Instrumentation originalInstrumentation = (Instrumentation) mInstrumentationField.get(activity);
            mInstrumentationField.set(activity, new ProxyInstrumentation(originalInstrumentation));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void doHandlerHook() {
        try {
            //确定hook点，ActivityThread类的mh
            // 先拿到ActivityThread
            Class<?> ActivityThreadClz = Class.forName("android.app.ActivityThread");
            Field field = ActivityThreadClz.getDeclaredField("sCurrentActivityThread");
            field.setAccessible(true);
            Object ActivityThreadObj = field.get(null);//OK，拿到主线程实例

            //现在拿mH
            Field mHField = ActivityThreadClz.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler mHObj = (Handler) mHField.get(ActivityThreadObj);//ok，当前的mH拿到了
            //再拿它的mCallback成员
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);

            //2.现在，造一个代理mH，
            // 他就是一个简单的Handler子类
            ProxyHandlerCallback proxyMHCallback = new ProxyHandlerCallback();

            //3.替换
            //将Handler的mCallback成员，替换成创建出来的代理HandlerCallback
            mCallbackField.set(mHObj, proxyMHCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 由于我只在SDK 28 对应的9.0设备上做过成功的试验，所以此方法命名为hookPMAfter28
     *
     * @param context
     */
    private static void hookPM(Context context) {
        try {
            String pmName = Util.getPMName(context);
            String hostClzName = Util.getHostClzName(context, pmName);

            Class<?> forName = Class.forName("android.app.ActivityThread");//PM居然是来自ActivityThread
            Field field = forName.getDeclaredField("sCurrentActivityThread");
            field.setAccessible(true);
            Object activityThread = field.get(null);
            Method getPackageManager = activityThread.getClass().getDeclaredMethod("getPackageManager");
            Object iPackageManager = getPackageManager.invoke(activityThread);

            String packageName = Util.getPMName(context);
            PMSInvocationHandler handler = new PMSInvocationHandler(iPackageManager, packageName, hostClzName);
            Class<?> iPackageManagerIntercept = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new
                    Class<?>[]{iPackageManagerIntercept}, handler);
            // 获取 sPackageManager 属性
            Field iPackageManagerField = activityThread.getClass().getDeclaredField("sPackageManager");
            iPackageManagerField.setAccessible(true);
            iPackageManagerField.set(activityThread, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class PMSInvocationHandler implements InvocationHandler {

        private Object base;
        private String packageName;
        private String hostClzName;

        public PMSInvocationHandler(Object base, String packageName, String hostClzName) {
            this.packageName = packageName;
            this.base = base;
            this.hostClzName = hostClzName;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("getActivityInfo")) {
                ComponentName componentName = new ComponentName(packageName, hostClzName);
                return method.invoke(base, componentName, PackageManager.GET_META_DATA, 0);//破费，一定是这样
            }

            return method.invoke(base, args);
        }
    }
}
