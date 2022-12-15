package com.dream.uieffectdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.JsonUtils
import com.dream.uieffectdemo.baservadapterhelper.BaseRvAdapterHelperActivity
import com.dream.uieffectdemo.databinding.ActivityMainBinding
import com.dream.uieffectdemo.imedittext.IMEditTextActivity
import com.dream.uieffectdemo.popupwindow.PopupWindowActivity
import com.dream.uieffectdemo.text.TextActivity
import com.dream.uieffectdemo.utils.JsonFileReader


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
        val json = JsonFileReader.getJson(this, "myjson.json")
        Log.d("erdai", "onCreate: $json")
        val text = JsonUtils.getString(json,"text")
        val formatStringByTemplate = getFormatStringByTemplate(text, json)
        Log.d("erdai", "onCreate: $formatStringByTemplate")


        val str = "*****\${queueNum}***".replace("replace","12")
        Log.d("erdai", "onCreate: $str")
        Log.d("erdai", "onCreate: ${"str".plus("哈哈哈").plus("*")}")
        initClickListener()
    }

    @Suppress("UNCHECKED_CAST")
    fun getFormatStringByTemplate(sourceString: String, template: String): String{
        try {
            val templateMap: Map<String,Any?> = GsonUtils.fromJson(template,Map::class.java) as Map<String, Any?>
            var formatString = sourceString
            for((key,value) in templateMap){
                val replace = "\${key}".replace("key", key)
                formatString = formatString.replace(replace,value?.toString()?:"")
            }
            return formatString
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    private fun initClickListener() {
        //popWindow 点击
        mBinding?.popupWindowClick = View.OnClickListener{
            startActivityInner<PopupWindowActivity>(this)
        }

        //smartEditText 点击
        mBinding?.smartEditTextClick = View.OnClickListener {
            startActivityInner<IMEditTextActivity>(this)
        }

        mViewModel?.baseQuickAdapterClick = View.OnClickListener {
            startActivityInner<BaseRvAdapterHelperActivity>(this)
        }

        mBinding?.textClick = View.OnClickListener {
            startActivityInner<TextActivity>(this)
        }
    }



    companion object{
        inline fun <reified T> startActivityInner(context: Context){
            val intent = Intent(context,T::class.java)
            context.startActivity(intent)
        }
    }

}