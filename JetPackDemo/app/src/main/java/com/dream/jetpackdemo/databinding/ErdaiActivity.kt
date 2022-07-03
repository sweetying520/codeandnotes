package com.dream.jetpackdemo.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dream.jetpackdemo.R
import com.dream.jetpackdemo.databinding.model.User

class ErdaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val erdaiBinding = DataBindingUtil.setContentView<ActivityErdaiBinding>(this, R.layout.activity_erdai)
        val user = User()
        erdaiBinding.user = user

        user.name = "erdai"
        user.pwd = "erdai666"
    }
}