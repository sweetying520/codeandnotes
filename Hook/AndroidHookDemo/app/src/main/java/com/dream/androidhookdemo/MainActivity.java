package com.dream.androidhookdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.tv);


        try {

            final Class<?> aClass = Class.forName("android.app.ActivityThread");
            final Object o = aClass.newInstance();
            final Field default_full_backup_agent = aClass.getDeclaredField("DEFAULT_FULL_BACKUP_AGENT");
            final Field tagField = aClass.getDeclaredField("TAG");
            Log.d("erdai", "tagField onCreate: " + tagField.get(o));
            Log.d("erdai", "backup_agent onCreate: " + default_full_backup_agent.get(o));


            Log.d("erdai", "tagField onCreate: " + tagField.get(o));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "erdai666", Toast.LENGTH_SHORT).show();
            }
        });
        HookSetOnClickListenerHelper.hook(this,tv);
    }
}