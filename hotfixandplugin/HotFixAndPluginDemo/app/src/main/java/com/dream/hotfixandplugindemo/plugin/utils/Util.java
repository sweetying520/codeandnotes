package com.dream.hotfixandplugindemo.plugin.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/10
 */
public class Util {
    /**
     * 获取当前应用的第一个Activity的name
     *
     * @param context
     * @param pmName
     * @return
     */
    public static String getHostClzName(Context context, String pmName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pmName, PackageManager
                    .GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        ActivityInfo[] activities = packageInfo.activities;
        if (activities == null || activities.length == 0) {
            return "";
        }
        ActivityInfo activityInfo = activities[0];
        return activityInfo.name;

    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPMName(Context context) {
        // 获取当前进程已经注册的 activity
        Context applicationContext = context.getApplicationContext();
        return applicationContext.getPackageName();
    }


    /**
     * 将 assets 下面的文件拷贝到外部存储的 cache 文件夹下面
     * @param context context
     * @param fileName fileName
     * @return String
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String copyAssetsFileToExternalCache(Context context, String fileName){
        InputStream in = null;
        OutputStream out = null;
        try {
            String outputPath = context.getExternalCacheDir() + File.separator + fileName;
            File outputFile = new File(outputPath);
            if(!outputFile.exists())outputFile.createNewFile();

            Log.d("erdai", "copyAssetsFileToExternalCache: " + outputPath);
            in = context.getAssets().open(fileName);
            out = new FileOutputStream(outputFile);
            byte[] bytes = new byte[in.available()];
            int len;
            while (((len = in.read(bytes)) != -1)){
                out.write(bytes,0,len);
            }
            return outputPath;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }


}