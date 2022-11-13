package com.dream.hookskindemo.utils;

import android.content.Context;
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
 * @since 2022/11/13
 */
public class FileUtils {

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
