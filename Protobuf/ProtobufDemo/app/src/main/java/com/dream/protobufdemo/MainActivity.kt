package com.dream.protobufdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hello.PersonOuterClass

class MainActivity : AppCompatActivity() {

    private val tv: TextView by lazy { findViewById(R.id.tv) }
   //protoc -I=./app/src/main/proto --java_out=./app/src/main/java person.proto
   //protoc --java_out=./app/src/main/java ./app/src/main/proto/person.proto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val person = PersonOuterClass.Person.newBuilder().setName("erdai")
            .setAge(18)
            .setEmail("123456@qq.com")
            .build()

            Log.d("erdai", "onCreate: ${person.toByteArray().asList()}")

        tv.setOnClickListener {
            val dataTemp = PersonOuterClass.Person.parseFrom(person.toByteArray())
            Log.e("erdai", "onCreate: \n$dataTemp")
        }
    }
}