package com.dream.hotfixandplugindemo.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/10
 */
@SuppressWarnings({"ConstantConditions", "JavaReflectionMemberAccess"})
public class PluginResourceHelper {
    public static Resources sPluginResources;
    public static AssetManager sNewAssetManager ;

    public static void addResource(Context context, String path) {
        //利用反射创建一个新的AssetManager

        try {
            sNewAssetManager = AssetManager.class.getConstructor().newInstance();
            //利用反射获取addAssetPath方法
            @SuppressLint("DiscouragedPrivateApi")
            Method mAddAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            mAddAssetPath.setAccessible(true);
            //利用反射调用addAssetPath方法加载外部的资源（SD卡）
            if (((Integer) mAddAssetPath.invoke(sNewAssetManager, path)) == 0) {
                throw new IllegalStateException("Could not create new AssetManager");
            }

            sPluginResources = new Resources(sNewAssetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());

            Log.d("erdai","插件资源加载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}