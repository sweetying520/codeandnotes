package com.dream.realinterviewquestion.sp_and_mmkv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.dream.realinterviewquestion.R;
import com.tencent.mmkv.MMKV;

/**
 * SP and MMKV 源码分析
 */
public class SpAndMMKVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_and_mmkvactivity);



    }

    public String getMyLocalClassName() {
        final String pkg = getPackageName();
        final String cls = getComponentName().getClassName();
        int packageLen = pkg.length();
        if (!cls.startsWith(pkg) || cls.length() <= packageLen
                || cls.charAt(packageLen) != '.') {
            return cls;
        }
        return cls.substring(packageLen+1);
    }

    public void spClick(View view) {
        SharedPreferences sp1 = ((Context) this).getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences sp2 = getPreferences(MODE_PRIVATE);
        SharedPreferences sp3 = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit = sp1.edit();
        edit.putString("data","111");
        edit.apply();
    }

    public void mmkvClick(View view) {
        final MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode("er","erdai666");

        final String er = mmkv.decodeString("er");
        Log.d("erdai", "mmkvClick: " + er);
    }
}