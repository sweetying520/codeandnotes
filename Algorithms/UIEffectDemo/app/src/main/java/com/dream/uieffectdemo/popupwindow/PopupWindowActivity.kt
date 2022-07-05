package com.dream.uieffectdemo.popupwindow

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ScreenUtils
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.databinding.ActivityPopupWindowBinding

class PopupWindowActivity : AppCompatActivity() {

    private var mBinding: ActivityPopupWindowBinding? = null
    private val mViewModel by viewModels<PopupWindowViewModel>()
    private var smartPopupWindow: SmartPopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_popup_window)

        mBinding?.lifecycleOwner = this
        mBinding?.viewModel = mViewModel

        initRv()
    }

    @SuppressLint("InflateParams")
    private fun initRv() {
        val mRecyclerView = mBinding?.rv
        val mAdapter = PopupWindowRvItemAdapter(this)
        mAdapter.onRvItemLongClickListener = { anchorView, _ ->
            //showPopupWindow(anchorView)
            if(smartPopupWindow == null){
                smartPopupWindow = SmartPopupWindow(this)
            }
            smartPopupWindow?.showPopupWindow(anchorView)
        }
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
        mRecyclerView?.adapter = mAdapter
        val mDataList = mViewModel.mockDataList()

        mAdapter.setNewData(mDataList)

    }

//    val location: IntArray = IntArray(2)
//
//    @SuppressLint("InflateParams")
//    private fun showPopupWindow(anchorView: View) {
//        val contentView = layoutInflater.inflate(R.layout.item_popupwindow_view, null)
//        val ivTriangleUp = contentView.findViewById<ImageView>(R.id.iv_triangle_up)
//        val ivTriangleDown = contentView.findViewById<ImageView>(R.id.iv_triangle_down)
//        val mPopupWindow = PopupWindow(
//            contentView,
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            true
//        )
//        mPopupWindow.isOutsideTouchable = true
//        mPopupWindow.setBackgroundDrawable(ColorDrawable())
//
//
//        val width = anchorView.width
//        val height = anchorView.height
//        Log.d("erdai", "anchorView: $width $height")
//        anchorView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
//        Log.d("erdai", "anchorView measure: ${anchorView.measuredWidth} ${anchorView.measuredHeight}")
//        contentView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
//        Log.d("erdai", "contentView measure: ${contentView.measuredWidth} ${contentView.measuredHeight}")
//        val screenWidth = ScreenUtils.getScreenWidth()
//        Log.d("erdai", "screenWidth: $screenWidth")
//
//        anchorView.getLocationOnScreen(location)
//        Log.d("erdai", "showPopupWindow location[]: ${location[0]} ${location[1]}  anchorView.left: ${anchorView.left}")
//        if(location[1] > (ScreenUtils.getAppScreenHeight() / 2 + 100)){
//            ivTriangleUp.visibility = View.GONE
//            ivTriangleDown.visibility = View.VISIBLE
//            ivTriangleDown.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
//            Log.d("erdai", "ivTriangleDown height: ${ivTriangleDown.measuredHeight}")
//            mPopupWindow.showAtLocation(anchorView,
//                Gravity.NO_GRAVITY,location[0] + anchorView.width / 2 - contentView.measuredWidth / 2,location[1] - contentView.measuredHeight - ivTriangleDown.measuredHeight)
//        }else{
//            ivTriangleUp.visibility = View.VISIBLE
//            ivTriangleDown.visibility = View.GONE
//            mPopupWindow.showAsDropDown(anchorView,anchorView.width / 2 - contentView.measuredWidth / 2,0)
//        }
//    }
}