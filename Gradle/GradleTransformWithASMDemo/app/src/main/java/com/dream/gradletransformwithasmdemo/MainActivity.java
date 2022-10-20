package com.dream.gradletransformwithasmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dream.customannotation.CostTime;

public class MainActivity extends AppCompatActivity {

    @CostTime
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}