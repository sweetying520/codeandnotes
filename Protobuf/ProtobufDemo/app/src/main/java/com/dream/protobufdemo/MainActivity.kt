package com.dream.protobufdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

   //protoc -I=./app/src/main/proto --java_out=./app/src/main/java person.proto
   //protoc --java_out=./app/src/main/java ./app/src/main/proto/person.proto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}