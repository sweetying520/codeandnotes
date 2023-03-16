package com.example.providerfreeinit;

import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
public class TestManager {

    private static Context mContext;


    public static void init(Context context){
        mContext = context;
    }


    public static String getStr(@StringRes int resId){
        return mContext.getString(resId);
    }

}
