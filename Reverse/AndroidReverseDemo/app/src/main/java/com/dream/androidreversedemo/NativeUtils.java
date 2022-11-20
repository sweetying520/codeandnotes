package com.dream.androidreversedemo;

import androidx.annotation.Keep;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/19
 */
@Keep
public class NativeUtils {

    public static native void methodNative();

    public static void methodNotNative(){
        String logMessage = "this is not native method";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }
}
