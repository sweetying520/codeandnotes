package com.dream.hotfixandplugindemo;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.dream.hotfixandplugindemo.hotfix.HotFixUtils;
import com.dream.hotfixandplugindemo.plugin.PluginClassHelper;
import com.dream.hotfixandplugindemo.plugin.PluginResourceHelper;
import com.dream.hotfixandplugindemo.plugin.utils.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/7
 */
//在Application中进行替换
public class MyApplication extends Application {

    public static boolean isOpenHotFix = false;

    @Override
    public void onCreate() {
        super.onCreate();
        final String path = Util.copyAssetsFileToExternalCache(this, "config.txt");
        File file = new File(path);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            final String isOpenHotFixString = properties.getProperty("isOpenHotFix");
            isOpenHotFix = Boolean.parseBoolean(isOpenHotFixString);
            Log.d("erdai", "onCreate: " + isOpenHotFix);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(isOpenHotFix){
            //dex作为插件进行加载
            Util.copyAssetsFileToExternalCache(this,"hotfix.dex");
            HotFixUtils.dexPlugin(this);

//            apk作为插件进行加载
//            Util.copyAssetsFileToExternalCache(this,"hotfix.apk");
//            HotFixUtils.apkPlugin(this);
        }else{
            String targetPath = Util.copyAssetsFileToExternalCache(this,"pluginapk.apk");
            PluginClassHelper.loadPluginClass(this,targetPath);
            PluginResourceHelper.addResource(this,targetPath);
        }

    }

    @Override
    public Resources getResources() {
        return PluginResourceHelper.sPluginResources == null ? super.getResources() : PluginResourceHelper.sPluginResources;
    }

    @Override
    public AssetManager getAssets() {
        return PluginResourceHelper.sNewAssetManager == null ? super.getAssets() : PluginResourceHelper.sNewAssetManager;
    }
}

