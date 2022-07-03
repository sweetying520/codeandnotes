package com.dream.jetpackdemo.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dream.jetpackdemo.R

class ViewModelActivity : AppCompatActivity() {

    private lateinit var tvCount: TextView
    private lateinit var btnAdd: Button



//    //方式1
//    private val viewModel1 by viewModels<MainViewModel>()
//
//    //方式2
//    private val viewModel2 by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }
//
    //使用了 SavedState
    private val viewModel2 by lazy {
        ViewModelProvider(
            this,
            SavedStateViewModelFactory(application, this)
        ).get(MainViewModel::class.java)
    }


    //不使用 ViewModel 的情况
//    private val viewModel2 = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        tvCount = findViewById(R.id.tv_count)
        btnAdd = findViewById(R.id.btn_add)

        tvCount.text = viewModel2.number.toString()
        btnAdd.setOnClickListener{
            ++viewModel2.number
            tvCount.text = viewModel2.number.toString()
        }


        //test
        val myAndroidViewModel = ViewModelProvider(this).get(MyAndroidViewModel::class.java)
        myAndroidViewModel.test()
    }


    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d("erdai", "onRetainCustomNonConfigurationInstance: ")
        return super.onRetainCustomNonConfigurationInstance()
    }

    override fun getLastNonConfigurationInstance(): Any? {
        Log.d("erdai", "getLastNonConfigurationInstance: ")
        return super.getLastNonConfigurationInstance()
    }
}