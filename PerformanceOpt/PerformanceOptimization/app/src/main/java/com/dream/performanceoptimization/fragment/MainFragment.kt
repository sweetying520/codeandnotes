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
class MainFragment: Fragment() {

    var enterTime = 0L

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d("erdai", "MainFragment setUserVisibleHint: $isVisibleToUser")
        if(isVisibleToUser){
            enterTime = SystemClock.elapsedRealtime()
            Log.d("erdai", "setUserVisibleHint: MainFragment 采集数据 $enterTime")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("erdai", "MainFragment onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("erdai", "MainFragment onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("erdai", "MainFragment onCreateView: ")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("erdai", "MainFragment onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("erdai", "MainFragment onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("erdai", "MainFragment onResume: ")
    }

    override fun onPause() {
        super.onPause()
        if(enterTime == 0L)return
        Log.d("erdai", "MainFragment onPause: 采集数据并上报")
    }
}