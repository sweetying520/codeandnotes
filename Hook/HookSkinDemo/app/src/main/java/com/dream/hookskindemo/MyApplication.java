package com.dream.hookskindemo;

import android.app.Application;

import com.dream.hookskindemo.skin.SkinEngine;
import com.dream.hookskindemo.utils.FileUtils;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/13
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileUtils.copyAssetsFileToExternalCache(this,"skin.apk");


        SkinEngine.getInstance().init(this);
    }
}
