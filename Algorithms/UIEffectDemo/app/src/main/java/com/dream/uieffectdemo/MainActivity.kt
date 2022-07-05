package com.dream.uieffectdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dream.uieffectdemo.databinding.ActivityMainBinding
import com.dream.uieffectdemo.popupwindow.PopupWindowActivity

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取 dataBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //获取 ViewModel
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mBinding?.lifecycleOwner = this
        mBinding?.viewModel = mViewModel


        initClickListener()
    }

    private fun initClickListener() {
        //popWindow 点击
        mBinding?.popupWindowClick = View.OnClickListener{
            startActivityInner<PopupWindowActivity>(this)
        }
    }




    companion object{
        inline fun <reified T> startActivityInner(context: Context){
            val intent = Intent(context,T::class.java)
            context.startActivity(intent)
        }
    }

}