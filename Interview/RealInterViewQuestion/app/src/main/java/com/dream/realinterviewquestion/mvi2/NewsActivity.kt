package com.dream.realinterviewquestion.mvi2

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dream.realinterviewquestion.R
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {

    private val viewModel by viewModels<NewsViewModel>()

    private val tv by lazy {
        findViewById<TextView>(R.id.tv)
    }

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        lifecycleScope.launch {
            viewModel.uiState.collect{ uiState ->
                when(uiState){
                    is LatestNewsUiState.Success ->{
                        dismissLoadingDialog()
                        updateUI(uiState)
                    }
                    is LatestNewsUiState.Error ->{
                        dismissLoadingDialog()
                        Toast.makeText(this@NewsActivity, "出错了", Toast.LENGTH_SHORT).show()
                    }
                    is LatestNewsUiState.Loading ->{
                        showLoadingDialog()
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(uiState: LatestNewsUiState.Success){
        if(uiState.dataList == null)return

        tv.text = "result is ${uiState.dataList?.get(0)?.name?:""}"
    }

    fun doGet(view: View){
        lifecycleScope.launch {
            viewModel.newsChannel.send(NewsIntent.GetNews)
        }
    }

    private fun dismissLoadingDialog() {
        progressDialog?.dismiss()
    }

    @Suppress("DEPRECATION")
    private fun showLoadingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("加载中...")
        progressDialog?.show()
    }
}