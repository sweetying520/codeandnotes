package com.dream.pluginapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class PluginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        Log.d("erdai", "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("erdai", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("erdai", "onResume: ");
    }

    @Override
    public Resources getResources() {
        return getApplication() != null && getApplication().getResources() != null ? getApplication().getResources() : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return getApplication() != null && getApplication().getAssets() != null ? getApplication().getAssets() : super.getAssets();

    }

    public void btnClick(View view) {
        Log.d("erdai", "btnClick: erdai666");
        Toast.makeText(this, "erdai666", Toast.LENGTH_SHORT).show();
    }
}