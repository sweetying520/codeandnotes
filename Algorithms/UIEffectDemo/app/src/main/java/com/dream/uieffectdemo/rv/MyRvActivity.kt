package com.dream.uieffectdemo.rv

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.databinding.ActivityRvMyBinding

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/29
 */
class MyRvActivity: AppCompatActivity(){

    private lateinit var mBinding: ActivityRvMyBinding
    private var mCount = 5000000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRvMyBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

    }

    inner class MyCustomAdapter: MyCustomRecyclerView.Adapter{
        override fun onCreateViewHolder(
            position: Int,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(position: Int, convertView: View?, parent: ViewGroup?) {
            TODO("Not yet implemented")
        }

        override fun getItemViewType(row: Int): Int {
            return 1
        }

        override fun getCount(): Int {
            return mCount
        }

        override fun getHeight(index: Int): Int {
            TODO("Not yet implemented")
        }

    }
}