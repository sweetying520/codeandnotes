package com.example.startupinit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
public class DrawableManager {

    private static Context mContext;


    public static void init(Context context){
        mContext = context;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable getDrawable(@DrawableRes int resId){
        return mContext.getDrawable(resId);
    }

}
