@file:Suppress("UnusedImport")

package com.dream.uieffectdemo.baservadapterhelper

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.dream.refresh.helper.RefreshListHelper
import com.dream.refresh.helper.SimpleLoadHelper
import com.dream.refresh.status.StatusViewProvider
import com.dream.uieffectdemo.databinding.ActivityLoadingUpPullBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 * function: 上拉加载更多 Activity
 * 1、使用 AppSmartRefreshLayout 那一套
 * 2、使用 BaseQuickAdapter 那一套
 *
 * @author zy
 * @since 2022/7/20
 */
class PullUpLoadingActivity: AppCompatActivity(), BaseQuickAdapter.RequestLoadMoreListener {

    private lateinit var mBinding: ActivityLoadingUpPullBinding
    private val mViewModel by viewModels<PullUpLoadingViewModel>()
    private lateinit var mSimpleLoadHelper: SimpleLoadHelper<String>
    private lateinit var mAdapter: PullUpLoadingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoadingUpPullBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initRefreshList()
        initRecyclerView()
        initObserverListener()
        initData()
    }


    private fun initRefreshList() {
        mBinding.statusView.setStatusViewProvider(object : StatusViewProvider{
            override fun getEmptyView(context: Context?, container: ViewGroup?): View? {
                return null
            }

            override fun getErrorView(context: Context?, container: ViewGroup?): View? {
                return null
            }

            override fun getLoadingView(context: Context?, container: ViewGroup?): View? {
                return null
            }

            override fun getContentView(context: Context?, container: ViewGroup?): View? {
                return null
            }

        })
//        mSimpleLoadHelper = RefreshListHelper.with(mBinding.refreshLayout)
//            .layoutManager(LinearLayoutManager(this))
//            .bindAdapter(PullUpLoadingAdapter())
//            .simpleLoadHelper()
//            .statusView(mBinding.statusView)
//            .onRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
//                override fun onRefresh(refreshLayout: RefreshLayout) {
//                    mViewModel.onRefresh()
//                }
//
//                override fun onLoadMore(refreshLayout: RefreshLayout) {
//                    mViewModel.onLoadMore()
//                }
//
//            })
    }

    private fun initRecyclerView() {
        mBinding.refreshLayout.isEnableLoadMore = false
        mBinding.refreshLayout.setEnableRefresh(false)
        mBinding.rvPullUp.layoutManager = LinearLayoutManager(this)
        mAdapter = PullUpLoadingAdapter()
        mAdapter.setOnLoadMoreListener(this,mBinding.rvPullUp)
        mBinding.rvPullUp.adapter = mAdapter
    }

    private fun initObserverListener() {
        mViewModel.mDataListLiveData.observe(this){
            //mSimpleLoadHelper.finishWithData(it)
            mBinding.statusView.showContent()
            if(it.isNullOrEmpty()){
                mAdapter.loadMoreEnd()
                return@observe
            }
            if(mAdapter.data.isEmpty()){
                mAdapter.setNewData(it)
            }else{
                mAdapter.addData(it)
                mAdapter.loadMoreComplete()
            }
        }
    }

    private fun initData() {
        mBinding.statusView.showLoading()
        mViewModel.getNetWorkDataList()
    }

    override fun onLoadMoreRequested() {
        mViewModel.getNetWorkDataList()
    }
}