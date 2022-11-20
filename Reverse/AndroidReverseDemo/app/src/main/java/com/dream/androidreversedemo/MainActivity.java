package com.dream.androidreversedemo;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.dream.androidutils.StringUtils;

public class MainActivity extends AppCompatActivity {

    String toastTips = "toast in MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.flFragmentContainer,new MyFragment()).commit();
        //1、Utils 下的方法调用
        Utils utils = new Utils();
        utils.methodNormal();
        //2、NativeUtils 下的方法调用
        try {
            NativeUtils.methodNative();
            NativeUtils.methodNotNative();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //3、第三方库下工具类的方法调用
        int result = StringUtils.getLength("erdai666");
        System.out.println(result);
        //4、MainActivity 下的 methodWithGlobalVariable 方法调用
        methodWithGlobalVariable();
        //5、MainActivity 下的 methodWithLocalVariable 方法调用
        methodWithLocalVariable();
    }

    private void methodWithGlobalVariable() {
        Toast.makeText(this, toastTips, Toast.LENGTH_SHORT).show();
    }

    private void methodWithLocalVariable() {
        String logMessage = "log in MainActivity";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }
}