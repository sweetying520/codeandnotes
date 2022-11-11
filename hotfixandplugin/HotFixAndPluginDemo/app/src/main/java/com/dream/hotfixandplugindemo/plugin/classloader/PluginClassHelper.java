package com.dream.hotfixandplugindemo.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/10
 */
@SuppressWarnings({"JavaReflectionMemberAccess", "ConstantConditions"})
public class PluginClassHelper {

    @SuppressLint("DiscouragedPrivateApi")
    public static void loadPluginClass(Context context,String path) {
        //获取插件 apk，通常都是从网络上下载，这里为了演示，直接将插件 apk 放到了手机的外部存储中
        //获取外部存储下的 pluginapk.apk 文件

        File pluginApkFile = new File(path);
        if(!pluginApkFile.exists())return;


        //构建插件 ClassLoader
        DexClassLoader pluginClassLoader = new DexClassLoader(
                pluginApkFile.getAbsolutePath(),
                context.getCacheDir().getAbsolutePath(),
                null,
                context.getClassLoader()
        );

        //测试是否能加载到插件 apk 中的类：success
        try {
            Class<?> clazz = pluginClassLoader.loadClass("com.dream.pluginapk.PluginUtils");
            Object o = clazz.newInstance();
            Method addMethod = clazz.getDeclaredMethod("add", int.class, int.class);
            int result = (int) addMethod.invoke(o, 4, 6);
            Log.d("erdai", "loadPluginClass: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            //1、获取 BaseDexClassLoader 中的 pathList 属性并设置私有属性可访问
            Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            pathListField.setAccessible(true);


            //2、获取当前 ClassLoader 的 pathList 的 dexElements 对象
            final Object currentPathList = pathListField.get(context.getClassLoader());
            final Field currentDexElementsField = currentPathList.getClass().getDeclaredField("dexElements");
            currentDexElementsField.setAccessible(true);
            final Object[] currentDexElements = (Object[]) currentDexElementsField.get(currentPathList);


            //3、获取插件 ClassLoader 的 pathList 的 dexElements 对象
            final Object pluginPathList = pathListField.get(pluginClassLoader);
            final Field pluginDexElementsField = pluginPathList.getClass().getDeclaredField("dexElements");
            pluginDexElementsField.setAccessible(true);
            final Object[] pluginDexElements = (Object[]) pluginDexElementsField.get(pluginPathList);

            //4、创建一个数组，合并插件和应用内的类
            Object[] combineArray = (Object[]) Array.newInstance(pluginDexElements.getClass().getComponentType(),
                    pluginDexElements.length + currentDexElements.length);
            System.arraycopy(pluginDexElements,0,combineArray,0,pluginDexElements.length);
            System.arraycopy(currentDexElements,0,combineArray,pluginDexElements.length,currentDexElements.length);


            //5、将合并的 dexElements 对象赋值给当前 dexElements 属性
            currentDexElementsField.set(currentPathList,combineArray);
            Log.d("erdai","插件类加载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}