package com.dream.hotfixandplugindemo.plugin.hook.proxy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.hotfixandplugindemo.TextActivity;

import java.lang.reflect.Method;

/**
 * function: hook Instrumentation 劫持 Activity 启动流程：startActivity
 *
 * @author zy
 * @since 2022/11/10
 */
public class ProxyInstrumentation extends Instrumentation {

    private final Instrumentation instrumentation;

    public ProxyInstrumentation(Instrumentation instrumentation){
        this.instrumentation=instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {


        //使用占坑的 Activity 绕过 AMS 的检查
        Log.d("erdai", "Hook成功，执行了startActivity" + who);
        Intent replaceIntent = new Intent(target, TextActivity.class);
        //将真实要启动的 intent 作为参数放入占坑的 intent 里面，后续在从占坑的 intent 里面将真实的 intent 给取出来
        replaceIntent.putExtra(TextActivity.TARGET_COMPONENT, intent);
        intent = replaceIntent;

        try {
            @SuppressWarnings("JavaReflectionMemberAccess") @SuppressLint("DiscouragedPrivateApi")
            Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity",
                    Context.class,
                    IBinder.class,
                    IBinder.class,
                    Activity.class,
                    Intent.class,
                    int.class,
                    Bundle.class);
            return (ActivityResult) execStartActivity.invoke(instrumentation, who, contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
