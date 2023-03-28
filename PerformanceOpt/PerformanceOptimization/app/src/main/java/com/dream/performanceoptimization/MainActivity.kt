package com.dream.performanceoptimization

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dream.performanceoptimization.fragment.Main2Fragment
import com.dream.performanceoptimization.fragment.Main3Fragment
import com.dream.performanceoptimization.fragment.MainFragment
import com.dream.performanceoptimization.fragment.MainFragmentPagerAdapter
import com.google.android.material.tabs.TabLayout

@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {

//    private var onCreateStartTime: Long = 0
//    private var onCreateEndTime: Long = 0
//
//    private var onInflateStartTime: Long = 0
//    private var onInflateEndTime: Long = 0

    private val tabLayout by lazy {
        findViewById<TabLayout>(R.id.tabLayout)
    }

    private val viewPage by lazy {
        findViewById<ViewPager>(R.id.viewPager)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        //onCreateStartTime = SystemClock.elapsedRealtime()

        super.onCreate(savedInstanceState)

//        onInflateStartTime = SystemClock.elapsedRealtime()
        setContentView(R.layout.activity_main)
//        onInflateEndTime = SystemClock.elapsedRealtime()
//        Log.d("erdai", "onCreate inflate: ${onInflateEndTime - onInflateStartTime}")

//        onCreateEndTime = SystemClock.elapsedRealtime()
//        val cost = onCreateEndTime - onCreateStartTime
//        Log.d("erdai", "onCreate: $cost")

        initViewPager()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    private fun initViewPager() {
        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(MainFragment())
        fragmentList.add(Main2Fragment())
        fragmentList.add(Main3Fragment())
        val fragmentPagerAdapter = MainFragmentPagerAdapter(this,fragmentList)
        viewPage.adapter = fragmentPagerAdapter
        viewPage.offscreenPageLimit = fragmentList.size
        tabLayout.setupWithViewPager(viewPage)
    }


    override fun onStart() {
        super.onStart()
        //Log.d("erdai", "Activity onStart: ")
    }

    override fun onResume() {
        super.onResume()

//        val cost = SystemClock.elapsedRealtime() - onCreateStartTime
//        Log.d("erdai", "Activity onResume: $cost")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

//        val cost = SystemClock.elapsedRealtime() - onCreateStartTime
//        Log.d("erdai", "onWindowFocusChanged: $cost")
    }
    
    

    fun anrClick(view: View) {
        //1、多线程死锁
//        val anrMock = AnrMock()
//        anrMock.testAnr()
        //2、主线程耗时操作
        //Thread.sleep(100000)
//        if(!NetworkUtils.isWifiConnected()){
//            Toast.makeText(this, "Wifi 不可用", Toast.LENGTH_SHORT).show()
//        }


        val nextInt = java.util.Random().nextFloat()
        Log.d("erdai", "anrClick: $nextInt")

//        startActivity(Intent(this,SecondActivity::class.java))

    }
}