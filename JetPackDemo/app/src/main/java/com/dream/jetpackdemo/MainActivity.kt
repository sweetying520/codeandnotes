package com.dream.jetpackdemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.dream.jetpackdemo.databinding.DataBindingActivity
import com.dream.jetpackdemo.lifecycle.LifeCycleActivity
import com.dream.jetpackdemo.lifecycle.MyLifecycleOwnerImplActivity
import com.dream.jetpackdemo.livedata.LiveDataActivity
import com.dream.jetpackdemo.room.RoomActivity
import com.dream.jetpackdemo.viewmodel.MainViewModel
import com.dream.jetpackdemo.viewmodel.ViewModelActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun lifecycle(view: View) {
        startActivityInner<LifeCycleActivity>(this)
    }

    fun livedata(view: View) {
        startActivityInner<LiveDataActivity>(this)
    }

    fun viewmodel(view: View) {
        startActivityInner<ViewModelActivity>(this)
    }

    fun dataBinding(view: View) {
        startActivityInner<DataBindingActivity>(this)
    }

    fun room(view: View) {
        startActivityInner<RoomActivity>(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("erdai", "onDestroy: " + isChangingConfigurations)
    }

    companion object{
        inline fun <reified T> startActivityInner(context: Context){
            val intent = Intent(context,T::class.java)
            context.startActivity(intent)
        }
    }


}