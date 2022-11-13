package com.dream.hookskindemo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.dream.hookskindemo.skin.SkinEngine;
import com.dream.hookskindemo.skin.SkinFactory;

import java.io.File;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/13
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected static String mCurrentSkin = null;

    private SkinFactory mSkinFactory;

    private boolean ifAllowChangeSkin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: 关键点1：hook（劫持）系统创建view的过程
        if (ifAllowChangeSkin) {
            mSkinFactory = new SkinFactory();
            mSkinFactory.setDelegate(getDelegate());
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            Log.d("layoutInflaterTag", layoutInflater.toString());
            layoutInflater.setFactory2(mSkinFactory);
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建完成但是还不可以交互
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 等控件创建完成并且可交互之后，再换肤
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("changeTag", null == mCurrentSkin ? "currentSkin是空" : mCurrentSkin);

        if (null != mCurrentSkin)
            changeSkin(this,mCurrentSkin); // 换肤操作必须在setContentView之后
    }

    /**
     * 做一个切换方法
     *
     * @return
     */
    protected String getPath() {
        return "skin.apk";
    }

    protected void changeSkin(Context context,String path) {
        if (ifAllowChangeSkin) {
            File skinFile = new File(context.getExternalCacheDir(), path);
            SkinEngine.getInstance().load(skinFile.getAbsolutePath());
            mSkinFactory.changeSkin();
            mCurrentSkin = path;
        }
    }
}