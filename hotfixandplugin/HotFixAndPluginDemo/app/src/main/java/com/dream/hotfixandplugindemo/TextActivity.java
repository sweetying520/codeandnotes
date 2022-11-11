package com.dream.hotfixandplugindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class TextActivity extends AppCompatActivity {

    public static final String TARGET_COMPONENT = "TARGET_COMPONENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

    }
}