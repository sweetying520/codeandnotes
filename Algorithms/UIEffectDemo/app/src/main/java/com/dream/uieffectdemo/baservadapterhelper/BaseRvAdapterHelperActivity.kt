package com.dream.uieffectdemo.baservadapterhelper

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.ToastUtils
import com.dream.uieffectdemo.MainActivity
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.databinding.ActivityHelperAdapterRvBaseBinding

/**
 * function: BaseRvAdapterHelperActivity
 *
 * @author zy
 * @since 2022/7/20
 */
class BaseRvAdapterHelperActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityHelperAdapterRvBaseBinding
    private val mViewModel by viewModels<BaseRvAdapterHelperViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_helper_adapter_rv_base)
        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel


        initClickListener()
    }


    private fun initClickListener() {
        mViewModel.click1 = View.OnClickListener{
            MainActivity.startActivityInner<PullUpLoadingActivity>(this)
        }
    }


}