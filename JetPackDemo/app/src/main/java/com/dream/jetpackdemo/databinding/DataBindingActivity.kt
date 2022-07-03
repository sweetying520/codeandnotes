package com.dream.jetpackdemo.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dream.jetpackdemo.MainActivity
import com.dream.jetpackdemo.R
import com.dream.jetpackdemo.databinding.viewmodel.DataBindingViewModel

class DataBindingActivity : AppCompatActivity() {

    private val dataBindingViewModel by viewModels<DataBindingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)

        val dataBinding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this,R.layout.activity_data_binding)


        //1、使用 Observable 的扩展属性
//        val cat = Cat()
//        cat.name.set("咖啡猫")
//        cat.isShowName.set(true)
//        dataBinding.cat = cat

        //2、使用 ViewModel + Observable 的扩展属性，其实就是把 Cat 换成 ViewModel 这里就不演示了
        //3、ViewModel + LiveData
        dataBinding.viewModel = dataBindingViewModel
        //必须绑定生命周期，否则无效果，为什么？
        dataBinding.lifecycleOwner = this

        //直接传过来 click 事件
        dataBinding.click = View.OnClickListener {
            Toast.makeText(this,"MyToast",Toast.LENGTH_SHORT).show()
        }

        dataBindingViewModel.observableFieldClick.set{
            Log.d("erdai", "onCreate: observableFieldClick")
            dataBindingViewModel.imageUrlLiveData.value = "https://gitee.com/sweetying/picgo/raw/master/img/202112151757220.png"
        }


        dataBinding.btnOperate.setOnClickListener {
            //1、
//            cat.name.set("ObservableField 改变的咖啡猫")
//            cat.isShowName.set(true)

            //3、
            dataBindingViewModel.nameLiveData.value = "erdai666"
        }

        dataBinding.btnOperate2.setOnClickListener {
            //1、
//            cat.isShowName.set(false)
            MainActivity.startActivityInner<ErdaiActivity>(this)
        }
    }
}