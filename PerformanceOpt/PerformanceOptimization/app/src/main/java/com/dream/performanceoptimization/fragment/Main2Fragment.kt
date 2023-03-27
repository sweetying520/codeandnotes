package com.dream.performanceoptimization.fragment

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dream.performanceoptimization.R

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/24
 */
class Main2Fragment: Fragment() {

    var enterTime = 0L

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d("erdai", "MainFragment2 setUserVisibleHint: $isVisibleToUser")
        if(isVisibleToUser){
            enterTime = SystemClock.elapsedRealtime()
            Log.d("erdai", "setUserVisibleHint: MainFragment2 采集数据 $enterTime")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("erdai", "Main2Fragment onAttach: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("erdai", "Main2Fragment onCreateView: ")
        return inflater.inflate(R.layout.fragment_main2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("erdai", "Main2Fragment onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("erdai", "Main2Fragment onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("erdai", "Main2Fragment onResume: ")
    }

    override fun onPause() {
        super.onPause()
        if(enterTime == 0L)return
        Log.d("erdai", "Main2Fragment onPause: 采集数据并上报")
    }
    
}