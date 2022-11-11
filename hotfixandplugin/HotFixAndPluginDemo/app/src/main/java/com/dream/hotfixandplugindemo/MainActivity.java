package com.dream.hotfixandplugindemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.dream.hotfixandplugindemo.plugin.hook.GlobalActivityHookHelper;
import com.dream.hotfixandplugindemo.plugin.hook.ProxyUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从文件中获取配置决定是热修复还是插件化

        if (!MyApplication.isOpenHotFix) {
            ProxyUtils.hook(this);
        }


    }

    public void btnClick(View view) {
        if(!MyApplication.isOpenHotFix){
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.dream.pluginapk", "com.dream.pluginapk.PluginActivity"));
            startActivity(intent);
        }else{
            int result = 1/0;
        }


        //startActivity(new Intent(this,TextActivity.class));
//

//        Toast.makeText(this, "ok 我现在换成了 apk 插件加载", Toast.LENGTH_SHORT).show();
//        Log.d("erdai", "btnClick: 哦耶 apk 插件加载");

//        Toast.makeText(this, "ok bug 修复了，我现在换成了 dex 插件加载", Toast.LENGTH_SHORT).show();
//        Log.d("erdai", "btnClick: 哦耶 dex 插件加载");



//        /**
//         * 1、内部储存
//         * Environment.getDataDirectory().getAbsolutePath()：/data
//         * getFilesDir().getAbsolutePath()：/data/user/0/com.dream.hotfixandplugindemo/files
//         * getCacheDir().getAbsolutePath()：/data/user/0/com.dream.hotfixandplugindemo/cache
//         * getCodeCacheDir().getAbsolutePath()：/data/user/0/com.dream.hotfixandplugindemo/code_cache
//         * getDataDir().getAbsolutePath()：/data/user/0/com.dream.hotfixandplugindemo （require api 24）
//         * getDir("custom_dir",MODE_PRIVATE).getAbsolutePath()：/data/user/0/com.dream.hotfixandplugindemo/app_custom_dir
//         */
//        Log.d("erdai", "内部存储 getDataDirectory : " + Environment.getDataDirectory().getAbsolutePath() + " " + Environment.getDataDirectory().getPath());
//        Log.d("erdai", "内部存储 getFilesDir : " + getFilesDir().getAbsolutePath() + " " + getFilesDir().getPath());
//        Log.d("erdai", "内部存储 getCacheDir : " + getCacheDir().getAbsolutePath() + " " + getCacheDir().getPath());
//        Log.d("erdai", "内部存储 getCodeCacheDir : " + getCodeCacheDir().getAbsolutePath() + " " + getCodeCacheDir().getPath());
//        Log.d("erdai", "内部存储 getDir : " + getDir("custom_dir",MODE_PRIVATE).getAbsolutePath() + " " + getDir("custom_dir",MODE_PRIVATE).getPath());
//
//        /**
//         * Android 11 无法在进行 SDCard 的写权限，从 Android 11 开始，应用无法在外部存储设备上创建自己的应用专用目录
//         * 如果在 Android 10 上想继续使用老版本的存储需：requestLegacyExternalStorage 设置为 true
//         * 2、外部存储
//         * Environment.getExternalStorageDirectory：/storage/emulated/0
//         * Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)：/storage/emulated/0/Documents
//         * getExternalFilesDir：/storage/emulated/0/Android/data/com.dream.hotfixandplugindemo/files
//         * getExternalCacheDir：/storage/emulated/0/Android/data/com.dream.hotfixandplugindemo/cache
//         */
//        String externalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Log.d("erdai", "Environment.getExternalStorageDirectory: " + externalStorageDir);
//        String externalStorageDir2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
//        Log.d("erdai", "Environment.getExternalStoragePublicDirectory: " + externalStorageDir2);
//        String absolutePath = getExternalFilesDir("").getAbsolutePath();
//        Log.d("erdai", "getExternalFilesDir: " + absolutePath);
//        String absolutePath2 = getExternalCacheDir().getAbsolutePath();
//        Log.d("erdai", "getExternalCacheDir: " + absolutePath2);
//
//        File file = new File(externalStorageDir + "/test.txt");
//        if(!file.exists()) {
//            try {
//                boolean newFile = file.createNewFile();
//                Log.d("erdai", "is create succ: " + newFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}