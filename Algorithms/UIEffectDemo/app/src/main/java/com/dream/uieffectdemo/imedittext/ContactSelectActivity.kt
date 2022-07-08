package com.dream.uieffectdemo.imedittext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.databinding.ActivitySelectContactBinding

/**
 * function: 联系人选择
 *
 * @author zy
 * @since 2022/7/7
 */
class ContactSelectActivity: AppCompatActivity() {

    companion object{
        const val PARAMS_ID_LIST = "id_list"
        const val PARAMS_NICKNAME_LIST = "nickname_list"
    }

    private val mViewModel by viewModels<ContactSelectViewModel>()
    private val map = mutableMapOf<Int,ContactSelectBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding = DataBindingUtil.setContentView<ActivitySelectContactBinding>(this,R.layout.activity_select_contact)

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

        val contactSelectAdapter = ContactSelectAdapter()
        contactSelectAdapter.onItemCheckListener = { selectData, position, isChecked ->
            if(isChecked){
                map[position] = selectData
            }else{
                map.remove(position)
            }
            Log.d("erdai", "onCreate: $map")

            if(position == 3 && isChecked){
                //回传 map 回去
                val intent = Intent()
                val userAccountList = ArrayList<String>()
                val nickNameList = ArrayList<String>()
                map.forEach {
                    it.value.account?.let { it1 -> userAccountList.add(it1) }
                    it.value.nickName?.let { it1 -> nickNameList.add(it1) }
                }
                intent.putExtra(PARAMS_ID_LIST,userAccountList)
                intent.putExtra(PARAMS_NICKNAME_LIST,nickNameList)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
        mBinding.mAdapter = contactSelectAdapter

    }
}