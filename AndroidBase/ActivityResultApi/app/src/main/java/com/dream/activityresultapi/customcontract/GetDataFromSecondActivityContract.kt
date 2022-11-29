package com.dream.activityresultapi.customcontract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.dream.activityresultapi.SecondActivity

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/29
 */
class GetDataFromSecondActivityContract: ActivityResultContract<Void?,String?>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        val intent = Intent(context,SecondActivity::class.java)
        intent.putExtra("key0","自定义 Contract，你好，我是 MainActivity")
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if(resultCode == Activity.RESULT_OK) {
            return intent?.getStringExtra("key1")
        }
        return null
    }

}