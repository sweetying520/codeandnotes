package com.dream.coroutinedemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.dream.coroutinedemo.databinding.ActivityMainBinding
import com.dream.coroutinedemo.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        //测试
        binding.testClick = View.OnClickListener {
            mainViewModel.test()
        }

        //测试2：flow
        binding.testFlow = View.OnClickListener {
            lifecycleScope.launch {
                mainViewModel.timeFlow.collectLatest{ time ->
                    binding.tvTestFlow.text = time.toString()
                    delay(3000)
                }
            }
        }

//        val friendJsonResponse = FriendJsonResponse(name = "erdai666", link = "https://www.baidu.com/")
//        //val toJson = Gson().newBuilder().serializeNulls().toJson(friendJsonResponse)
//        val toJson = GsonUtils.toJson(GsonBuilder().addSerializationExclusionStrategy(object : ExclusionStrategy{
//            override fun shouldSkipField(f: FieldAttributes?): Boolean {
//                val annotation = f?.getAnnotation(Expose::class.java)
//                if(annotation != null && !annotation.serialize){
//                    return true
//                }
//                return false
//            }
//
//            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
//                return false
//            }
//
//        }).create(),friendJsonResponse)
//        Log.d("erdai", "onCreate: $toJson")
    }

    /**
     * button
     */
    fun btnClick(view: View) {
        mainViewModel.getFriendJson()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("erdai", "onDestroy: ")
    }
}