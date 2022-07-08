package com.dream.uieffectdemo.imedittext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.databinding.ActivityTextEditImBinding

/**
 * function: 输入框输入
 *
 * @author zy
 * @since 2022/7/5
 */
class IMEditTextActivity: AppCompatActivity() {

    private var mBinding: ActivityTextEditImBinding? = null

    private val getBackValue = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
             val userAccountList = it.data?.getStringArrayListExtra(ContactSelectActivity.PARAMS_ID_LIST)
             val nickNameList = it.data?.getStringArrayListExtra(ContactSelectActivity.PARAMS_NICKNAME_LIST)

            updateAtEditText(userAccountList, nickNameList)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_text_edit_im)
        mBinding?.et?.setOnMentionInputListener { tag ->
            if (SmartMentionEditText.TIM_MENTION_TAG == tag || SmartMentionEditText.TIM_MENTION_TAG_FULL == tag){
                getBackValue.launch(Intent(this,ContactSelectActivity::class.java))
            }
        }
    }


    /**
     * 更新 @ 联系人到输入框
     */
    private fun updateAtEditText(
        userAccountList: ArrayList<String>?,
        nickNameList: ArrayList<String>?
    ) {
        val atDisplayString = getAtDisplayString(nickNameList)
        val atDisplayMap = getAtDisplayMap(userAccountList, nickNameList)
        mBinding?.et?.setMentionMap(atDisplayMap)
        val selectionEnd = mBinding?.et?.selectionEnd ?: 0
        if (selectionEnd > 0) {
            mBinding?.et?.editableText?.insert(selectionEnd, atDisplayString)
            mBinding?.et?.setSelection(selectionEnd + atDisplayString.length)
        }
    }

    /**
     * 获取 联系人=账号 的map：@erdai1 =12341
     */
    private fun getAtDisplayMap(
        userAccountList: java.util.ArrayList<String>?,
        nickNameList: java.util.ArrayList<String>?
    ): MutableMap<String,String>? {
        if(userAccountList.isNullOrEmpty() || nickNameList.isNullOrEmpty())return null
        //1、获取 @
        var mentionTag = SmartMentionEditText.TIM_MENTION_TAG
        val editable = mBinding?.et?.text
        val selectionEnd = mBinding?.et?.selectionEnd?:0
        val text = editable.toString()
        if (editable != null && selectionEnd > 0) {
            if (text.isNotEmpty()) {
                mentionTag = text.substring(selectionEnd - 1, selectionEnd)
            }
        }

        //2、构建 map
        val displayMap = mutableMapOf<String,String>()
        nickNameList.forEachIndexed { index, str ->
            if(index == 0){
                displayMap["$mentionTag$str "] = userAccountList[index]
                return@forEachIndexed
            }
            displayMap["${SmartMentionEditText.TIM_MENTION_TAG}$str "] = userAccountList[index]
        }
        return displayMap
    }

    /**
     * 获取需要展示的 String
     */
    private fun getAtDisplayString(nickNameList: ArrayList<String>?): String{
        if(nickNameList.isNullOrEmpty())return ""
        var atDisplayString = ""
        nickNameList.forEach { nickName->
            atDisplayString += nickName
            atDisplayString += " "
            atDisplayString += SmartMentionEditText.TIM_MENTION_TAG
        }
        if(atDisplayString.isNotEmpty()){
            atDisplayString = atDisplayString.substring(0,atDisplayString.length - 1)
        }
        return atDisplayString
    }

}