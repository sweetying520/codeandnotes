package com.dream.hotfixandplugindemo.plugin.hook.proxy;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.dream.hotfixandplugindemo.TextActivity;

import java.lang.reflect.Field;
import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/10
 */
@SuppressWarnings("ConstantConditions")
public class ProxyHandlerCallback implements Handler.Callback {
    private int EXECUTE_TRANSACTION = 159;//这个值，是android.app.ActivityThread的内部类H 中定义的常量EXECUTE_TRANSACTION


    @Override
    public boolean handleMessage(Message msg) {
        boolean result = false;
        if (msg.what == EXECUTE_TRANSACTION) {//这是跳转的时候,要对intent进行还原
            try {
                //先把相关@hide的类都建好
                Class<?> clientTransactionClz = Class.forName("android.app.servertransaction.ClientTransaction");
                Class<?> launchActivityItemClz = Class.forName("android.app.servertransaction.LaunchActivityItem");

                Field mActivityCallbacksField = clientTransactionClz.getDeclaredField("mActivityCallbacks");//ClientTransaction的成员
                mActivityCallbacksField.setAccessible(true);
                //类型判定，好习惯
                if (!clientTransactionClz.isInstance(msg.obj)) return true;
                Object mActivityCallbacksObj = mActivityCallbacksField.get(msg.obj);//根据源码，在这个分支里面,msg.obj就是 ClientTransaction类型,所以，直接用
                //拿到了ClientTransaction的List<ClientTransactionItem> mActivityCallbacks;
                List list = (List) mActivityCallbacksObj;

                if (list.size() == 0) return result;
                Object LaunchActivityItemObj = list.get(0);//所以这里直接就拿到第一个就好了

                if (!launchActivityItemClz.isInstance(LaunchActivityItemObj)) return result;
                //这里必须判定 LaunchActivityItemClz，
                // 因为 最初的ActivityResultItem传进去之后都被转化成了这LaunchActivityItemClz的实例
                Field mIntentField = launchActivityItemClz.getDeclaredField("mIntent");
                mIntentField.setAccessible(true);
                Intent mIntent = (Intent) mIntentField.get(LaunchActivityItemObj);
                final Intent targetIntent = mIntent.getParcelableExtra(TextActivity.TARGET_COMPONENT);
                if(targetIntent == null)return result;
                mIntent.setComponent(targetIntent.getComponent());
                Log.d("erdai", "接受到消息了msg：" + targetIntent);
                return  result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
