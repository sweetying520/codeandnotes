package com.dream.hotfixandplugindemo;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/7
 */
public class Test {

    public static void main(String[] args) {
        File file = new File("/Users/zhouying/codeandnotes/hotfixandplugin/HotFixAndPluginDemo/app/src/main/java/com/dream/hotfixandplugindemo/MainActivity.java");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while (((len = fileInputStream.read(bytes)) != -1)){
                System.out.println(new String(bytes,0,len));
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
